package com.vimeo.networking.model;

import com.vimeo.stag.UseStag;

/**
 * A {@link BaseResponseList} of {@link Album} objects
 */
@SuppressWarnings("unused")
@UseStag
public class AlbumList extends BaseResponseList<Album> {

    private static final long serialVersionUID = -4131452961620018914L;

    @Override
    public Class<Album> getModelClass() {
        return Album.class;
    }
}
