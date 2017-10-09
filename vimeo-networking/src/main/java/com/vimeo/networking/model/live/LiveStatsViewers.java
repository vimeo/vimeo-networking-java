package com.vimeo.networking.model.live;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Contains information about viewers of a live video
 *
 * Created by rigbergh on 10/6/17.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class LiveStatsViewers implements Serializable {

    private static final long serialVersionUID = -2487854074511211912L;

    @Nullable
    @SerializedName("current")
    private Long mCurrent;

    @Nullable
    @SerializedName("peak")
    private Long mPeak;

    /**
     * @return The current amount of people watching this video.
     */
    @Nullable
    public Long getCurrent() {
        return mCurrent;
    }

    /**
     * @return The peak amount of people watching this video at any time in the provided date range.
     */
    @Nullable
    public Long getPeak() {
        return mPeak;
    }

    void setCurrent(@Nullable Long current) {
        mCurrent = current;
    }

    void setPeak(@Nullable Long peak) {
        mPeak = peak;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        final LiveStatsViewers viewers = (LiveStatsViewers) o;

        //noinspection SimplifiableIfStatement
        if (mCurrent != null ? !mCurrent.equals(viewers.mCurrent) : viewers.mCurrent != null) { return false; }
        return mPeak != null ? mPeak.equals(viewers.mPeak) : viewers.mPeak == null;

    }

    @Override
    public int hashCode() {
        int result = mCurrent != null ? mCurrent.hashCode() : 0;
        result = 31 * result + (mPeak != null ? mPeak.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Viewers{" +
               "mCurrent=" + mCurrent +
               ", mPeak=" + mPeak +
               '}';
    }
}
