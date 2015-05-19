package com.vimeo.networking;

/**
 * Created by alfredhanssen on 4/12/15.
 */
public interface AuthCallback {

    public void success();

    public void failure(Error error);
}
