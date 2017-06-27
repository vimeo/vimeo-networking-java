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

package com.vimeo.networking.model.appconfiguration;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.Vimeo;

/**
 * An object returned from the /configs endpoint to specify the host base url for api requests.
 * <p>
 * Created by kylevenn on 5/20/15.
 */
public class ApiConfiguration {

    // Default host so if not set (from no internet connection) then we still have a base url
    @SerializedName("host")
    protected String mHost = Vimeo.VIMEO_BASE_URL_STRING;

    public void setHost(String host) {
        mHost = host;
    }

    public String getHost() {
        return mHost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApiConfiguration that = (ApiConfiguration) o;

        return !(mHost != null ? !mHost.equals(that.mHost) : that.mHost != null);

    }

    @Override
    public int hashCode() {
        return mHost != null ? mHost.hashCode() : 0;
    }
}
