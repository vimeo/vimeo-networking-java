package com.vimeo.android.networking.example;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.vimeo.android.networking.example.vimeonetworking.AndroidGsonDeserializer;
import com.vimeo.android.networking.example.vimeonetworking.NetworkingLogger;
import com.vimeo.android.networking.example.vimeonetworking.TestAccountStore;
import com.vimeo.networking.Configuration;
import com.vimeo.networking.Vimeo.LogLevel;
import com.vimeo.networking.VimeoClient;

/**
 * Created by kylevenn on 1/27/16.
 */
public class TestApp extends Application {

    private static final String SCOPE = "private public create edit delete interact";

    private static final boolean IS_DEBUG_BUILD = false;
    // Switch to true to see how access token auth works.
    private static final boolean ACCESS_TOKEN_PROVIDED = false;

    @Override
    public void onCreate() {
        super.onCreate();

        AccountPreferenceManager.initializeInstance(this);

        // <editor-fold desc="Vimeo API Library Initialization">
        final Configuration.Builder configBuilder;
        // This check is just as for the example. In practice, you'd use one technique or the other.
        if (ACCESS_TOKEN_PROVIDED) {
            configBuilder = getAccessTokenBuilder();
        } else {
            configBuilder = getClientIdAndClientSecretBuilder();
        }
        if (IS_DEBUG_BUILD) {
            // Disable cert pinning if debugging (so we can intercept packets)
            configBuilder.enableCertPinning(false);
            configBuilder.setLogLevel(LogLevel.VERBOSE);
        }
        VimeoClient.initialize(configBuilder.build());
        // </editor-fold>
    }

    public Configuration.Builder getAccessTokenBuilder() {
        // The values file is left out of git, so you'll have to provide your own access token
        final String accessToken = getString(R.string.access_token);
        return new Configuration.Builder(accessToken);
    }

    public Configuration.Builder getClientIdAndClientSecretBuilder() {
        // The values file is left out of git, so you'll have to provide your own id and secret
        final String clientId = getString(R.string.client_id);
        final String clientSecret = getString(R.string.client_secret);
        final String codeGrantRedirectUri = getString(R.string.deeplink_redirect_scheme) + "://" +
                                            getString(R.string.deeplink_redirect_host);
        final TestAccountStore testAccountStore = new TestAccountStore(this.getApplicationContext());
        final Configuration.Builder configBuilder =
                new Configuration.Builder(clientId, clientSecret, SCOPE, testAccountStore,
                                          new AndroidGsonDeserializer());
        configBuilder.setCacheDirectory(this.getCacheDir())
                .setUserAgentString(getUserAgentString(this)).setDebugLogger(new NetworkingLogger())
                // Used for oauth flow
                .setCodeGrantRedirectUri(codeGrantRedirectUri);

        return configBuilder;
    }

    public static String getUserAgentString(Context context) {
        final String packageName = context.getPackageName();

        String version = "unknown";
        try {
            final PackageInfo pInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            version = pInfo.versionName;
        } catch (final PackageManager.NameNotFoundException e) {
            Log.e("TestApp","Unable to get packageInfo: " + e.getMessage());
        }

        final String deviceManufacturer = Build.MANUFACTURER;
        final String deviceModel = Build.MODEL;
        final String deviceBrand = Build.BRAND;

        final String versionString = Build.VERSION.RELEASE;
        final String versionSDKString = String.valueOf(Build.VERSION.SDK_INT);

        return packageName + " (" + deviceManufacturer + ", " + deviceModel + ", " + deviceBrand +
               ", " + "Android " + versionString + "/" + versionSDKString + " Version " + version +
               ")";
    }
}
