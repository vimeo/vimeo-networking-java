package com.vimeo.networking2

import java.util.*

/**
 * Group DTO.
 */
data class Group(

    /**
     * The time in ISO 8601 format when the group was created.
     */
    val createdTime: Date? = null,

    /**
     * The group's description.
     */
    val description: String? = null,

    /**
     * The link to the group.
     */
    val link: String? = null,

    /**
     * Metadata about the group.
     */
    override val metadata: Metadata<GroupConnections, GroupInteractions>? = null,

    /**
     * The time in ISO 8601 format when the group was last modified.
     */
    val modifiedTime: Date? = null,

    /**
     * The group's display name.
     */
    val name: String? = null,

    /**
     * The active picture for this group.
     */
    val pictures: PictureCollection? = null,

    /**
     *The group's privacy settings.
     */
    val privacy: GroupPrivacy? = null,

    /**
     * The resource key of the group.
     */
    val resourceKey: String? = null,

    /**
     * The canonical relative URI of this group.
     */
    val uri: String? = null,

    /**
     * The owner of the group.
     */
    val user: User? = null

): Followable
