/*
 * Copyright (c) 2015 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.vimeo.networking.model.error;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.Vimeo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * This class represents an error response from the Vimeo API. It holds useful getters to understand
 * why your request might have failed.
 * <p/>
 * Created by zetterstromk on 5/27/15.
 */
public class VimeoError extends RuntimeException {

    private static final long serialVersionUID = -5252307626841557962L;

    private static final String AUTHENTICATION_HEADER = "WWW-Authenticate";
    private static final String AUTHENTICATION_TOKEN_ERROR = "Bearer error=\"invalid_token\"";

    private Response response;

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

    private Exception exception;
    private int httpStatusCode = Vimeo.NOT_FOUND;

    private boolean isCanceledError;

    public VimeoError() {
    }

    public VimeoError(String errorMessage) {
        this.developerMessage = errorMessage;
    }

    public VimeoError(String errorMessage, Exception exception) {
        this.developerMessage = errorMessage;
        this.exception = exception;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
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
        if (this.developerMessage == null || this.developerMessage.isEmpty()) {
            return this.errorMessage;
        }
        return this.developerMessage;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @NotNull
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
     * Returns the first invalid parameter in the {@link #invalidParameters} list
     * @return First InvalidParameter in the invalid parameters array
     */
    @Nullable
    public InvalidParameter getInvalidParameter() {
        return invalidParameters != null && !invalidParameters.isEmpty() ? invalidParameters.get(0) : null;
    }

    /**
     * Returns the error code for the first invalid parameter in the {@link #invalidParameters} list
     * or null if none exists.
     */
    @Nullable
    public ErrorCode getInvalidParameterErrorCode() {
        return getInvalidParameter() != null ? getInvalidParameter().getErrorCode() : null;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public int getHttpStatusCode() {
        if (response != null) {
            return response.code();
        }
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * True if the error was from poor connectivity, closed sockets, or any other issue with the networking
     * layer of the request.
     * @return {@link #isNetworkError}
     */
    public boolean isNetworkError() {
        // Response will be null if the VimeoCallback#onFailure was called (which will be due to issues
        // in the networking layer 2/17/16 [KV]
        return !isCanceledError && response == null;
    }

    public void setIsCanceledError(boolean isCanceledError) {
        this.isCanceledError = isCanceledError;
    }

    public boolean isCanceledError() {
        return isCanceledError;
    }

    public boolean isServiceUnavailable() {
        return (response != null) && (response.code() == 503);
    }

    public boolean isForbiddenError() {
        return (response != null) && (response.code() == 403);
    }

    public boolean isInvalidTokenError() {
        if ((response != null) && (response.code() == 401)) {
            List<String> headers = response.headers().values(AUTHENTICATION_HEADER);
            for (String header : headers) {
                if (header.equals(AUTHENTICATION_TOKEN_ERROR)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPasswordRequiredError() {
        if (((getInvalidParameter() != null) &&
             (getInvalidParameter().getErrorCode() == ErrorCode.INVALID_INPUT_VIDEO_NO_PASSWORD)) ||
            ((getInvalidParameter() != null) &&
             (getInvalidParameter().getErrorCode() == ErrorCode.INVALID_INPUT_VIDEO_PASSWORD_MISMATCH)) ||
            (getErrorCode() == ErrorCode.INVALID_INPUT_VIDEO_NO_PASSWORD) ||
            (getErrorCode() == ErrorCode.INVALID_INPUT_VIDEO_PASSWORD_MISMATCH)) {
            return true;
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

    /**
     * @return the most useful error message string or <code>""</code> if none available.
     */
    @NotNull
    public String getLogString() {
        if (getDeveloperMessage() != null) {
            return getDeveloperMessage();
        } else if (this.errorMessage != null) {
            return this.errorMessage;
        } else if (exception != null && exception.getMessage() != null) {
            return "Exception: " + exception.getMessage();
        } else if (getErrorCode() != ErrorCode.DEFAULT) {
            return "Error Code " + getErrorCode();
        } else if (getHttpStatusCode() != Vimeo.NOT_FOUND) {
            return "HTTP Status Code: " + getHttpStatusCode();
        }
        return "";
    }
}
