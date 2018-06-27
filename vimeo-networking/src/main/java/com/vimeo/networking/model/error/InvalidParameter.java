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
import com.vimeo.networking.utils.VimeoNetworkUtil;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Similar to {@link VimeoError} object, this holds error codes/error messages relevant to a specific invalid field.
 * <p>
 * Example: If an invalid email is passed to the login call (doesn't include an '@' symbol), there will be an
 * instance of this object in the {@link VimeoError#mInvalidParameters} list of the {@link VimeoError} response with
 * {@link InvalidParameter#mField} set to "email"
 * <p>
 * Created by kylevenn on 7/15/15.
 */
@SuppressWarnings("unused")
@UseStag(FieldOption.SERIALIZED_NAME)
public class InvalidParameter implements Serializable {

    private static final long serialVersionUID = -2231104595208701561L;

    @SerializedName("field")
    protected String mField;

    @SerializedName("error_code")
    private String mRawErrorCode;

    @NotNull
    private ErrorCode mErrorCode = ErrorCode.DEFAULT;

    @SerializedName("user_message")
    protected String mUserMessage;

    @SerializedName("developer_message")
    protected String mDeveloperMessage;

    public InvalidParameter() {}

    public InvalidParameter(String field, ErrorCode errorCode, String developerMessage) {
        this.mField = field;
        setErrorCode(errorCode);
        this.mDeveloperMessage = developerMessage;
    }

    /**
     * Set the raw error code, also sets the error code enum as a side effect.
     *
     * @param rawErrorCode the raw error code to set.
     */
    protected void setRawErrorCode(@Nullable String rawErrorCode) {
        mRawErrorCode = rawErrorCode;
        final ErrorCode errorCode = VimeoNetworkUtil.getGson().fromJson(mRawErrorCode, ErrorCode.class);
        mErrorCode = errorCode != null ? errorCode : ErrorCode.DEFAULT;
    }

    /**
     * Set the error code enum, also sets the raw error code string as a side effect.
     *
     * @param errorCode the error code enum to set.
     */
    private void setErrorCode(@NotNull ErrorCode errorCode) {
        mErrorCode = errorCode;
        final String json = VimeoNetworkUtil.getGson().toJson(errorCode);
        mRawErrorCode = json.replaceAll("^\"|\"$", "");
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
     * Returns the raw error code, may be null.
     *
     * @return the raw error code as a nullable string.
     */
    @Nullable
    public String getRawErrorCode() {
        return mRawErrorCode;
    }

    /**
     * Returns the field.
     *
     * @return the field as a string.
     */
    public String getField() {
        return mField;
    }

    /**
     * Returns the user message.
     *
     * @return the user message as a string.
     */
    public String getUserMessage() {
        return mUserMessage;
    }

    /**
     * Returns the developer message.
     *
     * @return the developer message as a string.
     */
    public String getDeveloperMessage() {
        return mDeveloperMessage;
    }
}
