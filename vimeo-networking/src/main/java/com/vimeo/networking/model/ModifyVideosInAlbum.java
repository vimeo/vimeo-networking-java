package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An object that is used to patch video addition and deletion updates to an Album.
 */
@SuppressWarnings("unused")
public class ModifyVideosInAlbum implements Serializable {

    private static final long serialVersionUID = -3094719083671086785L;

    @Nullable
    @SerializedName("remove")
    private List<NamedWrapperForRemove> mRemoveVideoList;

    @Nullable
    @SerializedName("set")
    private List<NamedWrapperForAdd> mAddVideoList;

    public ModifyVideosInAlbum(@Nullable List<RemoveVideoFromAlbum> removeVideoList,
                               @Nullable List<AddVideoToAlbum> addVideoList) {
        if (removeVideoList != null) {
            mRemoveVideoList = new ArrayList<>();
            for (final RemoveVideoFromAlbum curRemove : removeVideoList) {
                mRemoveVideoList.add(new NamedWrapperForRemove(curRemove));
            }
        }
        if (addVideoList != null) {
            mAddVideoList = new ArrayList<>();
            for (final AddVideoToAlbum curAdd : addVideoList) {
                mAddVideoList.add(new NamedWrapperForAdd(curAdd));
            }
        }
    }

    /**
     * @return A nullable list of RemoveVideoFromAlbum objects, containing the wrapped list of videos to be removed
     * from the album.
     */
    @Nullable
    public List<RemoveVideoFromAlbum> getRemoveVideoList() {
        if (mRemoveVideoList == null) {
            return null;
        }
        final List<RemoveVideoFromAlbum> retVal = new ArrayList<>();
        for (final NamedWrapperForRemove curRemove : mRemoveVideoList) {
            retVal.add(curRemove.getRemoveVideoFromAlbum());
        }
        return retVal;
    }

    /**
     * @return A nullable list of AddVideoToAlbum objects, containing the wrapped list of videos to be added
     * to the album.
     */
    @Nullable
    public List<AddVideoToAlbum> getAddVideoList() {
        if (mAddVideoList == null) {
            return null;
        }
        final List<AddVideoToAlbum> retVal = new ArrayList<>();
        for (final NamedWrapperForAdd curAdd : mAddVideoList) {
            retVal.add(curAdd.getAddVideoToAlbum());
        }
        return retVal;
    }

    private class NamedWrapperForAdd implements Serializable {

        private static final long serialVersionUID = 8713902512465812810L;

        @SerializedName("video")
        private final AddVideoToAlbum mAddVideoToAlbum;

        NamedWrapperForAdd(AddVideoToAlbum addVideoToAlbum) {
            mAddVideoToAlbum = addVideoToAlbum;
        }

        /**
         * @return The wrapped AddVideoToAlbum object.
         */
        AddVideoToAlbum getAddVideoToAlbum() {
            return mAddVideoToAlbum;
        }
    }

    private class NamedWrapperForRemove implements Serializable {

        private static final long serialVersionUID = -8122735684596463036L;

        @SerializedName("video")
        private final RemoveVideoFromAlbum mRemoveVideoFromAlbum;

        NamedWrapperForRemove(RemoveVideoFromAlbum removeVideoFromAlbum) {
            mRemoveVideoFromAlbum = removeVideoFromAlbum;
        }

        /**
         * @return The wrapped RemoveVideoFromAlbum object.
         */
        RemoveVideoFromAlbum getRemoveVideoFromAlbum() {
            return mRemoveVideoFromAlbum;
        }
    }


}
