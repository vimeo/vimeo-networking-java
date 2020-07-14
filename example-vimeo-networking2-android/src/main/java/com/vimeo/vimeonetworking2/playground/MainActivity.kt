package com.vimeo.vimeonetworking2.playground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vimeo.moshiexampleandroid.R
import com.vimeo.networking2.*
import com.vimeo.networking2.config.Configuration
import kotlinx.android.synthetic.main.activity_main.*

/**
 * The sample activity in which we create an authenticator instance and then authenticate with the server.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login.setOnClickListener {
            val authenticator = Authenticator(
                Configuration.Builder(
                    CLIENT_ID,
                    CLIENT_SECRET,
                    listOf(ScopeType.PUBLIC)
                ).build()
            )

            authenticator.clientCredentials(vimeoCallback(
                onSuccess = {
                    // Use the logged out token
                },
                onError = {
                    // Handle the error
                }
            ))
        }
    }

    companion object {
        const val CLIENT_ID = "CLIENT ID"
        const val CLIENT_SECRET = "CLIENT SECRET"
    }
}
