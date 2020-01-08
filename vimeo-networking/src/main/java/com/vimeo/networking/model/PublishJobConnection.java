package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;


import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

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
    private PublishJobDestinations mPublishJobDestinations;

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
     * @return an object representing which destinations the video was been published to.
     */
    public PublishJobDestinations getPublishJobDestinations() {
        return mPublishJobDestinations;
    }

    public void setPublishJobBlockers(PublishJobBlockers publishJobBlockers) {
        mPublishJobBlockers = publishJobBlockers;
    }

    public void setPublishJobConstraints(PublishJobConstraints publishJobConstraints) {
        mPublishJobConstraints = publishJobConstraints;
    }

    public void setPublishJobDestinations(PublishJobDestinations publishJobDestinations) {
        mPublishJobDestinations = publishJobDestinations;
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
        return getPublishJobDestinations() != null ? getPublishJobDestinations().equals(that.getPublishJobDestinations()) : that.getPublishJobDestinations() == null;
    }

    @Override
    public int hashCode() {
        int result = getPublishJobBlockers() != null ? getPublishJobBlockers().hashCode() : 0;
        result = 31 * result + (getPublishJobConstraints() != null ? getPublishJobConstraints().hashCode() : 0);
        result = 31 * result + (getPublishJobDestinations() != null ? getPublishJobDestinations().hashCode() : 0);
        return result;
    }
}


