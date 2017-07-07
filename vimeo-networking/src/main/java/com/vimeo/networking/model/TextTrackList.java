package com.vimeo.networking.model;

import com.vimeo.stag.UseStag;

/**
 * Created by Mohit Sarveiya on 6/29/17.
 */
@UseStag
@SuppressWarnings("unused")
public class TextTrackList extends BaseResponseList<TextTrack> {

    private static final long serialVersionUID = -7763182113197230457L;

    @Override
    public Class<TextTrack> getModelClass() {
        return TextTrack.class;
    }
}
