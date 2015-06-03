package com.vimeo.networking;

import com.vimeo.networking.model.error.VimeoError;

/**
 * Callback used specifically for authorization
 *
 * Created by alfredhanssen on 4/12/15.
 */
public interface AuthCallback {

    public void success();

    public void failure(VimeoError error);
}
