package com.vimeo.networking.model.search;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Search suggestions for videos. These suggestions can be ranked according to
 * their score - the higher the score the more relevant the suggestion.
 * <p>
 * Created by zetterstromk on 2/21/17.
 */
@UseStag(FieldOption.SERIALIZED_NAME)
public final class VideoSuggestion extends BaseSuggestion implements Serializable {

    private static final long serialVersionUID = -6047771941064977527L;

    @Nullable
    @SerializedName("meta")
    VideoSuggestionMetadata mMetadata;

    /**
     * Returns the score associated with this suggestion. The higher the score
     * the more relevant the suggestion.
     */
    public int getScore() {
        return mMetadata != null ? mMetadata.mScore : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        VideoSuggestion that = (VideoSuggestion) o;

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
        return "VideoSuggestion{" +
               "mText='" + mText + '\'' +
               ", mMetadata=" + mMetadata +
               '}';
    }

    @UseStag(FieldOption.SERIALIZED_NAME)
    public final static class VideoSuggestionMetadata implements Serializable {

        private static final long serialVersionUID = -5853739822133424630L;

        @SerializedName("score")
        int mScore;
    }
}
