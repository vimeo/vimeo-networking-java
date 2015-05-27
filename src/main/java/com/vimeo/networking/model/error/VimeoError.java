package com.vimeo.networking.model.error;

import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Header;

/**
 * Created by zetterstromk on 5/27/15.
 */
public class VimeoError {

    private static final String AUTHENTICATION_HEADER = "WWW-Authenticate";
    private static final String AUTHENTICATION_TOKEN_ERROR = "Bearer error=\"invalid_token\"";
    private RetrofitError retrofitError;

    public VimeoError(RetrofitError error) {
        this.retrofitError = error;
    }

    public RetrofitError getRetrofitError() {
        return retrofitError;
    }

    public void setRetrofitError(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
    }

    public boolean isServiceUnavailable() {
        if ((retrofitError.getKind() == RetrofitError.Kind.HTTP) &&
            (retrofitError.getResponse().getStatus() == 503)) {
            return true;
        }
        return false;
    }

    public boolean isForbiddenError() {
        if ((retrofitError.getKind() == RetrofitError.Kind.HTTP) &&
            (retrofitError.getResponse().getStatus() == 403)) {
            return true;
        }
        return false;
    }

    public boolean isInvalidTokenError() {
        if ((retrofitError.getKind() == RetrofitError.Kind.HTTP) &&
            (retrofitError.getResponse().getStatus() == 401)) {
            List<Header> headers = retrofitError.getResponse().getHeaders();
            for (Header header : headers) {
                if (header.getName().equals(AUTHENTICATION_HEADER) &&
                    header.getValue().equals(AUTHENTICATION_TOKEN_ERROR)) {
                    return true;
                }
            }
        }
        return false;
    }

}
