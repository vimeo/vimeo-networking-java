package com.vimeo.networking.utils;

import com.vimeo.networking.Vimeo;
import com.vimeo.networking.model.Privacy;
import com.vimeo.networking.model.Privacy.PrivacyCommentValue;
import com.vimeo.networking.model.Privacy.PrivacyEmbedValue;
import com.vimeo.networking.model.Privacy.PrivacyViewValue;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder for setting privacy params for a video.
 *
 * Created by Mohit Sarveiya on 3/20/18.
 */
public final class PrivacySettingsParams {

    private final Map<String, Object> params = new HashMap<>();

    /**
     * Set privacy setting for commenting on a video.
     *
     * @param privacyCommentValue a value of {@link PrivacyCommentValue}.
     *
     * @return An instance of {@link PrivacySettingsParams}.
     */
    public PrivacySettingsParams comments(@NotNull final Privacy.PrivacyCommentValue privacyCommentValue) {
        params.put(Vimeo.PARAMETER_VIDEO_COMMENTS, privacyCommentValue);
        return this;
    }

    /**
     * Set privacy setting for downloading on a video.
     *
     * @param download set {@code true} if video could be downloaded, otherwise {@code false}.
     *
     * @return An instance of {@link PrivacySettingsParams}.
     */
    public PrivacySettingsParams download(final boolean download) {
        params.put(Vimeo.PARAMETER_VIDEO_DOWNLOAD, download);
        return this;
    }

    /**
     * Set privacy setting for adding a video to a collection.
     *
     * @param add set {@code true} if video could be added to a collection, otherwise {@code false}.
     *
     * @return An instance of {@link PrivacySettingsParams}.
     */
    public PrivacySettingsParams addToCollections(final boolean add) {
        params.put(Vimeo.PARAMETER_VIDEO_ADD, add);
        return this;
    }

    /**
     * Set privacy setting to embed a video.
     *
     * @param privacyEmbedType a value of {@link PrivacyEmbedValue}.
     *
     * @return An instance of {@link PrivacySettingsParams}.
     */
    public PrivacySettingsParams embed(@NotNull final Privacy.PrivacyEmbedValue privacyEmbedType) {
        params.put(Vimeo.PARAMETER_VIDEO_EMBED, privacyEmbedType);
        return this;
    }

    /**
     * Set privacy setting to view a video.
     *
     * @param privacyViewType a value of {@link PrivacyViewValue}.
     *
     * @return An instance of {@link PrivacySettingsParams}.
     */
    public PrivacySettingsParams view(@NotNull final Privacy.PrivacyViewValue privacyViewType) {
        params.put(Vimeo.PARAMETER_VIDEO_VIEW, privacyViewType);
        return this;
    }

    /**
     * @return a map of all the privacy settings set.
     */
    @NotNull
    public Map<String, Object> getParams() {
        return new HashMap<>(params);
    }

}
