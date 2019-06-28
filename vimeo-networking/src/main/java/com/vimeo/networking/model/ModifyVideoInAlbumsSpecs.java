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
public class ModifyVideoInAlbumsSpecs implements Serializable {

    private static final long serialVersionUID = -1909177613077524718L;

    @Nullable
    @SerializedName("remove")
    private final Set<RemoveVideoFromAlbum> mRemoveVideoSet;

    @Nullable
    @SerializedName("add")
    private final Set<AddVideoToAlbum> mAddVideoSet;

    public ModifyVideoInAlbumsSpecs(@Nullable Set<RemoveVideoFromAlbum> removeVideoSet,
                                    @Nullable Set<AddVideoToAlbum> addVideoSet) {
        mRemoveVideoSet = removeVideoSet != null ? new LinkedHashSet<>(removeVideoSet) : null;
        mAddVideoSet = addVideoSet != null ? new LinkedHashSet<>(addVideoSet) : null;
    }

    /**
     * @return A nullable set of RemoveVideoFromAlbum objects, containing the wrapped list of albums from which a
     * video will be removed.
     */
    @Nullable
    public Set<RemoveVideoFromAlbum> getRemoveVideoSet() {
        if (mRemoveVideoSet == null) {
            return null;
        }
        return new LinkedHashSet<>(mRemoveVideoSet);
    }

    /**
     * @return A nullable set of AddVideoToAlbum objects, containing the wrapped list of albums from which a video
     * will be added.
     */
    @Nullable
    public Set<AddVideoToAlbum> getAddVideoSet() {
        if (mAddVideoSet == null) {
            return null;
        }
        return new LinkedHashSet<>(mAddVideoSet);
    }

}
