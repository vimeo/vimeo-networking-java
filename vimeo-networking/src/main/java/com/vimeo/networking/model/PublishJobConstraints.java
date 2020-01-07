package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag
public class PublishJobConstraints {

    @SerializedName("facebook")
    public PlatformConstraint mFacebook;

    @SerializedName("linkedIn")
    public PlatformConstraint mLinkedin;

    @SerializedName("youtube")
    public PlatformConstraint mYouTube;

    @SerializedName("twitter")
    public PlatformConstraint mTwitter;

    public PublishJobConstraints() {
    }

    public PlatformConstraint getFacebook() {
        return mFacebook;
    }

    public PlatformConstraint getLinkedin() {
        return mLinkedin;
    }

    public PlatformConstraint getYouTube() {
        return mYouTube;
    }

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

