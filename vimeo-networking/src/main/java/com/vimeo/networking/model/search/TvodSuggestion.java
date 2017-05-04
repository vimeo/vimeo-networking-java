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
import com.vimeo.networking.model.PictureCollection;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Search suggestions for ondemand titles
 * <p>
 * Created by zetterstromk on 2/21/17.
 */
@UseStag(FieldOption.SERIALIZED_NAME)
public final class TvodSuggestion extends BaseSuggestion implements Serializable {

    private static final long serialVersionUID = -1176546228110567452L;

    @Nullable
    @SerializedName("meta")
    TvodSuggestionMetadata mMetadata;

    /**
     * Return the {@link PictureCollection} associated with this TVOD.
     */
    @Nullable
    public PictureCollection getPictures() {
        return mMetadata != null ? mMetadata.mPoster : null;
    }

    /**
     * Returns the url representative of this TVOD.
     */
    @Nullable
    public String getTvodUrl() {
        return mMetadata != null ? mMetadata.mTargetUrl : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        TvodSuggestion that = (TvodSuggestion) o;

        if (!mText.equals(that.mText)) { return false; }
        return mMetadata != null ? mMetadata.equals(that.mMetadata) : that.mMetadata == null;

    }

    @Override
    public int hashCode() {
        int result = mText.hashCode();
        result = 31 * result + (mMetadata != null ? mMetadata.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TvodSuggestion{" +
               "mText='" + mText + '\'' +
               ", mMetadata=" + mMetadata +
               '}';
    }

    @UseStag(FieldOption.SERIALIZED_NAME)
    public final static class TvodSuggestionMetadata implements Serializable {

        private static final long serialVersionUID = 7538947801290601850L;

        @Nullable
        @SerializedName("target_url")
        String mTargetUrl;

        @Nullable
        @SerializedName("poster")
        PictureCollection mPoster;

    }
}
