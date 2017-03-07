/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Vimeo
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

package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

/**
 * A model representing pin code information to be used in association with authorization.
 * Created by rigbergh on 6/16/16.
 */
@SuppressWarnings("unused")
@UseStag
public class PinCodeInfo {

    @Nullable
    @SerializedName("device_code")
    protected String mDeviceCode;

    @Nullable
    @SerializedName("user_code")
    protected String mUserCode;

    @Nullable
    @SerializedName("authorize_link")
    protected String mAuthorizeLink;

    @Nullable
    @SerializedName("activate_link")
    protected String mActivateLink;

    @SerializedName("expires_in")
    protected int mExpiresIn;

    @SerializedName("interval")
    protected int mInterval;

    @Nullable
    public String getDeviceCode() {
        return mDeviceCode;
    }

    @Nullable
    public String getUserCode() {
        return mUserCode;
    }

    @Nullable
    public String getAuthorizeLink() {
        return mAuthorizeLink;
    }

    @Nullable
    public String getActivateLink() {
        return mActivateLink;
    }

    public int getExpiresIn() {
        return mExpiresIn;
    }

    public int getInterval() {
        return mInterval;
    }
}
