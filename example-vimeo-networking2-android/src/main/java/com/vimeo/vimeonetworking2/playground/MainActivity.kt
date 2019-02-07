package com.vimeo.vimeonetworking2.playground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vimeo.moshiexampleandroid.R
import com.vimeo.networking2.ApiResponse
import com.vimeo.networking2.Authenticator
import com.vimeo.networking2.BasicAccessToken
import com.vimeo.networking2.VimeoCallback
import com.vimeo.networking2.config.ServerConfig
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login.setOnClickListener {

            val serverConfig = ServerConfig(CLIENT_ID, CLIENT_SECRET)
            val authenticator = Authenticator.create(serverConfig)

            authenticator.clientCredentials(object: VimeoCallback<BasicAccessToken> {
                override fun onSuccess(response: ApiResponse.Success<BasicAccessToken>) {

                }

                override fun onApiError(apiError: ApiResponse.Failure.ApiFailure) {

                }

                override fun onGenericError(genericFailure: ApiResponse.Failure.GenericFailure) {

                }

                override fun onExceptionError(exceptionFailure: ApiResponse.Failure.ExceptionFailure) {

                }
            })

        }

    }

    companion object {
        const val CLIENT_ID = "CLIENT ID"
        const val CLIENT_SECRET = "CLIENT SECRET"
    }
}
