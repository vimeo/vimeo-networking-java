package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import java.io.Serializable;

/**
 * An object representing the information on the publishing constraints for each social network.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag
public class PublishJobConstraints implements Serializable {

    private static final long serialVersionUID = -2012494818939132360L;

    @SerializedName("facebook")
    private PlatformConstraint mFacebook;

    @SerializedName("linkedIn")
    private PlatformConstraint mLinkedin;

    @SerializedName("youtube")
    private PlatformConstraint mYouTube;

    @SerializedName("twitter")
    private PlatformConstraint mTwitter;

    /**
     * @return the publish constraints for Facebook.
     */
    public PlatformConstraint getFacebook() {
        return mFacebook;
    }

    /**
     * @return the publish constraints for LinkedIn.
     */
    public PlatformConstraint getLinkedin() {
        return mLinkedin;
    }

    /**
     * @return the publish constraints for YouTube.
     */
    public PlatformConstraint getYouTube() {
        return mYouTube;
    }

    /**
     * @return the publish constraints for Twitter.
     */
    public PlatformConstraint getTwitter() {
        return mTwitter;
    }

    public void setFacebook(PlatformConstraint facebook) {
        mFacebook = facebook;
    }

    public void setLinkedin(PlatformConstraint linkedin) {
        mLinkedin = linkedin;
    }

    public void setYouTube(PlatformConstraint youTube) {
        mYouTube = youTube;
    }

    public void setTwitter(PlatformConstraint twitter) {
        mTwitter = twitter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        PublishJobConstraints that = (PublishJobConstraints) o;

        if (getFacebook() != null ? !getFacebook().equals(that.getFacebook()) : that.getFacebook() != null) {
            return false;
        }
        if (getLinkedin() != null ? !getLinkedin().equals(that.getLinkedin()) : that.getLinkedin() != null) {
            return false;
        }
        if (getYouTube() != null ? !getYouTube().equals(that.getYouTube()) : that.getYouTube() != null) {
            return false;
        }
        return getTwitter() != null ? getTwitter().equals(that.getTwitter()) : that.getTwitter() == null;
    }

    @Override
    public int hashCode() {
        int result = getFacebook() != null ? getFacebook().hashCode() : 0;
        result = 31 * result + (getLinkedin() != null ? getLinkedin().hashCode() : 0);
        result = 31 * result + (getYouTube() != null ? getYouTube().hashCode() : 0);
        result = 31 * result + (getTwitter() != null ? getTwitter().hashCode() : 0);
        return result;
    }
}

