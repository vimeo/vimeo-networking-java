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

import java.util.Arrays;

/**
 * An object returned from the /configs endpoint to specify how Facebook login should be configured.
 * <p>
 * Created by vennk on 5/20/15.
 */
public class FacebookConfiguration {

    @SerializedName("required_scopes")
    protected String[] mRequiredScopes;

    public void setRequiredScopes(String[] requiredScopes) {
        mRequiredScopes = requiredScopes;
    }

    public String[] getRequiredScopes() {
        return mRequiredScopes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FacebookConfiguration that = (FacebookConfiguration) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.deepEquals(mRequiredScopes, that.mRequiredScopes);
    }

    @Override
    public int hashCode() {
        return mRequiredScopes != null ? Arrays.hashCode(mRequiredScopes) : 0;
    }
}
