/*
 * Copyright (c) 2020 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.vimeo.example

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vimeo.networking2.*
import com.vimeo.networking2.config.VimeoApiConfiguration
import kotlinx.android.synthetic.main.activity_main.*

/**
 * An sample activity in which we create an [Authenticator] instance to authenticate with the server and a
 * [VimeoApiClient] to make authenticated requests to the API.
 *
 * Please refer to [https://developer.vimeo.com/api/guides/start] for more information on how to get started with using
 * the Vimeo API if you are new to using it.
 *
 * In order to use this example, first you need to follow these steps:
 * 1. Go to [https://developer.vimeo.com/apps] and create an API app if you haven't created one.
 * 2. After creating the API app, find the client ID and client secret on the app page and replace the [CLIENT_ID] and
 * [CLIENT_SECRET] constants with those values.
 * 3. On the same page that had the client ID and client secret, add a callback URL. Ideally this URL is one that you
 * own and not one that another app will be handling.
 * 4. Replace [CODE_GRANT_REDIRECT_URL] with the URL added on the app page and replace intent filter in the
 * `AndroidManifest.xml` to handle the set URL instead of `https://example.com`.
 * 5. Run the app and make requests.
 */
@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!validateClientIdAndClientSecret()) {
            return
        }

        // Construct a configuration instance.
        val configuration = VimeoApiConfiguration.Builder(CLIENT_ID, CLIENT_SECRET, listOf(ScopeType.PUBLIC))
            .withCodeGrantRedirectUrl(CODE_GRANT_REDIRECT_URL)
            .build()

        // Initialize the authenticator instance with the configuration and the api client.
        val authenticator = Authenticator(configuration)
        val apiClient = VimeoApiClient(configuration, authenticator)

        // If we are handling a code grant redirect, a URI will be present in the Intent, which will be used to log in.
        // Otherwise it's a normal app start and we will obtain client credentials.
        authenticate(intent?.data, authenticator)

        loginStatus.text = authenticator.currentAccount.asLoginStatus()

        login.setOnClickListener {
            // Open the browser with the code grant authorization URI and let the user log in
            startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse(authenticator.createCodeGrantAuthorizationUri(REQUEST_CODE))
            ))
        }

        logOut.setOnClickListener {
            // Log out of whatever credentials are currently being used.
            authenticator.logOut(vimeoCallback(
                onSuccess = {
                    toast("Successfully logged out.")
                    loginStatus.text = authenticator.currentAccount.asLoginStatus()
                },
                onError = { toast("Unable to log out.") }
            ))
        }

        getMyVideos.setOnClickListener {
            // Fetch the currently logged in user's videos. Will fail if the user is not logged in.
            val uri = authenticator.currentAccount?.user?.metadata?.connections?.videos?.uri
                ?: return@setOnClickListener run { myVideosList.text = "Cannot fetch videos until user is logged in!" }

            // Fetch the list of videos, only asking for the "name" field in the response.
            apiClient.fetchVideoList(uri, "name", null, null, vimeoCallback(
                onSuccess = { myVideosList.text = it.data.asNameList() },
                onError = {
                    myVideosList.text = null
                    toast("Unable to fetch user's videos.")
                }
            ))
        }

        getStaffPicks.setOnClickListener {
            // Fetch the list of videos from staff picks, only asking for the "name" field in the response.
            apiClient.fetchVideoList(STAFF_PICKS_URI, "name", null, null, vimeoCallback(
                onSuccess = { staffPicksList.text = it.data.asNameList() },
                onError = {
                    staffPicksList.text = null
                    toast("Unable to fetch staff picked videos.")
                }
            ))
        }
    }

    private fun authenticate(redirectUri: Uri?, authenticator: Authenticator) {
        if (redirectUri != null) {
            // Optional, but for security purposes, we can verify that the code we specified is returned to us.
            val requestCode = redirectUri.getQueryParameter("state")
            require(requestCode == REQUEST_CODE)

            // Log in using the code grant URI provided to us by the redirect.
            authenticator.authenticateWithCodeGrant(redirectUri.toString(), vimeoCallback(
                onSuccess = {
                    toast("Successfully logged in.")
                    loginStatus.text = authenticator.currentAccount.asLoginStatus()
                },
                onError = { toast("Unable to log in.") }
            ))
        } else {
            // Obtain client credentials which can be used to make logged out requests.
            // Obtaining client credentials is technically optional and basic auth can be used instead. However, using
            // basic auth can result in your client secret being leaked, so it is better to use client credentials. If
            // client credentials are not obtained or this request fails, the VimeoApiClient will fall back to basic
            // authentication.
            authenticator.clientCredentials(vimeoCallback(
                onSuccess = {
                    toast("Obtained client credentials.")
                    loginStatus.text = authenticator.currentAccount.asLoginStatus()
                },
                onError = { toast("Couldn't obtain client credentials.") }
            ))
        }
    }

    private fun VideoList.asNameList(): String = data
        ?.map { it.name }
        ?.takeIf { it.isNotEmpty() }
        ?.joinToString(separator = "\n")
        ?: "No Videos"

    private fun VimeoAccount?.asLoginStatus() = when {
        this == null -> "Logged Out - Basic Authentication"
        this.isLoggedIn -> "Logged In - Authenticated"
        else -> "Logged Out - Client Credentials"
    }

    private fun toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    companion object {
        const val CLIENT_ID = "CLIENT ID"
        const val CLIENT_SECRET = "CLIENT SECRET"
        const val CODE_GRANT_REDIRECT_URL = "https://example.com"

        const val REQUEST_CODE = "12345"

        const val STAFF_PICKS_URI = "/channels/927/videos"
    }
}
