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

/**
 * An object returned from the /configs endpoint.
 * This is a way for the api to specify configuration for our application.
 * <p/>
 * Created by kylevenn on 5/20/15.
 */
public class AppConfiguration {

    @SerializedName("facebook")
    protected FacebookConfiguration mFacebook;

    @SerializedName("api")
    protected ApiConfiguration mApi;

    @SerializedName("features")
    protected FeaturesConfiguration mFeatures;

    public void setFacebook(FacebookConfiguration facebook) {
        mFacebook = facebook;
    }

    public void setApi(ApiConfiguration api) {
        mApi = api;
    }

    public void setFeatures(FeaturesConfiguration features) {
        mFeatures = features;
    }

    public FacebookConfiguration getFacebook() {
        return mFacebook;
    }

    public ApiConfiguration getApi() {
        return mApi;
    }

    public FeaturesConfiguration getFeatures() {
        return mFeatures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppConfiguration that = (AppConfiguration) o;

        if (mFacebook != null ? !mFacebook.equals(that.mFacebook) : that.mFacebook != null) {
            return false;
        }
        if (mApi != null ? !mApi.equals(that.mApi) : that.mApi != null) {
            return false;
        }
        return !(mFeatures != null ? !mFeatures.equals(that.mFeatures) : that.mFeatures != null);

    }

    @Override
    public int hashCode() {
        int result = mFacebook != null ? mFacebook.hashCode() : 0;
        result = 31 * result + (mApi != null ? mApi.hashCode() : 0);
        result = 31 * result + (mFeatures != null ? mFeatures.hashCode() : 0);
        return result;
    }
}
