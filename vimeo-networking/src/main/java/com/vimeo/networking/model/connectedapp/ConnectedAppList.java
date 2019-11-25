package com.vimeo.networking.model.connectedapp;

import com.vimeo.networking.model.BaseResponseList;
import com.vimeo.stag.UseStag;

/**
 * A {@link BaseResponseList} of {@link ConnectedApp} objects.
 */
@SuppressWarnings("unused")
@UseStag
public class ConnectedAppList extends BaseResponseList<ConnectedApp> {

    private static final long serialVersionUID = -114062434938515576L;

    @Override
    public Class<ConnectedApp> getModelClass() {
        return ConnectedApp.class;
    }
}
