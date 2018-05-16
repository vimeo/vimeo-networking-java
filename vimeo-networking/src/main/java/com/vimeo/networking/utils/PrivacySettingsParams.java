package com.vimeo.networking.utils;

import com.vimeo.networking.Vimeo;
import com.vimeo.networking.model.Privacy;

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
     * @param commentValue a value of {@link Privacy.CommentValue}.
     *
     * @return An instance of {@link PrivacySettingsParams}.
     */
    public PrivacySettingsParams comments(@NotNull final Privacy.CommentValue commentValue) {
        params.put(Vimeo.PARAMETER_VIDEO_COMMENTS, commentValue);
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
     * @param privacyEmbedType a value of {@link Privacy.EmbedValue}.
     *
     * @return An instance of {@link PrivacySettingsParams}.
     */
    public PrivacySettingsParams embed(@NotNull final Privacy.EmbedValue privacyEmbedType) {
        params.put(Vimeo.PARAMETER_VIDEO_EMBED, privacyEmbedType);
        return this;
    }

    /**
     * Set privacy setting to view a video.
     *
     * @param privacyViewType a value of {@link Privacy.ViewValue}.
     *
     * @return An instance of {@link PrivacySettingsParams}.
     */
    public PrivacySettingsParams view(@NotNull final Privacy.ViewValue privacyViewType) {
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
