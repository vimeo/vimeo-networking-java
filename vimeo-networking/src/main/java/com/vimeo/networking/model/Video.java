/*
 * Copyright (c) 2015 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.vimeo.networking.model;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.Vimeo;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking.model.Category.CategoryTypeAdapter;
import com.vimeo.networking.model.Embed.EmbedTypeAdapter;
import com.vimeo.networking.model.Interaction.Stream;
import com.vimeo.networking.model.Metadata.MetadataTypeAdapter;
import com.vimeo.networking.model.PictureCollection.PictureCollectionTypeAdapter;
import com.vimeo.networking.model.Privacy.PrivacyTypeAdapter;
import com.vimeo.networking.model.StatsCollection.StatsCollectionTypeAdapter;
import com.vimeo.networking.model.Tag.TagTypeAdapter;
import com.vimeo.networking.model.User.UserTypeAdapter;
import com.vimeo.networking.model.VideoFile.VideoFileTypeAdapter;
import com.vimeo.networking.model.playback.Play;
import com.vimeo.networking.model.playback.Play.PlayTypeAdapter;
import com.vimeo.networking.utils.EnumTypeAdapter;
import com.vimeo.networking.utils.ISO8601;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A model class representing a Video.
 * Created by alfredhanssen on 4/12/15.
 */
@SuppressWarnings("unused")
public class Video implements Serializable {

    private static final long serialVersionUID = -1282907783845240057L;

    public enum ContentRating // TODO: use this enum [AH] 4/24/2015
    {
        SAFE,
        UNRATED,
        NUDITY,
        LANGUAGE,
        DRUGS,
        VIOLENCE
    }

    public enum LicenseValue // TODO: use this, https://developer.vimeo.com/api/playground/creativecommons [AH] 4/24/2015
    {
        ATTRIBUTION,
        ATTRIBUTION_SHARE_ALIKE,
        ATTRIBUTION_NO_DERIVATIVES,
        ATTRIBUTION_NON_COMMERCIAL,
        ATTRIBUTION_NON_COMMERCIAL_SHARE_ALIKE,
        ATTRIBUTION_NON_COMMERCIAL_NO_DERIVATIVES,
        PUBLIC_DOMAIN_DEDICATION
    }

    public enum VodVideoType {
        NONE,
        TRAILER,
        RENTAL,
        SUBSCRIPTION,
        PURCHASE,
        UNKNOWN
    }

    private static final String STATUS_NONE = "N/A";
    private static final String STATUS_AVAILABLE = "available";
    private static final String STATUS_UPLOADING = "uploading";
    private static final String STATUS_TRANSCODE_STARTING = "transcode_starting";
    private static final String STATUS_TRANSCODING = "transcoding";
    private static final String STATUS_UPLOADING_ERROR = "uploading_error";
    private static final String STATUS_TRANSCODING_ERROR = "transcoding_error";
    private static final String STATUS_QUOTA_EXCEEDED = "quota_exceeded";

    public enum Status {
        NONE(STATUS_NONE),
        @SerializedName(STATUS_AVAILABLE)
        AVAILABLE(STATUS_AVAILABLE),
        @SerializedName(STATUS_UPLOADING)
        UPLOADING(STATUS_UPLOADING),
        @SerializedName(STATUS_TRANSCODE_STARTING)
        TRANSCODE_STARTING(STATUS_TRANSCODE_STARTING),
        @SerializedName(STATUS_TRANSCODING)
        TRANSCODING(STATUS_TRANSCODING),
        // Errors
        @SerializedName(STATUS_UPLOADING_ERROR)
        UPLOADING_ERROR(STATUS_UPLOADING_ERROR),
        @SerializedName(STATUS_TRANSCODING_ERROR)
        TRANSCODING_ERROR(STATUS_TRANSCODING_ERROR),
        @SerializedName(STATUS_QUOTA_EXCEEDED)
        QUOTA_EXCEEDED(STATUS_QUOTA_EXCEEDED);

        private final String mString;

        Status(String string) {
            mString = string;
        }

        @Override
        // Overridden for analytics.
        public String toString() {
            return mString;
        }
    }

