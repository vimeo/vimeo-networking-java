package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An object that is used to patch video addition and deletion updates to an Album.
 */
public class ModifyVideosInAlbum implements Serializable {

    private static final long serialVersionUID = -3094719083671086785L;

    private class NamedWrapperForAdd implements Serializable {

        private static final long serialVersionUID = 8713902512465812810L;

        NamedWrapperForAdd(AddVideoToAlbum addVideoToAlbum) {
            mAddVideoToAlbum = addVideoToAlbum;
        }

        @SerializedName("video")
        public AddVideoToAlbum mAddVideoToAlbum;
    }

    private class NamedWrapperForRemove implements Serializable {

        private static final long serialVersionUID = -8122735684596463036L;

        NamedWrapperForRemove(RemoveVideoFromAlbum removeVideoFromAlbum) {
            mRemoveVideoFromAlbum = removeVideoFromAlbum;
        }

        @SerializedName("video")
        public RemoveVideoFromAlbum mRemoveVideoFromAlbum;
    }

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

    @Nullable
    @SerializedName("remove")
    public List<NamedWrapperForRemove> mRemoveVideoList;

    @Nullable
    @SerializedName("set")
    public List<NamedWrapperForAdd> mAddVideoList;
}
