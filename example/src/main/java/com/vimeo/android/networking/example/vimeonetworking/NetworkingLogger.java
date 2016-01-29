package com.vimeo.android.networking.example.vimeonetworking;

import android.util.Log;

import com.vimeo.networking.logging.DebugLoggerInterface;

/**
 * Logger delegate for handling logs coming from the Networking library
 * <p/>
 * Created by kylevenn on 1/27/16.
 */
public class NetworkingLogger implements DebugLoggerInterface {

    private static final String TEST_APP = "~NET TEST APP~";

    @Override
    public void e(String error) {
        Log.e(TEST_APP, error);
    }

    @Override
    public void e(String error, Exception exception) {
        Log.e(TEST_APP, error, exception);
    }

    @Override
    public void d(String debug) {
        Log.d(TEST_APP, debug);
    }

    @Override
    public void v(String verbose) {
        Log.v(TEST_APP, verbose);
    }
}
