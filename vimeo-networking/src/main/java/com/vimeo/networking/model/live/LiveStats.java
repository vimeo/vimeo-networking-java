package com.vimeo.networking.model.live;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A model containing information about the stats for a live video
 * Created by rigbergh on 10/3/17.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class LiveStats implements Serializable {

    private static final long serialVersionUID = -8174242949801535304L;
    
    @Nullable
    @SerializedName("plays")
    private Long mPlays;

    @Nullable
    @SerializedName("total_view_time")
    private Long mTotalViewTime;

    @Nullable
    @SerializedName("viewers")
    private LiveStatsViewers mViewers;

    /**
     * @return The current total amount of plays this video has received.
     */
    @Nullable
    public Long getPlays() {
        return mPlays;
    }

    /**
     * @return The total amount of time spent watching this video by all viewers.
     */
    @Nullable
    public Long getTotalViewTime() {
        return mTotalViewTime;
    }

    /**
     * @return The {@link LiveStatsViewers} data for this video
     */
    @Nullable
    public LiveStatsViewers getViewers() {
        return mViewers;
    }

    /**
     * @see LiveStatsViewers#getCurrent()
     */
    @Nullable
    public Long getCurrentViewerCount() {
        return mViewers != null ? mViewers.getCurrent() : null;
    }

    /**
     * @see LiveStatsViewers#getPeak()
     */
    @Nullable
    public Long getPeakViewerCount() {
        return mViewers != null ? mViewers.getPeak() : null;
    }

    void setTotalViewTime(@Nullable Long totalViewTime) {
        mTotalViewTime = totalViewTime;
    }

    void setPlays(@Nullable Long plays) {
        mPlays = plays;
    }

    void setViewers(@Nullable LiveStatsViewers viewers) {
        mViewers = viewers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        final LiveStats liveStats = (LiveStats) o;

        if (mPlays != null ? !mPlays.equals(liveStats.mPlays) : liveStats.mPlays != null) { return false; }
        //noinspection SimplifiableIfStatement
        if (mTotalViewTime != null ?
                !mTotalViewTime.equals(liveStats.mTotalViewTime) : liveStats.mTotalViewTime != null) {
            return false;
        }
        return mViewers != null ? mViewers.equals(liveStats.mViewers) : liveStats.mViewers == null;

    }

    @Override
    public int hashCode() {
        int result = mPlays != null ? mPlays.hashCode() : 0;
        result = 31 * result + (mTotalViewTime != null ? mTotalViewTime.hashCode() : 0);
        result = 31 * result + (mViewers != null ? mViewers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LiveStats{" +
               "mPlays=" + mPlays +
               ", mTotalViewTime=" + mTotalViewTime +
               ", mViewers=" + mViewers +
               '}';
    }
}
