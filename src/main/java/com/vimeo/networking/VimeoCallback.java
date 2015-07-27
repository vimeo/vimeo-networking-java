package com.vimeo.networking;

import com.vimeo.networking.model.error.VimeoError;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Created by zetterstromk on 5/27/15.
 * <p/>
 * This class allows intercepting and analyzing of the responses for errors and invalid tokens
 */
public abstract class VimeoCallback<T> implements Callback<T> {

    public abstract void success(T t, VimeoResponse response);

    public abstract void failure(VimeoError error);

    @Override
    public void success(T t, Response response) {
        success(t, new VimeoResponse(response));
    }

    @Override
    public void failure(RetrofitError error) {
    // Useful code for debugging the raw response [KV]
        String json = "";
        if (error != null && error.getResponse() != null && error.getResponse().getBody() != null) {
            json = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());
        }

        VimeoError vimeoError = null;
        try {
            vimeoError = (VimeoError) error.getBodyAs(VimeoError.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (vimeoError == null) {
            vimeoError = new VimeoError();
        }
        vimeoError.setRetrofitError(error);
        failure(vimeoError);
    }
}
