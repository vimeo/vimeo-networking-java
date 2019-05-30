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

    private class NamedWrapperForAdd implements Serializable{

        private static final long serialVersionUID = 8713902512465812810L;

        NamedWrapperForAdd(List<AddVideoToAlbum> addVideoList) {
            mAddVideosToAlbums = new ArrayList<>(addVideoList);
        }

        @SerializedName("video")
        public List<AddVideoToAlbum> mAddVideosToAlbums;
    }

    private class NamedWrapperForRemove implements Serializable{

        private static final long serialVersionUID = -8122735684596463036L;

        NamedWrapperForRemove(List<RemoveVideoFromAlbum> removeVideoList) {
            mRemoveVideosFromAlbums = new ArrayList<>(removeVideoList);
        }

        @SerializedName("video")
        public List<RemoveVideoFromAlbum> mRemoveVideosFromAlbums;
    }

    public ModifyVideosInAlbum(@Nullable List<RemoveVideoFromAlbum> removeVideoList,
                               @Nullable List<AddVideoToAlbum> addVideoList) {
        if (removeVideoList != null) {
            mRemoveVideoList = new NamedWrapperForRemove(removeVideoList);
        }
        if (addVideoList != null) {
            mAddVideoList = new NamedWrapperForAdd(addVideoList);
        }
    }

    @Nullable
    @SerializedName("remove")
    public NamedWrapperForRemove mRemoveVideoList;

    @Nullable
    @SerializedName("set")
    public NamedWrapperForAdd mAddVideoList;
}
