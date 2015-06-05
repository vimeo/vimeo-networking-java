package com.vimeo.networking.model.error;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Header;

/**
 * Created by zetterstromk on 5/27/15.
 */
public class VimeoError extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -5252307626841557962L;

    private static final String AUTHENTICATION_HEADER = "WWW-Authenticate";
    private static final String AUTHENTICATION_TOKEN_ERROR = "Bearer error=\"invalid_token\"";

    private RetrofitError retrofitError;

    @SerializedName("error")
    private String errorMessage;
    @SerializedName("link")
    private String link;
    @SerializedName("developer_message")
    private String developerMessage;
    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("invalid_parameters")
    private JsonObject invalidParameters;

    public VimeoError() {
    }

    public VimeoError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public RetrofitError getRetrofitError() {
        return retrofitError;
    }

    public void setRetrofitError(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return this.link;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getDeveloperMessage() {
        return this.developerMessage;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


    public String getErrorCode() {
        return this.errorCode;
    }

    public void setInvalidParameters(JsonObject invalidParameters) {
        this.invalidParameters = invalidParameters;
    }

    public JsonObject getInvalidParameters() {
        return this.invalidParameters;
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