    // Note: if you rename any fields, GSON serialization of persisted videos may break. [KV] 3/31/16
    public String uri;
    public String name;
    public String description;
    public String link;
    public int duration;
    @Deprecated
    public ArrayList<VideoFile> files;
    public int width;
    public int height;
    @Deprecated
    public com.vimeo.networking.model.Embed embed;
    public String language;
    public Date createdTime;
    public Date modifiedTime;
    public Date releaseTime;
    public ArrayList<String> contentRating;
    public String license;
    public Privacy privacy;
    public PictureCollection pictures;
    public ArrayList<Tag> tags;
    public StatsCollection stats;
    public Metadata metadata;
    public User user;
    private Status status;
    public List<Category> categories;
    @Nullable
    private String password;
    @Nullable
    private String reviewLink;
    @Nullable
    private Play play;
    @Nullable
    private ArrayList<VideoFile> download;

    /** The resource_key field is the unique identifier for a Video object. It may be used for object comparison. */
    private String resourceKey;

    // -----------------------------------------------------------------------------------------------------
    // Resource Key
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Resource Key">
    public String getResourceKey() {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Status
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Status">

    /**
     * This will return the value as it's given to us from the API (or {@link Status#NONE if null}). Unlike
     * {@link Video#getStatus()}, this will return all known statuses for a video (including {@link Status#TRANSCODE_STARTING}.
     * <p/>
     * For a more simplified representation of the video status, use {@link Video#getStatus()}.
     */
    public Status getRawStatus() {
        return status == null ? Status.NONE : status;
    }

    /**
     * This getter is always guaranteed to return a {@link Status}, {@link Status#NONE if null}. If the Status
     * is equal to {@link Status#TRANSCODE_STARTING} then we'll just return the Status {@link Status#TRANSCODING}
     * since they're functionally equivalent from a client-side perspective.
     * <p/>
     * For an all-inclusive getter of the video status, use {@link Video#getRawStatus()}.
     */
    public Status getStatus() {
        if (status == Status.TRANSCODE_STARTING) {
            return Status.TRANSCODING;
        }
        return getRawStatus();
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    // </editor-fold>


    // -----------------------------------------------------------------------------------------------------
    // Watch Later
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Watch Later">
    @Nullable
    public Interaction getWatchLaterInteraction() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.watchlater != null) {
            return metadata.interactions.watchlater;
        }
        return null;
    }

    public boolean canWatchLater() {
        return getWatchLaterInteraction() != null;
    }

    public boolean isWatchLater() {
        return getWatchLaterInteraction() != null && getWatchLaterInteraction().added;
    }

