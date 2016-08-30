package com.vimeo.networking.model;

import org.jetbrains.annotations.Nullable;

/**
 * An interface that should be implemented by
 * any model object that wants to be able to
 * be followed.
 * <p/>
 * Created by restainoa on 8/29/16.
 */
public interface Followable {

    @Nullable
    Interaction getFollowInteraction();

    boolean canFollow();

    boolean isFollowing();

}

