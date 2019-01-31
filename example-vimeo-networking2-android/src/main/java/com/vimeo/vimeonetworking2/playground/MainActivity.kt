package com.vimeo.vimeonetworking2.playground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vimeo.moshiexampleandroid.R
import com.vimeo.networking2.Authenticator
import com.vimeo.networking2.config.ServerConfig
import com.vimeo.networking2.requests.ApiResponse
import com.vimeo.networking2.requests.AuthCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val serverConfig = ServerConfig(CLIENT_ID, CLIENT_SECRET)
            val authenticator = Authenticator.create(serverConfig)

            authenticator.clientCredentials(object : AuthCallback {

                override fun onSuccess(authResponse: ApiResponse.Success<String>) {
                    println("authResponse = [${authResponse}]")
                }

                override fun onGenericError(genericFailure: ApiResponse.Failure.GenericFailure) {
                    println("genericFailure = [${genericFailure}]")
                }

                override fun onApiError(apiFailure: ApiResponse.Failure.ApiFailure) {
                    println("apiFailure = [${apiFailure}]")
                }

                override fun onExceptionError(exceptionFailure: ApiResponse.Failure.ExceptionFailure) {
                    println("exceptionFailure = [${exceptionFailure}]")
                }
            })

        }
    }

    companion object {
        const val CLIENT_ID = "CLIENT ID"
        const val CLIENT_SECRET = "CLIENT SECRET"
    }
}
