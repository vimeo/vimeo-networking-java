/*
 * Copyright (c) 2017 Vimeo (https://vimeo.com)
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

package com.vimeo.networking.model.search;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A response sent from the API for search/autocomplete suggestions.
 * <p>
 * Created by zetterstromk on 2/21/17.
 */
@UseStag(FieldOption.SERIALIZED_NAME)
public class SuggestionResponse implements Serializable {

    private static final long serialVersionUID = -3474410244242185856L;

    @Nullable
    @SerializedName("ondemand_title")
    ArrayList<TvodSuggestion> mTvodSuggestions;

    @Nullable
    @SerializedName("video")
    ArrayList<VideoSuggestion> mVideoSuggestions;

    @Nullable
    public ArrayList<TvodSuggestion> getTvodSuggestionList() {
        return mTvodSuggestions;
    }

    @Nullable
    public ArrayList<VideoSuggestion> getVideoSuggestions() {
        return mVideoSuggestions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        SuggestionResponse that = (SuggestionResponse) o;

        if (mTvodSuggestions != null ? !mTvodSuggestions.equals(that.mTvodSuggestions) : that.mTvodSuggestions != null) {
            return false;
        }
        return mVideoSuggestions != null ? mVideoSuggestions.equals(that.mVideoSuggestions) : that.mVideoSuggestions == null;

    }

    @Override
    public int hashCode() {
        int result = mTvodSuggestions != null ? mTvodSuggestions.hashCode() : 0;
        result = 31 * result + (mVideoSuggestions != null ? mVideoSuggestions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SuggestionResponse{" +
               "mTvodSuggestions=" + mTvodSuggestions +
               ", mVideoSuggestions=" + mVideoSuggestions +
               '}';
    }
}
