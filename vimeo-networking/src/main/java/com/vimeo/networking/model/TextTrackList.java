package com.vimeo.networking.model;

import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

/**
 * List of text tracks for a video. Each text track in the list
 * represent a language.
 *
 * Created by Mohit Sarveiya on 6/29/17.
 */
@UseStag(FieldOption.SERIALIZED_NAME)
@SuppressWarnings("unused")
public class TextTrackList extends BaseResponseList<TextTrack> {

    private static final long serialVersionUID = -7763182113197230457L;

    @Override
    public Class<TextTrack> getModelClass() {
        return TextTrack.class;
    }
}
