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

import com.vimeo.stag.GsonAdapterKey;

import org.jetbrains.annotations.Nullable;

/**
 * A model representing pin code information to be used in association with authorization.
 * Created by rigbergh on 6/16/16.
 */
@SuppressWarnings("unused")
public class PinCodeInfo {

    @Nullable
    @GsonAdapterKey("device_code")
    public String mDeviceCode;

    @Nullable
    @GsonAdapterKey("user_code")
    public String mUserCode;

    @Nullable
    @GsonAdapterKey("authorize_link")
    public String mAuthorizeLink;

    @Nullable
    @GsonAdapterKey("activate_link")
    public String mActivateLink;

    @GsonAdapterKey("expires_in")
    public int expiresIn;

    @GsonAdapterKey("interval")
    public int interval;

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
        return expiresIn;
    }

    public int getInterval() {
        return interval;
    }
}
