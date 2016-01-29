package com.vimeo.networking.logging;

/**
 * Created by kylevenn on 10/9/15.
 */
public interface DebugLoggerInterface {

    void e(String error);

    void e(String error, Exception exception);

    void d(String debug);

    void v(String verbose);
}
