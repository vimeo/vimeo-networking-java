package com.vimeo.networking.utils;

import com.vimeo.networking.Vimeo;
import com.vimeo.networking.model.Privacy;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder for setting privacy params for a video.
 *
 * Created by Mohit Sarveiya on 3/20/18.
 */
public final class PrivacySettingsParams {

    private final Map<String, Object> params = new HashMap<>();

    public PrivacySettingsParams comments(final Privacy.PrivacyCommentValue privacyCommentValue) {
        params.put(Vimeo.PARAMETER_VIDEO_COMMENTS, privacyCommentValue);
        return this;
    }

    public PrivacySettingsParams download(final boolean download) {
        params.put(Vimeo.PARAMETER_VIDEO_DOWNLOAD, download);
        return this;
    }

    public PrivacySettingsParams addToCollections(final boolean add) {
        params.put(Vimeo.PARAMETER_VIDEO_ADD, add);
        return this;
    }

    public PrivacySettingsParams embed(final Privacy.PrivacyEmbedValue privacyEmbedType) {
        params.put(Vimeo.PARAMETER_VIDEO_EMBED, privacyEmbedType);
        return this;
    }

    public PrivacySettingsParams view(final Privacy.PrivacyViewValue privacyViewType) {
        params.put(Vimeo.PARAMETER_VIDEO_VIEW, privacyViewType);
        return this;
    }

    public Map<String, Object> getParams() {
        return params;
    }

}
