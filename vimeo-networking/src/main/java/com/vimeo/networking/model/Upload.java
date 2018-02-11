package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Object that contains information related to a {@link Video} upload
 *
 * @see <A href="https://github.vimeows.com/Vimeo-API/blueprint/blob/master/docs/3.4/representations/Upload.apib">
 * Upload API blueprint</A>
 * <p>
 * Created by rigbergh on 2/6/18.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class Upload implements Serializable{

    private static final String STATUS_COMPLETE = "complete";
    private static final String STATUS_IN_PROGRESS = "in_progress";
    private static final String STATUS_ERROR = "error";
    private static final String STATUS_NONE = "none";
    private static final long serialVersionUID = 4341282154011644678L;

    @UseStag
    public enum Status {
        @SerializedName(STATUS_NONE)
        NONE(STATUS_NONE),
        @SerializedName(STATUS_ERROR)
        ERROR(STATUS_ERROR),
        @SerializedName(STATUS_COMPLETE)
        COMPLETE(STATUS_COMPLETE),
        @SerializedName(STATUS_IN_PROGRESS)
        IN_PROGRESS(STATUS_IN_PROGRESS);

        private final String mStatus;

        Status(@NotNull String status) {
            mStatus = status;
        }

        @Override
        public String toString() {
            return mStatus;
        }
    }

    @Nullable
    @SerializedName("approach")
    private String mApproach;

    @Nullable
    @SerializedName("complete_uri")
    private String mCompleteUri;

    @Nullable
    @SerializedName("status")
    private Status mStatus;

    @Nullable
    @SerializedName("upload_link")
    private String mUploadLink;

    @Nullable
    @SerializedName("form")
    private String mForm;

    @Nullable
    @SerializedName("link")
    private String mLink;

    @Nullable
    @SerializedName("redirect_url")
    private String mRedirectUrl;

    @SerializedName("size")
    protected long mSize;


    /**
     * @return The upload approach (e.g. tus)
     */
    @Nullable
    public String getApproach() {
        return mApproach;
    }

    /**
     * @return The uri for completing an upload. This is not for tus. For tus uploads use {@link #getUploadLink}
     */
    @Nullable
    public String getCompleteUri() {
        return mCompleteUri;
    }

    /**
     * @return The HTML upload form
     */
    @Nullable
    public String getForm() {
        return mForm;
    }

    /**
     * @return The link, for upload approach, "pull". This is not for tus. For tus uploads use {@link #getUploadLink}
     */
    @Nullable
    public String getLink() {
        return mLink;
    }

    /**
     * @return The app redirect URL. This is not for tus. For tus uploads use {@link #getUploadLink}
     */
    @Nullable
    public String getRedirectUrl() {
        return mRedirectUrl;
    }

    /**
     * @return The size of the upload in bytes
     */
    public long getSize() {
        return mSize;
    }

    /**
     * @return Status code for clip availability.
     */
    public Status getStatus() {
        return mStatus == null ? Status.NONE : mStatus;
    }

    /**
     * @return The link for sending file data via tus
     */
    @Nullable
    public String getUploadLink() {
        return mUploadLink;
    }

    void setApproach(@Nullable String approach) {
        mApproach = approach;
    }

    void setCompleteUri(@Nullable String completeUri) {
        mCompleteUri = completeUri;
    }

    void setStatus(@Nullable Status status) {
        mStatus = status;
    }

    void setUploadLink(@Nullable String uploadLink) {
        mUploadLink = uploadLink;
    }

    void setForm(@Nullable String form) {
        mForm = form;
    }

    void setLink(@Nullable String link) {
        mLink = link;
    }

    void setRedirectUrl(@Nullable String redirectUrl) {
        mRedirectUrl = redirectUrl;
    }

    void setSize(long size) {
        mSize = size;
    }
}
