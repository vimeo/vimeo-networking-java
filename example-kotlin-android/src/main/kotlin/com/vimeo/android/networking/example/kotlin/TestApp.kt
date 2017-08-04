package com.vimeo.android.networking.example.kotlin

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

import com.vimeo.android.networking.example.kotlin.vimeonetworking.NetworkingLogger
import com.vimeo.android.networking.example.kotlin.vimeonetworking.TestAccountStore
import com.vimeo.networking.Configuration
import com.vimeo.networking.Vimeo.LogLevel
import com.vimeo.networking.VimeoClient

/**
 * The application class.
 *
 * Created by anthonyr on 5/8/17.
 */
class TestApp : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = this
        AccountPreferenceManager.initializeInstance(appContext!!)

        // <editor-fold desc="Vimeo API Library Initialization">
        val configBuilder: Configuration.Builder
        // This check is just as for the example. In practice, you'd use one technique or the other.
        if (ACCESS_TOKEN_PROVIDED) {
            configBuilder = accessTokenBuilder
        } else {
            configBuilder = clientIdAndClientSecretBuilder
        }
        if (IS_DEBUG_BUILD) {
            // Disable cert pinning if debugging (so we can intercept packets)
            configBuilder.enableCertPinning(false)
            configBuilder.setLogLevel(LogLevel.VERBOSE)
        }
        VimeoClient.initialize(configBuilder.build())
        // </editor-fold>
    }

    // The values file is left out of git, so you'll have to provide your own access token
    val accessTokenBuilder: Configuration.Builder
        get() {
            val accessToken = getString(R.string.access_token)
            return Configuration.Builder(accessToken)
        }

    // The values file is left out of git, so you'll have to provide your own id and secret
    // Used for oauth flow
    val clientIdAndClientSecretBuilder: Configuration.Builder
        get() {
            val clientId = getString(R.string.client_id)
            val clientSecret = getString(R.string.client_secret)
            val codeGrantRedirectUri = getString(R.string.deeplink_redirect_scheme) + "://" + getString(R.string.deeplink_redirect_host)
            val testAccountStore = TestAccountStore(this.applicationContext)
            val configBuilder = Configuration.Builder(clientId, clientSecret, SCOPE)
            configBuilder.setCacheDirectory(this.cacheDir)
                    .setUserAgentString(getUserAgentString(this)).setDebugLogger(NetworkingLogger())
                    .setCodeGrantRedirectUri(codeGrantRedirectUri)

            return configBuilder
        }

    companion object {

        private val SCOPE = "private public create edit delete interact"

        private val IS_DEBUG_BUILD = false
        // Switch to true to see how access token auth works.
        private val ACCESS_TOKEN_PROVIDED = false

        var appContext: Context? = null
            private set

        fun getUserAgentString(context: Context): String {
            val packageName = context.packageName

            var version = "unknown"
            try {
                val pInfo = context.packageManager.getPackageInfo(packageName, 0)
                version = pInfo.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                println("Unable to get packageInfo: " + e.message)
            }

            val deviceManufacturer = Build.MANUFACTURER
            val deviceModel = Build.MODEL
            val deviceBrand = Build.BRAND

            val versionString = Build.VERSION.RELEASE
            val versionSDKString = Build.VERSION.SDK_INT.toString()

            return packageName + " (" + deviceManufacturer + ", " + deviceModel + ", " + deviceBrand +
                    ", " + "Android " + versionString + "/" + versionSDKString + " Version " + version +
                    ")"
        }
    }
}
