package com.vimeo.android.networking.example.kotlin

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import android.widget.Toast
import com.vimeo.networking.VimeoClient
import com.vimeo.networking.callbacks.AuthCallback
import com.vimeo.networking.callbacks.ModelCallback
import com.vimeo.networking.callbacks.VimeoCallback
import com.vimeo.networking.model.User
import com.vimeo.networking.model.VideoList
import com.vimeo.networking.model.error.VimeoError

class MainActivity : AppCompatActivity(), OnClickListener {

    private val mApiClient = VimeoClient.getInstance()
    private var mProgressDialog: ProgressDialog? = null

    private var mRequestOutputTv: TextView? = null

    // <editor-fold desc="Life Cycle">

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ---- Initial UI Setup ----
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        mProgressDialog = ProgressDialog(this)
        mProgressDialog!!.setMessage("All of your API are belong to us...")

        // ---- Code Grant Check ----
        handleCodeGrantIfNecessary()

        // ---- Client Credentials Auth ----
        if (mApiClient.vimeoAccount.accessToken == null) {
            // If there is no access token, fetch one on first app open
            authenticateWithClientCredentials()
        }

        // ---- View Binding ----
        mRequestOutputTv = findViewById(R.id.request_output_tv) as TextView
        findViewById(R.id.fab)!!.setOnClickListener(this)
        findViewById(R.id.code_grant_btn)!!.setOnClickListener(this)
        findViewById(R.id.request_output_tv)!!.setOnClickListener(this)
        findViewById(R.id.staff_picks_btn)!!.setOnClickListener(this)
        findViewById(R.id.account_type_btn)!!.setOnClickListener(this)
        findViewById(R.id.logout_btn)!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.staff_picks_btn -> fetchStaffPicks()
            R.id.account_type_btn -> fetchAccountType()
            R.id.logout_btn -> logout()
            R.id.code_grant_btn, R.id.fab -> {
                toast("Authenticate on Web")
                goToWebForCodeGrantAuth()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    // </editor-fold>

    // <editor-fold desc="Requests">

    private fun fetchStaffPicks() {
        mProgressDialog!!.show()
        mApiClient.fetchNetworkContent(STAFF_PICKS_VIDEO_URI, object : ModelCallback<VideoList>(VideoList::class.java) {
            override fun success(videoList: VideoList?) {
                if (videoList != null && videoList.mData != null) {
                    var videoTitlesString = ""
                    var addNewLine = false
                    for (video in videoList.mData) {
                        if (addNewLine) {
                            videoTitlesString += "\n"
                        }
                        addNewLine = true
                        videoTitlesString += video.mName
                    }
                    mRequestOutputTv!!.text = videoTitlesString
                }
                toast("Staff Picks Success")
                mProgressDialog!!.hide()
            }

            override fun failure(error: VimeoError) {
                toast("Staff Picks Failure")
                mRequestOutputTv!!.text = error.developerMessage
                mProgressDialog!!.hide()
            }
        })
    }

    private fun fetchAccountType() {
        mProgressDialog!!.show()
        mApiClient.fetchCurrentUser(object : ModelCallback<User>(User::class.java) {
            override fun success(user: User?) {
                if (user != null) {
                    mRequestOutputTv!!.text = "Current account type: " + user.mAccount
                    toast("Account Check Success")
                } else {
                    toast("Account Check Failure")
                }
                mProgressDialog!!.hide()
            }

            override fun failure(error: VimeoError) {
                toast("Account Check Failure")
                mRequestOutputTv!!.text = error.developerMessage
                mProgressDialog!!.hide()
            }
        })
    }

    private fun logout() {
        mProgressDialog!!.show()
        mApiClient.logOut(object : VimeoCallback<Any>() {
            override fun success(o: Any) {
                AccountPreferenceManager.removeClientAccount()
                toast("Logout Success")
                mProgressDialog!!.hide()
            }

            override fun failure(error: VimeoError) {
                AccountPreferenceManager.removeClientAccount()
                toast("Logout Failure")
                mRequestOutputTv!!.text = error.developerMessage
                mProgressDialog!!.hide()
            }
        })
    }

    // You can't make any requests to the api without an access token. This will get you a basic
    // "Client Credentials" gran which will allow you to make requests
    private fun authenticateWithClientCredentials() {
        mProgressDialog!!.show()
        mApiClient.authorizeWithClientCredentialsGrant(object : AuthCallback {
            override fun success() {
                toast("Client Credentials Authorization Success")
                mProgressDialog!!.hide()
            }

            override fun failure(error: VimeoError) {
                toast("Client Credentials Authorization Failure")
                mRequestOutputTv!!.text = error.developerMessage
                mProgressDialog!!.hide()
            }
        })
    }

    private fun authenticateWithCodeGrant(uri: Uri) {
        mProgressDialog!!.show()
        if (uri.query == null || uri.query.isEmpty()) {
            toast("Bad deep link - no query parameters")
            return
        }
        mApiClient.authenticateWithCodeGrant(uri.toString(), object : AuthCallback {
            override fun success() {
                toast("Code Grant Success")
                mProgressDialog!!.hide()
            }

            override fun failure(error: VimeoError) {
                toast("Code Grant Failure")
                mRequestOutputTv!!.text = error.developerMessage
                mProgressDialog!!.hide()
            }
        })
    }

    // </editor-fold>

    // <editor-fold desc="Code Grant">

    // We deep link to this activity as specified in the AndroidManifest.
    private fun handleCodeGrantIfNecessary() {
        if (intent != null) {
            val action = intent.action
            val uri = intent.data
            if (Intent.ACTION_VIEW == action && uri != null) {
                // This is coming from a deep link
                authenticateWithCodeGrant(uri)
            }
        }
    }

    private fun goToWebForCodeGrantAuth() {
        val uri = mApiClient.codeGrantAuthorizationURI
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(browserIntent)
    }

    // </editor-fold>

    // <editor-fold desc="Helpers">

    private fun toast(string: String) {
        Toast.makeText(this@MainActivity, string, Toast.LENGTH_SHORT).show()
    }

    companion object {

        val STAFF_PICKS_VIDEO_URI = "/channels/927/videos" // 927 == staffpicks
    }

    /*
    // Can only fetch quota if you have the upload privilege
    private void fetchAccountType() {
        mProgressDialog.show();
        mApiClient.fetchCurrentUser(new ModelCallback<User>(User.class) {
            @Override
            public void success(User user) {
                long fileSizeBytes = user.getFreeUploadSpace();
                if (user.getFreeUploadSpace() != Vimeo.NOT_FOUND) {
                    String formattedFileSize =
                            Formatter.formatShortFileSize(TestApp.getAppContext(), fileSizeBytes);
                    mRequestOutputTv.setText("Available Space: " + formattedFileSize);
                    toast("Quote Check Success");
                } else {
                    toast("Quote Check Failure");
                }
                mProgressDialog.hide();
            }

            @Override
            public void failure(VimeoError error) {
                mRequestOutputTv.setText(error.getDeveloperMessage());
                mProgressDialog.hide();
            }
        });
    }
    */

    // </editor-fold>
}
