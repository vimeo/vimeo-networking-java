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
import com.vimeo.networking.model.Metadata.MetadataTypeAdapter;
import com.vimeo.networking.model.PictureCollection.PictureCollectionTypeAdapter;
import com.vimeo.networking.model.Preferences.PreferencesTypeAdapter;
import com.vimeo.networking.model.Privacy.PrivacyValue;
import com.vimeo.networking.model.UploadQuota.UploadQuotaTypeAdapter;
import com.vimeo.networking.model.Website.WebsiteTypeAdapter;
import com.vimeo.networking.utils.ISO8601;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Model representing a User
 * Created by alfredhanssen on 4/12/15.
 */

public class User implements Serializable {

    private static final long serialVersionUID = -4112910222188194647L;

    public enum AccountType {
        BASIC,
        PRO,
        PLUS,
        STAFF
    }

    public String uri;
    public String name;
    public String link;
    public String location;
    public String bio;
    public Date createdTime;
    public String account;
    public PictureCollection pictures;
    public ArrayList<Website> websites;
    public Metadata metadata;
    @SerializedName("upload_quota")
    public UploadQuota uploadQuota;
    @Nullable
    protected Preferences preferences;

    public AccountType getAccountType() {
        if (this.account == null) {
            //We should assume the account object could be null; also, a User object could be created with
            // just a uri, then updated when fetched from the server, so account would be null until then.
            // Scenario: deeplinking. [KZ] 9/29/15
            return AccountType.BASIC;
        }
        if ("basic".equals(this.account)) {
            return AccountType.BASIC;
        } else if ("plus".equals(this.account)) {
            return AccountType.PLUS;
        } else if ("pro".equals(this.account)) {
            return AccountType.PRO;
        } else if ("staff".equals(this.account)) {
            return AccountType.STAFF;
        }

        return AccountType.BASIC;
    }

