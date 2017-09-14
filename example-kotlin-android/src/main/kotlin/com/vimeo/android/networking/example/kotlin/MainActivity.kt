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
import com.vimeo.networking.callbacks.VimeoCallback
import com.vimeo.networking.callers.GetRequestCaller
import com.vimeo.networking.model.User
import com.vimeo.networking.model.VideoList
import com.vimeo.networking.model.error.VimeoError
import okhttp3.CacheControl

/**
 * The main activity.
 *
 * Created by anthonyr on 5/8/17.
 **/
class MainActivity : AppCompatActivity(), OnClickListener {

    private val apiClient = VimeoClient.getInstance()

    private lateinit var progressDialog: ProgressDialog
    private lateinit var requestOutputTv: TextView

    // <editor-fold desc="Life Cycle">

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ---- Initial UI Setup ----
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("All of your API are belong to us...")

        // ---- Code Grant Check ----
        handleCodeGrantIfNecessary()

        // ---- Client Credentials Auth ----
        if (apiClient.vimeoAccount.accessToken == null) {
            // If there is no access token, fetch one on first app open
            authenticateWithClientCredentials()
        }

        // ---- View Binding ----
        requestOutputTv = findViewById(R.id.request_output_tv)
        findViewById<View>(R.id.fab).setOnClickListener(this)
        findViewById<View>(R.id.code_grant_btn).setOnClickListener(this)
        findViewById<View>(R.id.request_output_tv).setOnClickListener(this)
        findViewById<View>(R.id.staff_picks_btn).setOnClickListener(this)
        findViewById<View>(R.id.account_type_btn).setOnClickListener(this)
        findViewById<View>(R.id.logout_btn).setOnClickListener(this)
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
        progressDialog.show()
        apiClient.getContent(STAFF_PICKS_VIDEO_URI, CacheControl.FORCE_NETWORK, GetRequestCaller.VIDEO_LIST, null, null, null, object : VimeoCallback<VideoList>() {
            override fun success(videoList: VideoList?) {
                if (videoList != null && videoList.data != null) {
                    var videoTitlesString = ""
                    var addNewLine = false
                    for (video in videoList.data) {
                        if (addNewLine) {
                            videoTitlesString += "\n"
                        }
                        addNewLine = true
                        videoTitlesString += video.name
                    }
                    requestOutputTv.text = videoTitlesString
                }
                toast("Staff Picks Success")
                progressDialog.hide()
            }

            override fun failure(error: VimeoError?) {
                toast("Staff Picks Failure")
                requestOutputTv.text = error?.developerMessage
                progressDialog.hide()
            }

        })
    }

    private fun fetchAccountType() {
        progressDialog.show()
        apiClient.getCurrentUser(object : VimeoCallback<User>() {
            override fun success(user: User?) {
                if (user != null) {
                    requestOutputTv.text = "Current account type: ${user.account}"
                    toast("Account Check Success")
                } else {
                    toast("Account Check Failure")
                }
                progressDialog.hide()
            }

            override fun failure(error: VimeoError) {
                toast("Account Check Failure")
                requestOutputTv.text = error.developerMessage
                progressDialog.hide()
            }
        })
    }

    private fun logout() {
        progressDialog.show()
        apiClient.logOut(object : VimeoCallback<Any>() {
            override fun success(o: Any) {
                AccountPreferenceManager.removeClientAccount()
                toast("Logout Success")
                progressDialog.hide()
            }

            override fun failure(error: VimeoError) {
                AccountPreferenceManager.removeClientAccount()
                toast("Logout Failure")
                requestOutputTv.text = error.developerMessage
                progressDialog.hide()
            }
        })
    }

    // You can't make any requests to the api without an access token. This will get you a basic
    // "Client Credentials" gran which will allow you to make requests
    private fun authenticateWithClientCredentials() {
        progressDialog.show()
        apiClient.authorizeWithClientCredentialsGrant(object : AuthCallback {
            override fun success() {
                toast("Client Credentials Authorization Success")
                progressDialog.hide()
            }

            override fun failure(error: VimeoError) {
                toast("Client Credentials Authorization Failure")
                requestOutputTv.text = error.developerMessage
                progressDialog.hide()
            }
        })
    }

    private fun authenticateWithCodeGrant(uri: Uri) {
        progressDialog.show()
        if (uri.query == null || uri.query.isEmpty()) {
            toast("Bad deep link - no query parameters")
            return
        }
        apiClient.authenticateWithCodeGrant(uri.toString(), object : AuthCallback {
            override fun success() {
                toast("Code Grant Success")
                progressDialog.hide()
            }

            override fun failure(error: VimeoError) {
                toast("Code Grant Failure")
                requestOutputTv.text = error.developerMessage
                progressDialog.hide()
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
        val uri = apiClient.codeGrantAuthorizationURI
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(browserIntent)
    }

    // </editor-fold>

    // <editor-fold desc="Helpers">

    private fun toast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    companion object {

        val STAFF_PICKS_VIDEO_URI = "/channels/927/videos" // 927 == staffpicks
    }

    /*
    // Can only fetch quota if you have the upload privilege
    private fun fetchAccountTypes() {
        mProgressDialog!!.show()
        mApiClient.fetchCurrentUser(object : ModelCallback<User>(User::class.java) {
            override fun success(user: User) {
                val fileSizeBytes = user.freeUploadSpace
                if (user.freeUploadSpace.compareTo(Vimeo.NOT_FOUND) != 0) {
                    val formattedFileSize = Formatter.formatShortFileSize(TestApp.appContext, fileSizeBytes)
                    mRequestOutputTv!!.text = "Available Space: " + formattedFileSize
                    toast("Quote Check Success")
                } else {
                    toast("Quote Check Failure")
                }
                mProgressDialog!!.hide()
            }

            override fun failure(error: VimeoError) {
                mRequestOutputTv!!.text = error.developerMessage
                mProgressDialog!!.hide()
            }
        })
    }
    */

    // </editor-fold>
}
