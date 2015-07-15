package com.vimeo.networking.model.error;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

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
    private ErrorCode errorCode;
    @SerializedName("invalid_parameters")
    private List<InvalidParameter> invalidParameters;

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

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode == null ? ErrorCode.DEFAULT : this.errorCode;
    }

    public void setInvalidParameters(List<InvalidParameter> invalidParameters) {
        this.invalidParameters = invalidParameters;
    }

    public List<InvalidParameter> getInvalidParameters() {
        return this.invalidParameters;
    }

    /**
     * Returns the first invalid parameter in the parameter list
     * @return First InvalidParameter in the invalid parameters array
     */
    @Nullable
    public InvalidParameter getInvalidParameter() {
        return invalidParameters != null && invalidParameters.size() > 0 ? this.invalidParameters
                .get(0) : null;
    }

    public boolean isServiceUnavailable() {
        return (retrofitError.getKind() == RetrofitError.Kind.HTTP) &&
               (retrofitError.getResponse().getStatus() == 503);
    }

    public boolean isForbiddenError() {
        return (retrofitError.getKind() == RetrofitError.Kind.HTTP) &&
               (retrofitError.getResponse().getStatus() == 403);
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

    public void addInvalidParameter(String field, ErrorCode code, String developerMessage) {
        InvalidParameter invalidParameter = new InvalidParameter(field, code, developerMessage);
        if (this.invalidParameters == null) {
            invalidParameters = new ArrayList<>();
        }
        this.invalidParameters.add(invalidParameter);
    }
}
