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

    public ModifyVideosInAlbum(@Nullable List<RemoveVideoFromAlbum> removeVideoList,
                               @Nullable List<AddVideoToAlbum> addVideoList) {
        if (removeVideoList != null) {
            mRemoveVideoList = new ArrayList<>(removeVideoList);
        }
        if (addVideoList != null) {
            mAddVideoList = new ArrayList<>(addVideoList);
        }
    }

    @Nullable
    @SerializedName("remove")
    public List<RemoveVideoFromAlbum> mRemoveVideoList;

    @Nullable
    @SerializedName("set")
    public List<AddVideoToAlbum> mAddVideoList;
}
