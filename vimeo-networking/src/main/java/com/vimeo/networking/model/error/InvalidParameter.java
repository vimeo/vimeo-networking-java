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

/**
 * Similar to {@link VimeoError} object, this holds error codes/error messages relevant to a specific invalid field.
 * <p/>
 * Example: If an invalid email is passed to the login call (doesn't include an '@' symbol), there will be an
 * instance of this object in the {@link VimeoError#invalidParameters} list of the {@link VimeoError} response with
 * {@link InvalidParameter#field} set to "email"
 * <p/>
 * Created by kylevenn on 7/15/15.
 */
public class InvalidParameter {

    @SerializedName("field")
    private String field;
    @SerializedName("error_code")
    private ErrorCode errorCode;
    @SerializedName("user_message")
    private String userMessage;
    @SerializedName("developer_message")
    private String developerMessage;

    public InvalidParameter(String field, ErrorCode errorCode, String developerMessage) {
        this.field = field;
        this.errorCode = errorCode;
        this.developerMessage = developerMessage;
    }

    public String getField() {
        return field;
    }

    public ErrorCode getErrorCode() {
        return errorCode == null ? ErrorCode.DEFAULT : errorCode;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }
}
