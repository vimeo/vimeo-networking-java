package com.vimeo.networking.logging;

/**
 * Created by kylevenn on 10/9/15.
 */
public class NetworkingLogger implements NetworkingLoggerInterface {

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
    public void i(String info) {
        System.out.println(info);
    }
}
