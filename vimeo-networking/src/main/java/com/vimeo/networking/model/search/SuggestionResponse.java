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
    ArrayList<OndemandSuggestion> mOndemandSuggestions;

    @Nullable
    @SerializedName("video")
    ArrayList<VideoSuggestion> mVideoSuggestions;

    @Nullable
    public ArrayList<OndemandSuggestion> getOndemandSuggestionList() {
        return mOndemandSuggestions;
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

        if (mOndemandSuggestions != null ? !mOndemandSuggestions.equals(that.mOndemandSuggestions) : that.mOndemandSuggestions != null) {
            return false;
        }
        return mVideoSuggestions != null ? mVideoSuggestions.equals(that.mVideoSuggestions) : that.mVideoSuggestions == null;

    }

    @Override
    public int hashCode() {
        int result = mOndemandSuggestions != null ? mOndemandSuggestions.hashCode() : 0;
        result = 31 * result + (mVideoSuggestions != null ? mVideoSuggestions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SuggestionResponse{" +
               "mOndemandSuggestions=" + mOndemandSuggestions +
               ", mVideoSuggestions=" + mVideoSuggestions +
               '}';
    }
}
