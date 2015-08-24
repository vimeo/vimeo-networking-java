package com.vimeo.networking;

import retrofit.client.Response;

/**
 * Created by zetterstromk on 5/27/15.
 */
public class VimeoResponse {

    private Response response;

    public VimeoResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
