package com.vimeo.networking.upload;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The representation of the upload object.
 * <p>
 * Created by restainoa on 3/27/18.
 */
@UseStag
public class Upload implements Serializable {

    private static final long serialVersionUID = 9209923316093008332L;

    /**
     * The status of the upload.
     */
    public enum Status {
        @SerializedName("complete")
        COMPLETE,
        @SerializedName("error")
        ERROR,
        @SerializedName("in_progress")
        IN_PROGRESS
    }

    @Nullable
    @SerializedName("approach")
    private String mApproach;

    @Nullable
    @SerializedName("complete_uri")
    private String mCompleteUri;

    @Nullable
    @SerializedName("gcs")
    private ArrayList<Gcs> mGcs;

    @Nullable
    @SerializedName("form")
    private String mForm;

    @Nullable
    @SerializedName("link")
    private String mLink;

    @Nullable
    @SerializedName("redirect_url")
    private String mRedirectUrl;

    @Nullable
    @SerializedName("size")
    private Long mSize;

    @Nullable
    @SerializedName("status")
    private Status mStatus;

    @Nullable
    @SerializedName("upload_link")
    private String mUploadLink;

    @Nullable
    public String getApproach() {
        return mApproach;
    }

    void setApproach(@Nullable String approach) {
        mApproach = approach;
    }

    @Nullable
    public String getCompleteUri() {
        return mCompleteUri;
    }

    void setCompleteUri(@Nullable String completeUri) {
        mCompleteUri = completeUri;
    }

    @Nullable
    public String getForm() {
        return mForm;
    }

    void setForm(@Nullable String form) {
        mForm = form;
    }

    @Nullable
    public String getLink() {
        return mLink;
    }

    void setLink(@Nullable String link) {
        mLink = link;
    }

    @Nullable
    public String getRedirectUrl() {
        return mRedirectUrl;
    }

    void setRedirectUrl(@Nullable String redirectUrl) {
        mRedirectUrl = redirectUrl;
    }

    @Nullable
    public Long getSize() {
        return mSize;
    }

    void setSize(@Nullable Long size) {
        mSize = size;
    }

    @Nullable
    public Status getStatus() {
        return mStatus;
    }

    void setStatus(@Nullable Status status) {
        mStatus = status;
    }

    @Nullable
    public String getUploadLink() {
        return mUploadLink;
    }

    void setUploadLink(@Nullable String uploadLink) {
        mUploadLink = uploadLink;
    }

    /**
     * @return An array of Gcs objects used for uploading if available. The number of Gcs object will
     * be equal to the number of parallel requests specified in the video upload creation step.
     */
    @Nullable
    public ArrayList<Gcs> getGcs() {
        return mGcs != null ? new ArrayList<>(mGcs) : null;
    }

    /**
     * Set the array of Gcs objects
     */
    public void setGcs(@Nullable ArrayList<Gcs> gcs) {
        mGcs = (gcs != null) ? new ArrayList<>(gcs) : null;
    }
}
