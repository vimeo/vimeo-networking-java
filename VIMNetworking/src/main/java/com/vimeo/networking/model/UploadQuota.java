package com.vimeo.networking.model;


import com.vimeo.networking.Vimeo;

import java.io.Serializable;

/**
 * Created by kylevenn on 8/19/15.
 */
public class UploadQuota implements Serializable {

    private static final long serialVersionUID = 4050488085481972886L;

    public Space space;
    public com.vimeo.networking.model.Quota quota;

    // Returns -1 if there is no space object on this user
    public long getFreeUploadSpace() {
        if (space != null) {
            return space.free;
        } else {
            return Vimeo.NOT_FOUND;
        }
    }
}
