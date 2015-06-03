package com.vimeo.networking.model.error;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Header;

/**
 * Created by zetterstromk on 5/27/15.
 */
public class VimeoError extends RuntimeException {

    private static final String AUTHENTICATION_HEADER = "WWW-Authenticate";
    private static final String AUTHENTICATION_TOKEN_ERROR = "Bearer error=\"invalid_token\"";
    private RetrofitError retrofitError;
    private VimeoErrorBody errorBody;

    public VimeoError(RetrofitError error) {
        setRetrofitError(error);
    }

    public VimeoError(VimeoErrorBody body) {
        setErrorBody(body);
    }

    public VimeoError(String errorMessage) {
        if (this.errorBody == null) {
            this.errorBody = new VimeoErrorBody(errorMessage);
        }
    }

    public RetrofitError getRetrofitError() {
        return retrofitError;
    }

    public void setRetrofitError(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
        try {
            this.errorBody = (VimeoErrorBody) this.retrofitError.getBodyAs(VimeoErrorBody.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setErrorBody(VimeoErrorBody body) {
        this.errorBody = body;
    }

    public String getLink() {
        if (this.errorBody != null) {
            return this.errorBody.link;
        }
        return null;
    }

    public String getErrorMessage() {
        if (this.errorBody != null) {
            return this.errorBody.errorMessage;
        }
        return null;
    }

    public String getDeveloperMessage() {
        if (this.errorBody != null) {
            return this.errorBody.developerMessage;
        }
        return null;
    }

    public String getErrorCode() {
        if (this.errorBody != null) {
            return this.errorBody.errorCode;
        }
        return null;
    }

    public JsonObject getInvalidParameters() {
        if (this.errorBody != null) {
            return this.errorBody.invalidParameters;
        }
        return null;
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
