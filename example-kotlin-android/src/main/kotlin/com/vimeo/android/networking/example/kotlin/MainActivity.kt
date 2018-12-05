package com.vimeo.android.networking.example.kotlin

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.vimeo.networking.VimeoClient
import com.vimeo.networking.callbacks.AuthCallback
import com.vimeo.networking.callbacks.VimeoCallback
import com.vimeo.networking.callers.GetRequestCaller
import com.vimeo.networking.callers.MoshiGetRequestCaller
import com.vimeo.networking.model.User
import com.vimeo.networking.model.VideoList
import com.vimeo.networking.model.error.VimeoError
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.*
import okhttp3.CacheControl
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.system.measureTimeMillis

/**
 * The main activity.
 **/
class MainActivity : AppCompatActivity() {

    private val job = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private val apiClient = VimeoClient.getInstance()

    private val progressDialog by lazy {
        ProgressDialog(this).also { it.setCancelable(false) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ---- Initial UI Setup ----
        setSupportActionBar(toolbar)
        progressDialog.setMessage("All of your API are belong to us...")

        // ---- Code Grant Check ----
        handleCodeGrantIfNecessary()

        // ---- Client Credentials Auth ----
        if (apiClient.vimeoAccount.accessToken == null) {
            // If there is no access token, fetch one on first app open
            authenticateWithClientCredentials()
        }

        // ---- View Binding ----
        val codeGrantClick = {
            toast("Authenticate on Web")
            goToWebForCodeGrantAuth()
        }
        fab.setOnClickListener { codeGrantClick() }
        code_grant_btn.setOnClickListener { codeGrantClick() }

        staff_picks_btn.setOnClickListener { fetchStaffPicks() }
        staff_picks_gson_btn.setOnClickListener { timeGsonStaffPicksRequest() }
        staff_picks_moshi_btn.setOnClickListener { timeMoshiStaffPicksRequest() }

        account_type_btn.setOnClickListener { fetchAccountType() }
        logout_btn.setOnClickListener { logout() }
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialog.dismiss()
        job.cancel()
    }

    private fun timeMoshiStaffPicksRequest() {
        uiScope.launch {
            progressDialog.show()
            val moshiTime = async(Dispatchers.IO) {
                measureTimeMillis {
                    repeat(repeatTimes) {
                        fetchStaffPicky(MoshiGetRequestCaller.VIDEO_LIST)
                    }
                }
            }.await()

            progressDialog.dismiss()
            staff_picks_request_time.text = getString(R.string.request_time, "Moshi", moshiTime)
        }
    }

    private fun timeGsonStaffPicksRequest() {
        uiScope.launch {
            progressDialog.show()
            val gsonTime = async(Dispatchers.IO) {
                measureTimeMillis {
                    repeat(repeatTimes) {
                        fetchStaffPicky(GetRequestCaller.VIDEO_LIST)
                    }
                }
            }.await()

            progressDialog.dismiss()
            staff_picks_request_time.text = getString(R.string.request_time, "Gson", gsonTime)

        }
    }

    private suspend fun <DataType_T> fetchStaffPicky(
        caller: VimeoClient.Caller<DataType_T>
    ) {
        getContent(
            uri = STAFF_PICKS_VIDEO_URI,
            cacheControl = CacheControl.FORCE_NETWORK,
            caller = caller,
            query = null,
            refinementMap = null,
            fieldFilter = null
        )
    }

    private suspend fun <DataType_T> getContent(
        uri: String,
        cacheControl: CacheControl,
        caller: VimeoClient.Caller<DataType_T>,
        query: String?,
        refinementMap: Map<String, String>?,
        fieldFilter: String?
    ): DataType_T? = suspendCoroutine { cont ->
        apiClient.getContent(
            uri,
            cacheControl,
            caller,
            query,
            refinementMap,
            fieldFilter,
            object : VimeoCallback<DataType_T>() {
                override fun success(t: DataType_T) {
                    cont.resume(t)
                }

                override fun failure(error: VimeoError?) {
                    cont.resume(null)
                }

            })
    }

    private fun fetchStaffPicks() {
        progressDialog.show()
        apiClient.getContent(
            STAFF_PICKS_VIDEO_URI,
            CacheControl.FORCE_NETWORK,
            GetRequestCaller.VIDEO_LIST,
            null, null, null,
            object : VimeoCallback<VideoList>() {
                override fun success(videoList: VideoList?) {
                    if (videoList?.data != null) {
                        val videoTitles = StringBuilder()
                        var addNewLine = false

                        videoList.data.forEach { video ->
                            if (addNewLine) videoTitles.append("\n")
                            addNewLine = true
                            videoTitles.append(video.name)
                        }

                        request_output_tv.text = videoTitles.toString()
                    }
                    toast("Staff Picks Success")
                    progressDialog.hide()
                }

                override fun failure(error: VimeoError?) {
                    toast("Staff Picks Failure")
                    request_output_tv.text = error?.developerMessage
                    progressDialog.hide()
                }

            }
        )
    }

    private fun fetchAccountType() {
        progressDialog.show()
        apiClient.getCurrentUser(
            object : VimeoCallback<User>() {
                override fun success(user: User?) {
                    if (user != null) {
                        request_output_tv.text = "Current account type: ${user.membership?.display}"
                        toast("Account Check Success")
                    } else {
                        toast("Account Check Failure")
                    }
                    progressDialog.hide()
                }

                override fun failure(error: VimeoError) {
                    toast("Account Check Failure")
                    request_output_tv.text = error.developerMessage
                    progressDialog.hide()
                }
            }
        )
    }

    private fun logout() {
        progressDialog.show()
        apiClient.logOut(
            object : VimeoCallback<Any>() {
                override fun success(o: Any) {
                    AccountPreferenceManager.removeClientAccount()
                    toast("Logout Success")
                    progressDialog.hide()
                }

                override fun failure(error: VimeoError) {
                    AccountPreferenceManager.removeClientAccount()
                    toast("Logout Failure")
                    request_output_tv.text = error.developerMessage
                    progressDialog.hide()
                }
            }
        )
    }

    // You can't make any requests to the api without an access token. This will get you a basic
    // "Client Credentials" gran which will allow you to make requests
    private fun authenticateWithClientCredentials() {
        progressDialog.show()
        apiClient.authorizeWithClientCredentialsGrant(
            object : AuthCallback {
                override fun success() {
                    toast("Client Credentials Authorization Success")
                    progressDialog.hide()
                }

                override fun failure(error: VimeoError) {
                    toast("Client Credentials Authorization Failure")
                    request_output_tv.text = error.developerMessage
                    progressDialog.hide()
                }
            }
        )
    }

    private fun authenticateWithCodeGrant(uri: Uri) {
        progressDialog.show()
        if (uri.query.isNullOrEmpty()) {
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
                request_output_tv.text = error.developerMessage
                progressDialog.hide()
            }
        })
    }

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
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
    }

    private fun toast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    companion object {

        private const val STAFF_PICKS_VIDEO_URI = "/channels/927/videos" // 927 == staffpicks

        private const val repeatTimes = 10
    }

}
