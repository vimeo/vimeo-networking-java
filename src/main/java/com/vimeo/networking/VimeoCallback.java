package com.vimeo.networking;

import com.vimeo.networking.model.error.VimeoError;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
        VimeoError vimeoError = null;
        try {
            vimeoError = (VimeoError) error.getBodyAs(VimeoError.class);
        } catch (Exception e) {
            e.printStackTrace();
            vimeoError = new VimeoError();
        }
        vimeoError.setRetrofitError(error);
        failure(vimeoError);
    }
}
