package com.vimeo.networking2

/**
 * All actions that can be taken on groups.
 */
data class GroupInteractions(

    /**
     * An action indicating that someone has joined the group.
     */
    override val follow: GroupFollowInteraction? = null

): FollowableInteractions<UpdatableInteraction>
