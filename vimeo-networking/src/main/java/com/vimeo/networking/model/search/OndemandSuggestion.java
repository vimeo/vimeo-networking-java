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
public class OndemandSuggestion extends BaseSuggestion implements Serializable {

    private static final long serialVersionUID = -1176546228110567452L;

    @Nullable
    @SerializedName("meta")
    OndemandSuggestionMetadata mMetadata;

    /**
     * Return the {@link PictureCollection} associated with this VOD.
     */
    @Nullable
    public PictureCollection getPictures() {
        return mMetadata != null ? mMetadata.mPoster : null;
    }

    /**
     * Returns the url representative of this VOD.
     */
    @Nullable
    public String getOnDemandUrl() {
        return mMetadata != null ? mMetadata.mTargetUrl : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        OndemandSuggestion that = (OndemandSuggestion) o;

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
        return "OndemandSuggestion{" +
               "mText='" + mText + '\'' +
               ", mMetadata=" + mMetadata +
               '}';
    }

    @UseStag(FieldOption.SERIALIZED_NAME)
    static class OndemandSuggestionMetadata implements Serializable {

        private static final long serialVersionUID = 7538947801290601850L;

        @Nullable
        @SerializedName("target_url")
        String mTargetUrl;

        @Nullable
        @SerializedName("poster")
        PictureCollection mPoster;

    }
}
