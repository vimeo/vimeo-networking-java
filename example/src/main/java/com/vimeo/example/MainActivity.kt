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

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vimeo.networking2.*
import com.vimeo.networking2.config.VimeoApiConfiguration
import kotlinx.android.synthetic.main.activity_main.*

/**
 * The sample activity in which we create an [Authenticator] instance to authenticate with the server.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Construct a configuration instance.
        val configuration = VimeoApiConfiguration.Builder(CLIENT_ID, CLIENT_SECRET, listOf(ScopeType.PUBLIC))
            .withCodeGrantRedirectUrl("https://example.com")
            .build()

        // Initialize the authenticator instance with the configuration and the api client.
        val authenticator = Authenticator(configuration)
        val apiClient = VimeoApiClient(configuration, authenticator)

        // If we are handling a code grant redirect, this URI will be present, otherwise it's a normal app start.
        val redirectUri = intent?.data

        if (redirectUri != null) {
            // Log in using the code grant URI provided to us by the redirect.
            authenticator.authenticateWithCodeGrant(redirectUri.toString(), vimeoCallback(
                onSuccess = {
                    toast("Successfully logged in.")
                    loginStatus.text = authenticator.currentAccount.asLoginStatus()
                },
                onError = { toast("Unable to log in.") }
            ))
        }

        loginStatus.text = authenticator.currentAccount.asLoginStatus()

        clientCredentials.setOnClickListener {
            // Obtain logged out credentials which can be used to make logged out requests.
            authenticator.clientCredentials(vimeoCallback(
                onSuccess = {
                    toast("Obtained client credentials.")
                    loginStatus.text = authenticator.currentAccount.asLoginStatus()
                },
                onError = { toast("Couldn't obtain client credentials") }
            ))
        }

        login.setOnClickListener {
            // Open the browser with the code grant authorization URI and let the user log in
            startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse(authenticator.obtainCodeGrantAuthorizationUri(REQUEST_CODE))
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
            val uri = authenticator.currentAccount?.user?.metadata?.connections?.videos?.uri ?: ""
            apiClient.fetchVideoList(uri, null, null, null, vimeoCallback(
                onSuccess = { myVideosList.text = it.data.asNameList() },
                onError = {
                    myVideosList.text = null
                    toast("Unable to fetch user's videos.")
                }
            ))
        }
    }

    private fun VideoList.asNameList(): String = data
        ?.map { it.name }
        ?.takeIf { it.isNotEmpty() }
        ?.joinToString(separator = "\n")
        ?: "No Videos"

    private fun VimeoAccount?.asLoginStatus() = when {
        this == null -> "Logged Out - Unauthenticated"
        this.isLoggedIn -> "Logged In - Authenticated"
        else -> "Logged Out - Authenticated"
    }

    private fun toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    companion object {
        const val CLIENT_ID = "CLIENT ID"
        const val CLIENT_SECRET = "CLIENT SECRET"
        const val REQUEST_CODE = 12345
    }
}
