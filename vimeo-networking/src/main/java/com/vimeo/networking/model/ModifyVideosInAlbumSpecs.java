package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * An object that is used to patch video addition and deletion updates to an Album.
 */
@SuppressWarnings("unused")
public class ModifyVideosInAlbumSpecs implements Serializable {

    private static final long serialVersionUID = -3094719083671086785L;

    @Nullable
    @SerializedName("remove")
    private Set<NamedWrapperForRemove> mRemoveVideoSet;

    @Nullable
    @SerializedName("set")
    private Set<NamedWrapperForAdd> mAddVideoSet;

    public ModifyVideosInAlbumSpecs(@Nullable Set<RemoveVideoFromAlbum> removeVideoSet,
                                    @Nullable Set<AddVideoToAlbum> addVideoSet) {
        if (removeVideoSet != null) {
            mRemoveVideoSet = new LinkedHashSet<>();
            for (final RemoveVideoFromAlbum curRemove : removeVideoSet) {
                mRemoveVideoSet.add(new NamedWrapperForRemove(curRemove));
            }
        }
        if (addVideoSet != null) {
            mAddVideoSet = new LinkedHashSet<>();
            for (final AddVideoToAlbum curAdd : addVideoSet) {
                mAddVideoSet.add(new NamedWrapperForAdd(curAdd));
            }
        }
    }

    /**
     * @return A nullable list of RemoveVideoFromAlbum objects, containing the wrapped list of videos to be removed
     * from the album.
     */
    @Nullable
    public Set<RemoveVideoFromAlbum> getRemoveVideoSet() {
        if (mRemoveVideoSet == null) {
            return null;
        }
        final Set<RemoveVideoFromAlbum> retVal = new LinkedHashSet<>();
        for (final NamedWrapperForRemove curRemove : mRemoveVideoSet) {
            retVal.add(curRemove.getRemoveVideoFromAlbum());
        }
        return retVal;
    }

    /**
     * @return A nullable list of AddVideoToAlbum objects, containing the wrapped list of videos to be added
     * to the album.
     */
    @Nullable
    public Set<AddVideoToAlbum> getAddVideoSet() {
        if (mAddVideoSet == null) {
            return null;
        }
        final Set<AddVideoToAlbum> retVal = new LinkedHashSet<>();
        for (final NamedWrapperForAdd curAdd : mAddVideoSet) {
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
