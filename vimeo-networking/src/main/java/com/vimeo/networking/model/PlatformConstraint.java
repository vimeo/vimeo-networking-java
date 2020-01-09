package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import java.io.Serializable;

/**
 * Constraints put in place by a social media platform on uploading videos.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag
public class PlatformConstraint implements Serializable {

    private static final long serialVersionUID = -3004640141349461475L;

    @SerializedName("duration")
    private int mDuration;

    @SerializedName("size")
    private long mSize;

    /**
     * @return the max length in seconds of a video for the corresponding platform.
     */
    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        this.mDuration = duration;
    }

    /**
     * @return the max file size in gigabytes of a video for the corresponding platform.
     */
    public long getSize() {
        return mSize;
    }

    public void setSize(long size) {
        this.mSize = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        PlatformConstraint that = (PlatformConstraint) o;

        if (getDuration() != that.getDuration()) { return false; }
        return getSize() == that.getSize();
    }

    @Override
    public int hashCode() {
        int result = getDuration();
        result = 31 * result + (int) (getSize() ^ (getSize() >>> 32));
        return result;
    }
}
