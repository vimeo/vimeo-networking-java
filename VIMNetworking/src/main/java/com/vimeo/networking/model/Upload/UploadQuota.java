package com.vimeo.networking.model.Upload;


/**
 * Created by kylevenn on 8/19/15.
 */
public class UploadQuota {

    public Space space;
    public Quota quota;

    // Returns -1 if there is no space object on this user
    public long getFreeUploadSpace() {
        if (space != null) {
            return space.free;
        } else {
            return -1;
        }
    }
}
