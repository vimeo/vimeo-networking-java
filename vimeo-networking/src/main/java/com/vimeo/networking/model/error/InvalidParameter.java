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
import com.vimeo.stag.UseStag;

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
@UseStag
public class InvalidParameter {

    @SerializedName("field")
    protected String mField;

    @SerializedName("error_code")
    protected ErrorCode mErrorCode;

    @SerializedName("user_message")
    protected String mUserMessage;

    @SerializedName("developer_message")
    protected String mDeveloperMessage;

    public InvalidParameter() {}

    public InvalidParameter(String field, ErrorCode errorCode, String developerMessage) {
        this.mField = field;
        this.mErrorCode = errorCode;
        this.mDeveloperMessage = developerMessage;
    }

    public String getField() {
        return mField;
    }

    public ErrorCode getErrorCode() {
        return mErrorCode == null ? ErrorCode.DEFAULT : mErrorCode;
    }

    public String getUserMessage() {
        return mUserMessage;
    }

    public String getDeveloperMessage() {
        return mDeveloperMessage;
    }
}