    @Nullable
    public Connection getWatchLaterConnection() {
        if (metadata != null && metadata.connections != null && metadata.connections.watchlater != null) {
            return metadata.connections.watchlater;
        }
        return null;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Likes
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Like">
    @Nullable
    public Interaction getLikeInteraction() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.like != null) {
            return metadata.interactions.like;
        }
        return null;
    }

    public boolean canLike() {
        return getLikeInteraction() != null;
    }

    public boolean isLiked() {
        return getLikeInteraction() != null && getLikeInteraction().added;
    }

    @Nullable
    public Connection getLikesConnection() {
        if ((metadata != null) && (metadata.connections != null) && (metadata.connections.likes != null)) {
            return metadata.connections.likes;
        }
        return null;
    }

    @Nullable
    public Connection getRelatedConnection() {
        if ((metadata != null) && (metadata.connections != null)) {
            return metadata.connections.related;
        }
        return null;
    }

    public int likeCount() {
        if (getLikesConnection() != null) {
            return getLikesConnection().total;
        }
        return 0;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Stats
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Stats">

    /**
     * @return the number of plays this Videohas . It will return <code>null</code> if the owner of the video
     * has specified that they want to hide play count.
     */
    @Nullable
    public Integer playCount() {
        if (stats != null) {
            return stats.plays;
        } else {
            return null;
        }
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Files
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Files">
    @Nullable
    public VideoFile getVideoFileForMd5(String md5) {
        // Only Progressive video files have an md5
        if (play == null || play.getProgressiveVideoFiles() == null) {
            return null;
        }
        for (VideoFile file : play.getProgressiveVideoFiles()) {
            if (file != null && file.getMd5() != null && file.getMd5().equals(md5)) {
                return file;
            }
        }
        return null;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Recommendation
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Recommendation">
    public String recommendationsUri() {
        String recommendationsUri = null;
        if (metadata != null && metadata.connections != null &&
            metadata.connections.recommendations != null) {
            recommendationsUri = metadata.connections.recommendations.uri;
        }
        if (recommendationsUri == null) {
            recommendationsUri = uri + Vimeo.ENDPOINT_RECOMMENDATIONS;
        }
        return recommendationsUri;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Password
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Password">

    /**
     * The password for the video, only sent when the following conditions are true:
     * <ul>
     * <ol>The privacy is set to password</ol>
     * <ol>The user making the request owns the video</ol>
     * <ol>The application making the request is granted access to view this field</ol>
     * </ul>
     *
     * @return the password if applicable
     */
    @Nullable
    public String getPassword() {
        return password;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Review Link
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Review Link">
    @Nullable
    public String getReviewLink() {
        return reviewLink;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Play object, which holds the playback and embed controls
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Play object, which holds the playback and embed controls">
    @Nullable
    public Play getPlay() {
        return play;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Download - an array of VideoFile objects that may be downloaded
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Download">
    @Nullable
    public ArrayList<VideoFile> getDownload() {
        return download;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // VOD
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="VOD">

    /**
     * A VOD video can have multiple purchase types active at once, this is a convenience method that
     * picks one of them based on the following priority:
     * 1) trailer
     * 2) purchased
     * 3) both rental and subscription? choose the later expiration, expirations equal? choose subscription
     * 4) subscription
     * 5) rental
     *
     * @return the VodVideoType of the video or {@code VodVideoType.NONE} if it is not a VOD video or
     * {@code VodVideoType.UNKNOWN} if it is a VOD video that is not marked as rented, subscribed or bought
     */
    public VodVideoType getVodVideoType() {
        if (isVod()) {
            if (isTrailer()) {
                return VodVideoType.TRAILER;
            }
            if (isPurchase()) {
                return VodVideoType.PURCHASE;
            }
            // rentals or subscriptions that have been purchased will always have an expiration date
            Date rentalExpires = getRentalExpiration();
            Date subscriptionExpires = getSubscriptionExpiration();
            if (rentalExpires != null && subscriptionExpires != null) {
                if (rentalExpires.after(subscriptionExpires)) {
                    return VodVideoType.RENTAL;
                }
            }
            if (isSubscription()) {
                return VodVideoType.SUBSCRIPTION;
            }
            if (isRental()) {
                return VodVideoType.RENTAL;
            }
            // it is a VOD, but it was not purchased.
            // it is either the user's own video or promo code access or possibly an extra on a series
            // regardless, we don't have a way to determine it at this point 5/4/16 [HR]
            return VodVideoType.UNKNOWN;
        }
        return VodVideoType.NONE;
    }

    private static boolean isPurchased(Interaction interaction) {
        return (interaction != null && interaction.stream == Stream.PURCHASED);
    }

    /**
     * Returns the date the VOD video will expire. In the event that a video is both rented and subscribed,
     * this will return the later expiration date. If they are equal, subscription date will be returned.
     *
     * @return the expiration date or null if there is no expiration
     */
    @Nullable
    public Date getVodExpiration() {
        if (isVod()) {
            Date rentalExpires = getRentalExpiration();
            Date subscriptionExpires = getSubscriptionExpiration();
            if (rentalExpires != null && subscriptionExpires != null) {
                if (rentalExpires.after(subscriptionExpires)) {
                    return rentalExpires;
                } else {
                    return subscriptionExpires;
                }
            } else if (rentalExpires != null) {
                return rentalExpires;
            } else if (subscriptionExpires != null) {
                return subscriptionExpires;
            }
        }
        return null;
    }

    private boolean isPossiblePurchase() {
        return (isVod() && !isTrailer() && metadata != null && metadata.interactions != null);
    }

    @Nullable
    public Date getRentalExpiration() {
        if (isRental()) {
            // isRental will validate and prevent possible npes
            assert metadata.interactions.rent != null;
            return metadata.interactions.rent.expiration;
        }
        return null;
    }

    @Nullable
    public Date getSubscriptionExpiration() {
        if (isSubscription()) {
            // isSubscription will validate and prevent possible npes
            assert metadata.interactions.subscribe != null;
            return metadata.interactions.subscribe.expiration;
        }
        return null;
    }

    public boolean isRental() {
        return (isPossiblePurchase() && isPurchased(metadata.interactions.rent));
    }

    public boolean isSubscription() {
        return (isPossiblePurchase() && isPurchased(metadata.interactions.subscribe));
    }

    public boolean isPurchase() {
        return (isPossiblePurchase() && isPurchased(metadata.interactions.buy));
    }

    public boolean isTrailer() {
        return (isVod() && metadata.connections.trailer == null);
    }

    public boolean isVod() {
        return (metadata != null && metadata.connections != null && metadata.connections.ondemand != null);
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Equals/Hashcode
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Equals/Hashcode">
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Video)) {
            return false;
        }

        Video that = (Video) o;

        return (this.resourceKey != null && that.resourceKey != null) &&
               this.resourceKey.equals(that.resourceKey);
    }

    @Override
    public int hashCode() {
        return this.resourceKey != null ? this.resourceKey.hashCode() : 0;
    }
    // </editor-fold>

    @SuppressWarnings("deprecation")
    public static class VideoTypeAdapter extends TypeAdapter<Video> {

        private final static EnumTypeAdapter<Status> sStatusEnumTypeAdapter =
                new EnumTypeAdapter<>(Status.class);

        @NotNull
        private final VideoFileTypeAdapter mVideoFileTypeAdapter;
        @NotNull
        private final EmbedTypeAdapter mEmbedTypeAdapter;
        @NotNull
        private final PrivacyTypeAdapter mPrivacyTypeAdapter;
        @NotNull
        private final TagTypeAdapter mTagTypeAdapter;
        @NotNull
        private final StatsCollectionTypeAdapter mStatsCollectionTypeAdapter;
        @NotNull
        private final PictureCollectionTypeAdapter mPictureCollectionTypeAdapter;
        @NotNull
        private final MetadataTypeAdapter mMetadataTypeAdapter;
        @NotNull
        private final UserTypeAdapter mUserTypeAdapter;
        @NotNull
        private final CategoryTypeAdapter mCategoryTypeAdapter;
        @NotNull
        private final PlayTypeAdapter mPlayTypeAdapter;

        public VideoTypeAdapter(@NotNull VideoFileTypeAdapter videoFileTypeAdapter,
                                @NotNull EmbedTypeAdapter embedTypeAdapter,
                                @NotNull PrivacyTypeAdapter privacyTypeAdapter,
                                @NotNull TagTypeAdapter tagTypeAdapter,
                                @NotNull StatsCollectionTypeAdapter statsCollectionTypeAdapter,
                                @NotNull PictureCollectionTypeAdapter pictureCollectionTypeAdapter,
                                @NotNull MetadataTypeAdapter metadataTypeAdapter,
                                @NotNull UserTypeAdapter userTypeAdapter,
                                @NotNull CategoryTypeAdapter categoryTypeAdapter,
                                @NotNull PlayTypeAdapter playTypeAdapter) {
            mVideoFileTypeAdapter = videoFileTypeAdapter;
            mEmbedTypeAdapter = embedTypeAdapter;
            mPrivacyTypeAdapter = privacyTypeAdapter;
            mTagTypeAdapter = tagTypeAdapter;
            mStatsCollectionTypeAdapter = statsCollectionTypeAdapter;
            mPictureCollectionTypeAdapter = pictureCollectionTypeAdapter;
            mMetadataTypeAdapter = metadataTypeAdapter;
            mUserTypeAdapter = userTypeAdapter;
            mCategoryTypeAdapter = categoryTypeAdapter;
            mPlayTypeAdapter = playTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, Video value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("Video null in write()");
                out.endObject();
                return;
            }

            if (value.uri != null) {
                out.name("uri").value(value.uri);
            }
            if (value.name != null) {
                out.name("name").value(value.name);
            }
            if (value.description != null) {
                out.name("description").value(value.description);
            }
            if (value.link != null) {
                out.name("link").value(value.link);
            }
            out.name("duration").value(value.duration);
            if (value.files != null) {
                out.name("files").beginArray();
                for (final VideoFile videoFile : value.files) {
                    mVideoFileTypeAdapter.write(out, videoFile);
                }
                out.endArray();
            }
            out.name("width").value(value.width);
            out.name("height").value(value.height);
            if (value.embed != null) {
                out.name("embed");
                mEmbedTypeAdapter.write(out, value.embed);
            }
            if (value.language != null) {
                out.name("language").value(value.language);
            }
            if (value.createdTime != null) {
                out.name("created_time").value(ISO8601.fromDate(value.createdTime));
            }
            if (value.modifiedTime != null) {
                out.name("modified_time").value(ISO8601.fromDate(value.modifiedTime));
            }
            if (value.releaseTime != null) {
                out.name("release_time").value(ISO8601.fromDate(value.releaseTime));
            }
            if (value.contentRating != null) {
                out.name("content_rating").beginArray();
                for (final String option : value.contentRating) {
                    out.value(option);
                }
                out.endArray();
            }
            if (value.license != null) {
                out.name("license").value(value.license);
            }
            if (value.privacy != null) {
                out.name("privacy");
                mPrivacyTypeAdapter.write(out, value.privacy);
            }
            if (value.pictures != null) {
                out.name("pictures");
                mPictureCollectionTypeAdapter.write(out, value.pictures);
            }
            if (value.tags != null) {
                out.name("tags").beginArray();
                for (final Tag tag : value.tags) {
                    mTagTypeAdapter.write(out, tag);
                }
                out.endArray();
            }
            if (value.stats != null) {
                out.name("stats");
                mStatsCollectionTypeAdapter.write(out, value.stats);
            }
            if (value.metadata != null) {
                out.name("metadata");
                mMetadataTypeAdapter.write(out, value.metadata);
            }
            if (value.user != null) {
                out.name("user");
                mUserTypeAdapter.write(out, value.user);
            }
            if (value.status != null) {
                out.name("status");
                sStatusEnumTypeAdapter.write(out, value.status);
            }
            if (value.categories != null) {
                out.name("categories").beginArray();
                for (final Category category : value.categories) {
                    mCategoryTypeAdapter.write(out, category);
                }
                out.endArray();
            }
            if (value.password != null) {
                out.name("password").value(value.password);
            }
            if (value.reviewLink != null) {
                out.name("review_link").value(value.reviewLink);
            }
            if (value.play != null) {
                out.name("play");
                mPlayTypeAdapter.write(out, value.play);

            }
            if (value.download != null) {
                out.name("download").beginArray();
                for (final VideoFile videoFile : value.download) {
                    mVideoFileTypeAdapter.write(out, videoFile);
                }
                out.endArray();
            }
            if (value.resourceKey != null) {
                out.name("resource_key").value(value.resourceKey);
            }

            out.endObject();
        }

        @Override
        public Video read(JsonReader in) throws IOException {
            final Video video = new Video();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "uri":
                        video.uri = in.nextString();
                        break;
                    case "name":
                        video.name = in.nextString();
                        break;
                    case "description":
                        video.description = in.nextString();
                        break;
                    case "link":
                        video.link = in.nextString();
                        break;
                    case "duration":
                        video.duration = in.nextInt();
                        break;
                    case "files":
                        in.beginArray();
                        video.files = new ArrayList<>();
                        while (in.hasNext()) {
                            video.files.add(mVideoFileTypeAdapter.read(in));
                        }
                        in.endArray();
                        break;
                    case "width":
                        video.width = in.nextInt();
                        break;
                    case "height":
                        video.height = in.nextInt();
                        break;
                    case "embed":
                        video.embed = mEmbedTypeAdapter.read(in);
                        break;
                    case "language":
                        video.language = in.nextString();
                        break;
                    case "created_time":
                        try {
                            video.createdTime = ISO8601.toDate(in.nextString());
                        } catch (ParseException e) {
                            ClientLogger.e("Error parsing Video date", e);
                        }
                        break;
                    case "modified_time":
                        try {
                            video.modifiedTime = ISO8601.toDate(in.nextString());
                        } catch (ParseException e) {
                            ClientLogger.e("Error parsing Video date", e);
                        }
                        break;
                    case "release_time":
                        try {
                            video.releaseTime = ISO8601.toDate(in.nextString());
                        } catch (ParseException e) {
                            ClientLogger.e("Error parsing Video date", e);
                        }
                        break;
                    case "content_rating":
                        in.beginArray();
                        video.contentRating = new ArrayList<>();
                        while (in.hasNext()) {
                            video.contentRating.add(in.nextString());
                        }
                        in.endArray();
                        break;
                    case "license":
                        video.license = in.nextString();
                        break;
                    case "privacy":
                        video.privacy = mPrivacyTypeAdapter.read(in);
                        break;
                    case "pictures":
                        video.pictures = mPictureCollectionTypeAdapter.read(in);
                        break;
                    case "tags":
                        in.beginArray();
                        video.tags = new ArrayList<>();
                        while (in.hasNext()) {
                            video.tags.add(mTagTypeAdapter.read(in));
                        }
                        in.endArray();
                        break;
                    case "stats":
                        video.stats = mStatsCollectionTypeAdapter.read(in);
                        break;
                    case "metadata":
                        video.metadata = mMetadataTypeAdapter.read(in);
                        break;
                    case "user":
                        video.user = mUserTypeAdapter.read(in);
                        break;
                    case "status":
                        video.status = sStatusEnumTypeAdapter.read(in);
                        break;
                    case "categories":
                        in.beginArray();
                        video.categories = new ArrayList<>();
                        while (in.hasNext()) {
                            video.categories.add(mCategoryTypeAdapter.read(in));
                        }
                        in.endArray();
                        break;
                    case "password":
                        video.password = in.nextString();
                        break;
                    case "review_link":
                        video.reviewLink = in.nextString();
                        break;
                    case "play":
                        video.play = mPlayTypeAdapter.read(in);
                        break;
                    case "download":
                        in.beginArray();
                        video.download = new ArrayList<>();
                        while (in.hasNext()) {
                            video.download.add(mVideoFileTypeAdapter.read(in));
                        }
                        in.endArray();
                        break;
                    case "resource_key":
                        video.resourceKey = in.nextString();
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return video;
        }
    }

    @SuppressWarnings("deprecation")
    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (Video.class.isAssignableFrom(typeToken.getRawType())) {
                TypeAdapter videoFileTypeAdapter = gson.getAdapter(VideoFile.class);
                TypeAdapter embedTypeAdapter = gson.getAdapter(Embed.class);
                TypeAdapter privacyTypeAdapter = gson.getAdapter(Privacy.class);
                TypeAdapter tagTypeAdapter = gson.getAdapter(Tag.class);
                TypeAdapter statsCollectionTypeAdapter = gson.getAdapter(StatsCollection.class);
                TypeAdapter pictureCollectionTypeAdapter = gson.getAdapter(PictureCollection.class);
                TypeAdapter metadataTypeAdapter = gson.getAdapter(Metadata.class);
                TypeAdapter userTypeAdapter = gson.getAdapter(User.class);
                TypeAdapter categoryTypeAdapter = gson.getAdapter(Category.class);
                TypeAdapter playTypeAdapter = gson.getAdapter(Play.class);
                if (videoFileTypeAdapter instanceof VideoFileTypeAdapter &&
                    embedTypeAdapter instanceof EmbedTypeAdapter &&
                    privacyTypeAdapter instanceof PrivacyTypeAdapter &&
                    tagTypeAdapter instanceof TagTypeAdapter &&
                    statsCollectionTypeAdapter instanceof StatsCollectionTypeAdapter &&
                    pictureCollectionTypeAdapter instanceof PictureCollectionTypeAdapter &&
                    metadataTypeAdapter instanceof MetadataTypeAdapter &&
                    userTypeAdapter instanceof UserTypeAdapter &&
                    categoryTypeAdapter instanceof CategoryTypeAdapter &&
                    playTypeAdapter instanceof PlayTypeAdapter) {
                    return (TypeAdapter<T>) new VideoTypeAdapter((VideoFileTypeAdapter) videoFileTypeAdapter,
                                                                 (EmbedTypeAdapter) embedTypeAdapter,
                                                                 (PrivacyTypeAdapter) privacyTypeAdapter,
                                                                 (TagTypeAdapter) tagTypeAdapter,
                                                                 (StatsCollectionTypeAdapter) statsCollectionTypeAdapter,
                                                                 (PictureCollectionTypeAdapter) pictureCollectionTypeAdapter,
                                                                 (MetadataTypeAdapter) metadataTypeAdapter,
                                                                 (UserTypeAdapter) userTypeAdapter,
                                                                 (CategoryTypeAdapter) categoryTypeAdapter,
                                                                 (PlayTypeAdapter) playTypeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
