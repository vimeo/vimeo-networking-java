package com.vimeo.networking.model.appconfiguration;

import com.vimeo.networking.VimeoClient;

/**
 * Created by vennk on 5/20/15.
 */
public class ApiConfiguration {

    // Default host so if not set (from no internet connection) then we still have a base url
    public String host = VimeoClient.VIMEO_BASE_URL_STRING;
}
