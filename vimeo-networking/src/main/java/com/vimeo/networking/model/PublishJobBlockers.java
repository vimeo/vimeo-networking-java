package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import java.util.Collections;
import java.util.List;

@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag
public class PublishJobBlockers {

    @UseStag
    public enum Blocker {
        @SerializedName("size")
        SIZE,
        @SerializedName("duration")
        DURATION,
        @SerializedName("fb_no_pages")
        FB_NO_PAGES,
        @SerializedName("li_no_organizations")
        LI_NO_ORGANIZATIONS;

        Blocker() {
        }
    }

    @SerializedName("facebook")
    private List<Blocker> mFacebook;

    @SerializedName("youtube")
    private List<Blocker> mYoutube;

    @SerializedName("linkedin")
    private List<Blocker> mLinkedin;

    @SerializedName("twitter")
    private List<Blocker> mTwitter;

    PublishJobBlockers() {
    }

    public List<Blocker> getFacebook() {
        return mFacebook == null ? null : Collections.unmodifiableList(mFacebook);
    }

    public List<Blocker> getYoutube() {
        return mYoutube == null ? null : Collections.unmodifiableList(mYoutube);
    }

    public List<Blocker> getLinkedin() {
        return mLinkedin == null ? null : Collections.unmodifiableList(mLinkedin);
    }

    public List<Blocker> getTwitter() {
        return mTwitter == null ? null : Collections.unmodifiableList(mTwitter);
    }

    public void setFacebook(List<Blocker> facebook) {
        mFacebook = facebook == null ? null : Collections.unmodifiableList(facebook);
    }

    public void setYoutube(List<Blocker> youtube) {
        mYoutube = youtube == null ? null : Collections.unmodifiableList(youtube);
    }

    public void setLinkedin(List<Blocker> linkedin) {
        mLinkedin = linkedin == null ? null : Collections.unmodifiableList(linkedin);
    }

    public void setTwitter(List<Blocker> twitter) {
        mTwitter = twitter == null ? null : Collections.unmodifiableList(twitter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        PublishJobBlockers that = (PublishJobBlockers) o;

        if (getFacebook() != null ? !getFacebook().equals(that.getFacebook()) : that.getFacebook() != null) {
            return false;
        }
        if (getYoutube() != null ? !getYoutube().equals(that.getYoutube()) : that.getYoutube() != null) {
            return false;
        }
        if (getLinkedin() != null ? !getLinkedin().equals(that.getLinkedin()) : that.getLinkedin() != null) {
            return false;
        }
        return getTwitter() != null ? getTwitter().equals(that.getTwitter()) : that.getTwitter() == null;
    }

    @Override
    public int hashCode() {
        int result = getFacebook() != null ? getFacebook().hashCode() : 0;
        result = 31 * result + (getYoutube() != null ? getYoutube().hashCode() : 0);
        result = 31 * result + (getLinkedin() != null ? getLinkedin().hashCode() : 0);
        result = 31 * result + (getTwitter() != null ? getTwitter().hashCode() : 0);
        return result;
    }
}

