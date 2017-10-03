package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.appconfiguration.AppConfiguration;
import com.vimeo.networking.model.appconfiguration.live.LiveChatConfiguration;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A model representing the specific data needed for the live chat feature when a live video is playing.
 * Additional data can be found in the {@link LiveChatConfiguration} class available in the {@link AppConfiguration}
 * <p>
 * Created by rigbergh on 10/3/17.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class LiveChat implements Serializable {

    private static final long serialVersionUID = 1166079832999328432L;
    @Nullable
    @SerializedName("room_id")
    private String mRoomId;

    @Nullable
    @SerializedName("token")
    private String mToken;

    @Nullable
    @SerializedName("user")
    private User mUser;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Nullable
    public String getRoomId() {
        return mRoomId;
    }

    @Nullable
    public String getToken() {
        return mToken;
    }

    @Nullable
    public User getUser() {
        return mUser;
    }

    void setRoomId(@Nullable String roomId) {
        mRoomId = roomId;
    }

    void setToken(@Nullable String token) {
        mToken = token;
    }

    void setUser(@Nullable User user) {
        mUser = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        final LiveChat liveChat = (LiveChat) o;

        if (mRoomId != null ? !mRoomId.equals(liveChat.mRoomId) : liveChat.mRoomId != null) { return false; }
        //noinspection SimplifiableIfStatement
        if (mToken != null ? !mToken.equals(liveChat.mToken) : liveChat.mToken != null) { return false; }
        return mUser != null ? mUser.equals(liveChat.mUser) : liveChat.mUser == null;

    }

    @Override
    public int hashCode() {
        int result = mRoomId != null ? mRoomId.hashCode() : 0;
        result = 31 * result + (mToken != null ? mToken.hashCode() : 0);
        result = 31 * result + (mUser != null ? mUser.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LiveChat{" +
               "mRoomId='" + mRoomId + '\'' +
               ", mToken='" + mToken + '\'' +
               ", mUser=" + mUser +
               '}';
    }
}
