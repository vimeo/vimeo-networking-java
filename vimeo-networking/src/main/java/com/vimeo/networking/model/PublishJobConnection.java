package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;


import org.jetbrains.annotations.Nullable;

/**
 * A Connection to provide the Publish to Social data for this video.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag
public class PublishJobConnection extends Connection implements Entity {

    private static final long serialVersionUID = 6618152644915659939L;

    @SerializedName("publish_blockers")
    private PublishJobBlockers mPublishJobBlockers;

    @SerializedName("publish_constraints")
    private PublishJobConstraints mPublishJobConstraints;

    @SerializedName("publish_destinations")
    private PublishJobAttempts mPublishJobAttempts;

    @Nullable
    @Override
    public String getIdentifier() {
        return mUri;
    }

    /**
     * @return an object representing the blockers for each platform preventing the video from being published.
     */
    public PublishJobBlockers getPublishJobBlockers() {
        return mPublishJobBlockers;
    }

    /**
     * @return an object representing publish constraints for each social media platform.
     */
    public PublishJobConstraints getPublishJobConstraints() {
        return mPublishJobConstraints;
    }

    /**
     * @return an object representing whether attempts have been made to publish
     * the video to third party social platform destinations.
     */
    public PublishJobAttempts getPublishJobAttempts() {
        return mPublishJobAttempts;
    }

    public void setPublishJobBlockers(PublishJobBlockers publishJobBlockers) {
        mPublishJobBlockers = publishJobBlockers;
    }

    public void setPublishJobConstraints(PublishJobConstraints publishJobConstraints) {
        mPublishJobConstraints = publishJobConstraints;
    }

    public void setPublishJobAttempts(PublishJobAttempts publishJobAttempts) {
        mPublishJobAttempts = publishJobAttempts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        PublishJobConnection that = (PublishJobConnection) o;

        if (getPublishJobBlockers() != null ? !getPublishJobBlockers().equals(that.getPublishJobBlockers()) : that.getPublishJobBlockers() != null) {
            return false;
        }
        if (getPublishJobConstraints() != null ? !getPublishJobConstraints().equals(that.getPublishJobConstraints()) : that.getPublishJobConstraints() != null) {
            return false;
        }
        return getPublishJobAttempts() != null ? getPublishJobAttempts().equals(that.getPublishJobAttempts()) : that.getPublishJobAttempts() == null;
    }

    @Override
    public int hashCode() {
        int result = getPublishJobBlockers() != null ? getPublishJobBlockers().hashCode() : 0;
        result = 31 * result + (getPublishJobConstraints() != null ? getPublishJobConstraints().hashCode() : 0);
        result = 31 * result + (getPublishJobAttempts() != null ? getPublishJobAttempts().hashCode() : 0);
        return result;
    }
}


