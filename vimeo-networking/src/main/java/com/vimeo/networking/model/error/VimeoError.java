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
import com.vimeo.networking.utils.VimeoNetworkUtil;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * This class represents an error response from the Vimeo API. It holds useful getters to understand
 * why your request might have failed.
 * <p>
 * Created by zetterstromk on 5/27/15.
 */
@SuppressWarnings("unused")
@UseStag(FieldOption.SERIALIZED_NAME)
public class VimeoError extends RuntimeException {

    private static final long serialVersionUID = -5252307626841557962L;

    private static final String AUTHENTICATION_HEADER = "WWW-Authenticate";
    private static final String AUTHENTICATION_TOKEN_ERROR = "Bearer error=\"invalid_token\"";

    private Response mResponse;

    @SerializedName("error")
    protected String mErrorMessage;

    @SerializedName("link")
    protected String mLink;

    @SerializedName("developer_message")
    protected String mDeveloperMessage;

    @SerializedName("error_code")
    private String mRawErrorCode;

    @NotNull
    private ErrorCode mErrorCode = ErrorCode.DEFAULT;

    @NotNull
    private LocalErrorCode mLocalErrorCode = LocalErrorCode.DEFAULT;

    @SerializedName("invalid_parameters")
    protected List<InvalidParameter> mInvalidParameters;

    private Exception mException;
    private int mHttpStatusCode = Vimeo.NOT_FOUND;

    private boolean mIsCanceledError;

    public VimeoError() {
    }

    public VimeoError(String errorMessage) {
        this.mDeveloperMessage = errorMessage;
    }

    public VimeoError(String errorMessage, Exception exception) {
        this.mDeveloperMessage = errorMessage;
        this.mException = exception;
    }

    public Response getResponse() {
        return mResponse;
    }

    public void setResponse(Response response) {
        this.mResponse = response;
    }

    public void setLink(String link) {
        this.mLink = link;
    }

    public String getLink() {
        return this.mLink;
    }

    public void setErrorMessage(String errorMessage) {
        this.mErrorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.mDeveloperMessage = developerMessage;
    }

    public String getDeveloperMessage() {
        if (this.mDeveloperMessage == null || this.mDeveloperMessage.isEmpty()) {
            return this.mErrorMessage;
        }
        return this.mDeveloperMessage;
    }

    /**
     * Return the raw error code string.
     *
     * @return the raw error code..
     */
    public String getRawErrorCode() {
        return mRawErrorCode;
    }

    /**
     * Sets the raw error code as well as the enumerated error code if the raw error code can be mapped to one of the
     * enum values.
     *
     * @param rawErrorCode the numerical error code causing this error.
     */
    protected void setRawErrorCode(String rawErrorCode) {
        mRawErrorCode = rawErrorCode;

        final ErrorCode errorCode = VimeoNetworkUtil.getGson().fromJson(mRawErrorCode, ErrorCode.class);
        mErrorCode = errorCode != null ? errorCode : ErrorCode.DEFAULT;
    }

    /**
     * Returns the error code. If no value was set, defaults to {@link ErrorCode#DEFAULT}.
     *
     * @return the error code.
     */
    @NotNull
    public ErrorCode getErrorCode() {
        return mErrorCode;
    }

    /**
     * Returns the local error code. If no value was set, defaults to {@link LocalErrorCode#DEFAULT}.
     *
     * @return the local error code.
     */
    @NotNull
    public LocalErrorCode getLocalErrorCode() {
        return mLocalErrorCode;
    }

    /**
     * Set the local error code enum.
     *
     * @param localErrorCode the local error code to use.
     */
    public void setLocalErrorCode(@NotNull LocalErrorCode localErrorCode) {
        mLocalErrorCode = localErrorCode;
    }

    public void setInvalidParameters(List<InvalidParameter> invalidParameters) {
        this.mInvalidParameters = invalidParameters;
    }

