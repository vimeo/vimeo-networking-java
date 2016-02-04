package com.vimeo.networking.logging;

/**
 * Created by kylevenn on 10/9/15.
 */
public class DebugLogger implements DebugLoggerInterface {

    @Override
    public void e(String error) {
        System.out.println(error);
    }

    @Override
    public void e(String error, Exception exception) {
        System.out.println(error);
        exception.printStackTrace();
    }

    @Override
    public void d(String debug) {
        System.out.println(debug);
    }

    @Override
    public void v(String verbose) {
        System.out.println(verbose);
    }
}