    /**
     * -----------------------------------------------------------------------------------------------------
     * Interaction Accessors/Helpers
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Accessors/Helpers">
    public boolean canFollow() {
        return getFollowInteraction() != null;
    }

    public boolean isFollowing() {
        return getFollowInteraction() != null && metadata.interactions.follow.added;
    }

    @Nullable
    public Interaction getFollowInteraction() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.follow != null) {
            return metadata.interactions.follow;
        }
        return null;
    }

    @Nullable
    public Connection getFollowingConnection() {
        if (metadata != null && metadata.connections != null) {
            return metadata.connections.following;
        }
        return null;
    }

    @Nullable
    public Connection getFollowersConnection() {
        if (metadata != null && metadata.connections != null) {
            return metadata.connections.followers;
        }
        return null;
    }

    public int getFollowerCount() {
        if (getFollowersConnection() != null) {
            return getFollowersConnection().total;
        }
        return 0;
    }

    public int getFollowingCount() {
        if (getFollowingConnection() != null) {
            return getFollowingConnection().total;
        }
        return 0;
    }

    @Nullable
    public Connection getLikesConnection() {
        if (metadata != null && metadata.connections != null && metadata.connections.likes != null) {
            return metadata.connections.likes;
        }
        return null;
    }

    public int getLikesCount() {
        if (getLikesConnection() != null) {
            return getLikesConnection().total;
        }
        return 0;
    }

    @Nullable
    public Connection getWatchLaterConnection() {
        if (metadata != null && metadata.connections != null && metadata.connections.watchlater != null) {
            return metadata.connections.watchlater;
        }
        return null;
    }
    // </editor-fold>

    public ArrayList<Picture> getPictures() {
        if (pictures == null || pictures.sizes == null) {
            return new ArrayList<>();
        }
        return pictures.sizes;
    }

    @Nullable
    public Connection getVideosConnection() {
        if ((metadata != null) && (metadata.connections != null) && (metadata.connections.videos != null)) {
            return metadata.connections.videos;
        }
        return null;
    }

    public int getVideoCount() {
        if (getVideosConnection() != null) {
            return metadata.connections.videos.total;
        }
        return 0;
    }

    public boolean isPlusOrPro() {
        boolean plusOrPro = false;
        if (((getAccountType() == AccountType.PLUS) || (getAccountType() == AccountType.PRO))) {
            plusOrPro = true;
        }
        return plusOrPro;
    }

    @Nullable
    public PrivacyValue getPreferredVideoPrivacyValue() {
        PrivacyValue privacyValue = null;
        if (getPreferences() != null && getPreferences().getVideos() != null &&
            getPreferences().getVideos().getPrivacy() != null) {
            privacyValue = PrivacyValue.privacyValueFromString(getPreferences().getVideos().getPrivacy());
        }
        return privacyValue;
    }

    public boolean canUploadPicture() {
        if ((metadata != null) && (metadata.connections != null) &&
            (metadata.connections.pictures != null) &&
            (metadata.connections.pictures.options != null)) {
            return metadata.connections.pictures.options.contains(Vimeo.OPTIONS_POST);
        }
        return false;
    }

    public UploadQuota getUploadQuota() {
        return uploadQuota;
    }

    // Returns -1 if there is no space object on this user
    public long getFreeUploadSpace() {
        if (uploadQuota != null) {
            return uploadQuota.getFreeUploadSpace();
        }
        return Vimeo.NOT_FOUND;
    }

    /**
     * -----------------------------------------------------------------------------------------------------
     * Getters
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Getters">
    public String getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getLocation() {
        return location;
    }

    public String getBio() {
        return bio;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getAccount() {
        return account;
    }

    public ArrayList<Website> getWebsites() {
        return websites;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    @Nullable
    public Preferences getPreferences() {
        return preferences;
    }

    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User that = (User) o;

        return ((this.uri != null && that.uri != null) && this.uri.equals(that.uri));
    }

    @Override
    public int hashCode() {
        return this.uri != null ? this.uri.hashCode() : 0;
    }

    public static class UserTypeAdapter extends TypeAdapter<User> {

        @NotNull
        private final UploadQuotaTypeAdapter mUploadQuotaTypeAdapter;
        @NotNull
        private final WebsiteTypeAdapter mWebsiteTypeAdapter;
        @NotNull
        private final MetadataTypeAdapter mMetadataTypeAdapter;
        @NotNull
        private final PreferencesTypeAdapter mPreferencesTypeAdapter;
        @NotNull
        private final PictureCollectionTypeAdapter mPictureCollectionTypeAdapter;

        public UserTypeAdapter(@NotNull UploadQuotaTypeAdapter uploadQuotaTypeAdapter,
                               @NotNull WebsiteTypeAdapter websiteTypeAdapter,
                               @NotNull MetadataTypeAdapter metadataTypeAdapter,
                               @NotNull PreferencesTypeAdapter preferencesTypeAdapter,
                               @NotNull PictureCollectionTypeAdapter pictureCollectionTypeAdapter) {
            mUploadQuotaTypeAdapter = uploadQuotaTypeAdapter;
            mWebsiteTypeAdapter = websiteTypeAdapter;
            mMetadataTypeAdapter = metadataTypeAdapter;
            mPreferencesTypeAdapter = preferencesTypeAdapter;
            mPictureCollectionTypeAdapter = pictureCollectionTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, User value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("User null in write()");
                out.endObject();
                return;
            }
            if (value.uri != null) {
                out.name("uri").value(value.uri);
            }
            if (value.name != null) {
                out.name("name").value(value.name);
            }
            if (value.link != null) {
                out.name("link").value(value.link);
            }
            if (value.location != null) {
                out.name("location").value(value.location);
            }
            if (value.bio != null) {
                out.name("bio").value(value.bio);
            }
            if (value.createdTime != null) {
                out.name("created_time").value(ISO8601.fromDate(value.createdTime));
            }
            if (value.account != null) {
                out.name("account").value(value.account);
            }
            if (value.pictures != null) {
                out.name("pictures");
                mPictureCollectionTypeAdapter.write(out, value.pictures);
            }
            out.name("websites").beginArray();
            if (value.websites != null) {
                for (final Website website : value.websites) {
                    mWebsiteTypeAdapter.write(out, website);
                }
            }
            out.endArray();
            if (value.metadata != null) {
                out.name("metadata");
                mMetadataTypeAdapter.write(out, value.metadata);
            }
            if (value.uploadQuota != null) {
                out.name("upload_quota");
                mUploadQuotaTypeAdapter.write(out, value.uploadQuota);
            }
            if (value.preferences != null) {
                out.name("preferences");
                mPreferencesTypeAdapter.write(out, value.preferences);
            }

            out.endObject();
        }

        @Override
        public User read(JsonReader in) throws IOException {
            final User user = new User();
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
                        user.uri = in.nextString();
                        break;
                    case "name":
                        user.name = in.nextString();
                        break;
                    case "link":
                        user.link = in.nextString();
                        break;
                    case "location":
                        user.location = in.nextString();
                        break;
                    case "bio":
                        user.bio = in.nextString();
                        break;
                    case "created_time":
                        try {
                            user.createdTime = ISO8601.toDate(in.nextString());
                        } catch (ParseException e) {
                            ClientLogger.e("Error parsing User date", e);
                        }
                        break;
                    case "account":
                        user.account = in.nextString();
                        break;
                    case "pictures":
                        user.pictures = mPictureCollectionTypeAdapter.read(in);
                        break;
                    case "websites":
                        in.beginArray();
                        user.websites = new ArrayList<>();
                        while (in.hasNext()) {
                            user.websites.add(mWebsiteTypeAdapter.read(in));
                        }
                        in.endArray();
                        break;
                    case "metadata":
                        user.metadata = mMetadataTypeAdapter.read(in);
                        break;
                    case "upload_quota":
                        user.uploadQuota = mUploadQuotaTypeAdapter.read(in);
                        break;
                    case "preferences":
                        user.preferences = mPreferencesTypeAdapter.read(in);
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return user;
        }
    }

    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (User.class.isAssignableFrom(type.getRawType())) {
                TypeAdapter uploadQuotaTypeAdapter = gson.getAdapter(UploadQuota.class);
                TypeAdapter websiteTypeAdapter = gson.getAdapter(Website.class);
                TypeAdapter pictureCollectionTypeAdapter = gson.getAdapter(PictureCollection.class);
                TypeAdapter metadataTypeAdapter = gson.getAdapter(Metadata.class);
                TypeAdapter preferencesTypeAdapter = gson.getAdapter(Preferences.class);
                if (uploadQuotaTypeAdapter instanceof UploadQuotaTypeAdapter &&
                    pictureCollectionTypeAdapter instanceof PictureCollectionTypeAdapter &&
                    websiteTypeAdapter instanceof WebsiteTypeAdapter &&
                    metadataTypeAdapter instanceof MetadataTypeAdapter &&
                    preferencesTypeAdapter instanceof PreferencesTypeAdapter) {
                    return (TypeAdapter<T>) new UserTypeAdapter(
                            (UploadQuotaTypeAdapter) uploadQuotaTypeAdapter,
                            (WebsiteTypeAdapter) websiteTypeAdapter,
                            (MetadataTypeAdapter) metadataTypeAdapter,
                            (PreferencesTypeAdapter) preferencesTypeAdapter,
                            (PictureCollectionTypeAdapter) pictureCollectionTypeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
