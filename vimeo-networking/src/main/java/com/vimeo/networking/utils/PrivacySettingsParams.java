package com.vimeo.networking.utils;

import com.vimeo.networking.Vimeo;
import com.vimeo.networking2.enums.CommentPrivacyType;
import com.vimeo.networking2.enums.EmbedPrivacyType;
import com.vimeo.networking2.enums.ViewPrivacyType;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder for setting privacy params for a video.
 */
public final class PrivacySettingsParams {

    private final Map<String, Object> params = new HashMap<>();

    /**
     * Set privacy setting for commenting on a video.
     *
     * @param commentValue a value of {@link com.vimeo.networking2.enums.CommentPrivacyType}.
     * @return An instance of {@link PrivacySettingsParams}.
     */
    public PrivacySettingsParams comments(@NotNull final CommentPrivacyType commentValue) {
        params.put(Vimeo.PARAMETER_VIDEO_COMMENTS, commentValue);
        return this;
    }

    /**
     * Set privacy setting for downloading on a video.
     *
     * @param download set {@code true} if video could be downloaded, otherwise {@code false}.
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
     * @return An instance of {@link PrivacySettingsParams}.
     */
    public PrivacySettingsParams addToCollections(final boolean add) {
        params.put(Vimeo.PARAMETER_VIDEO_ADD, add);
        return this;
    }

    /**
     * Set privacy setting to embed a video.
     *
     * @param privacyEmbedType a value of {@link EmbedPrivacyType}.
     * @return An instance of {@link PrivacySettingsParams}.
     */
    public PrivacySettingsParams embed(@NotNull final EmbedPrivacyType privacyEmbedType) {
        params.put(Vimeo.PARAMETER_VIDEO_EMBED, privacyEmbedType);
        return this;
    }

    /**
     * Set privacy setting to view a video.
     *
     * @param privacyViewType a value of {@link ViewPrivacyType}.
     * @return An instance of {@link PrivacySettingsParams}.
     */
    public PrivacySettingsParams view(@NotNull final ViewPrivacyType privacyViewType) {
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