    public List<InvalidParameter> getInvalidParameters() {
        return this.mInvalidParameters;
    }

    /**
     * Returns the first invalid parameter in the {@link #mInvalidParameters} list
     *
     * @return First InvalidParameter in the invalid parameters array
     */
    @Nullable
    public InvalidParameter getInvalidParameter() {
        return mInvalidParameters != null && !mInvalidParameters.isEmpty() ? mInvalidParameters.get(0) : null;
    }

    /**
     * Returns the error code for the first invalid parameter in the {@link #mInvalidParameters} list
     * or null if none exists.
     */
    @Nullable
    public ErrorCode getInvalidParameterErrorCode() {
        return getInvalidParameter() != null ? getInvalidParameter().getErrorCode() : null;
    }

    public Exception getException() {
        return mException;
    }

    public void setException(Exception exception) {
        this.mException = exception;
    }

    public int getHttpStatusCode() {
        if (mResponse != null) {
            return mResponse.code();
        }
        return mHttpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.mHttpStatusCode = httpStatusCode;
    }

    /**
     * True if the error was from poor connectivity, closed sockets, or any other issue with the networking
     * layer of the request.
     *
     * @return {@link #isNetworkError}
     */
    public boolean isNetworkError() {
        // Response will be null if the VimeoCallback#onFailure was called (which will be due to issues
        // in the networking layer 2/17/16 [KV]
        return !mIsCanceledError && mResponse == null;
    }

    public void setIsCanceledError(boolean isCanceledError) {
        this.mIsCanceledError = isCanceledError;
    }

    public boolean isCanceledError() {
        return mIsCanceledError;
    }

    public boolean isServiceUnavailable() {
        return (mResponse != null) && (mResponse.code() == 503);
    }

    public boolean isForbiddenError() {
        return (mResponse != null) && (mResponse.code() == 403);
    }

    public boolean isInvalidTokenError() {
        if ((mResponse != null) && (mResponse.code() == 401)) {
            List<String> headers = mResponse.headers().values(AUTHENTICATION_HEADER);
            for (String header : headers) {
                if (header.equals(AUTHENTICATION_TOKEN_ERROR)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPasswordRequiredError() {
        InvalidParameter invalidParameter = getInvalidParameter();
        ErrorCode code = getErrorCode();
        return ((invalidParameter != null) &&
                (invalidParameter.getErrorCode() == ErrorCode.INVALID_INPUT_VIDEO_NO_PASSWORD)) ||
               ((invalidParameter != null) &&
                (invalidParameter.getErrorCode() == ErrorCode.INVALID_INPUT_VIDEO_PASSWORD_MISMATCH)) ||
               (code == ErrorCode.INVALID_INPUT_VIDEO_NO_PASSWORD) ||
               (code == ErrorCode.INVALID_INPUT_VIDEO_PASSWORD_MISMATCH);
    }

    public void addInvalidParameter(String field, ErrorCode code, String developerMessage) {
        InvalidParameter invalidParameter = new InvalidParameter(field, code, developerMessage);
        if (this.mInvalidParameters == null) {
            mInvalidParameters = new ArrayList<>();
        }
        this.mInvalidParameters.add(invalidParameter);
    }

    /**
     * @return the most useful error message string or <code>""</code> if none available.
     */
    @NotNull
    public String getLogString() {
        if (getDeveloperMessage() != null) {
            return getDeveloperMessage();
        } else if (this.mErrorMessage != null) {
            return this.mErrorMessage;
        } else if (mException != null && mException.getMessage() != null) {
            return "Exception: " + mException.getMessage();
        } else if (getErrorCode() != ErrorCode.DEFAULT) {
            return "Error Code " + getErrorCode();
        } else if (getHttpStatusCode() != Vimeo.NOT_FOUND) {
            return "HTTP Status Code: " + getHttpStatusCode();
        }
        return "";
    }
}
