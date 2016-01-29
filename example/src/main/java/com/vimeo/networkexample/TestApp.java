package com.vimeo.networkexample;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.vimeo.networkexample.vimeonetworking.AndroidGsonDeserializer;
import com.vimeo.networkexample.vimeonetworking.NetworkingLogger;
import com.vimeo.networkexample.vimeonetworking.TestAccountStore;
import com.vimeo.networking.Configuration;
import com.vimeo.networking.Vimeo;
import com.vimeo.networking.Vimeo.LogLevel;
import com.vimeo.networking.VimeoClient;

/**
 * Created by kylevenn on 1/27/16.
 */
public class TestApp extends Application {

    private static final String SCOPE = "private public create edit delete interact";

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        AccountPreferenceManager.initializeInstance(mContext);

        // <editor-fold desc="Vimeo API Library Initialization">
        // The values file is left out of git, so you'll have to provide your own id and secret
        String clientId = getString(R.string.client_id);
        String clientSecret = getString(R.string.client_secret);
        String codeGrantRedirectUri = getString(R.string.deeplink_redirect_scheme) + "://" +
                                      getString(R.string.deeplink_redirect_host);
        TestAccountStore testAccountStore = new TestAccountStore(this.getApplicationContext());
        Configuration.Builder configBuilder =
                new Configuration.Builder(Vimeo.VIMEO_BASE_URL_STRING, clientId, clientSecret, SCOPE,
                                          testAccountStore, new AndroidGsonDeserializer());
        configBuilder.setCacheDirectory(this.getCacheDir())
                .setUserAgentString(getUserAgentString(this)).setDebugLogger(new NetworkingLogger())
                // Used for oauth flow
                .setCodeGrantRedirectUri(codeGrantRedirectUri);

        if (/*isDebugBuild*/false) {
            // Disable cert pinning if debugging (so we can intercept packets)
            configBuilder.enableCertPinning(false);
            configBuilder.setLogLevel(LogLevel.VERBOSE);
        }
        VimeoClient.initialize(configBuilder.build());
        // </editor-fold>
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static String getUserAgentString(Context context) {
        String packageName = context.getPackageName();

        String version = "unknown";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            System.out.println("Unable to get packageInfo: " + e.getMessage());
        }

        String deviceManufacturer = Build.MANUFACTURER;
        String deviceModel = Build.MODEL;
        String deviceBrand = Build.BRAND;

        String versionString = Build.VERSION.RELEASE;
        String versionSDKString = String.valueOf(Build.VERSION.SDK_INT);

        return packageName + " (" + deviceManufacturer + ", " + deviceModel + ", " + deviceBrand +
               ", " + "Android " + versionString + "/" + versionSDKString + " Version " + version +
               ")";
    }
}
