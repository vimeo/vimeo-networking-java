package com.vimeo.networking2

import java.util.*

/**
 * Folder data.
 */
data class Folder(

    /**
     * The time in ISO 8601 format when the folder was created.
     */
    val createdTime: String? = null,

    /**
     * The folder's metadata.
     */
    val metadata: Metadata<AlbumConnections, AlbumInteractions>? = null,

    /**
     * The time in ISO 8601 format when the folder was last modified.
     */
    val modifiedTime: Date? = null,

    /**
     * The name of the folder.
     */
    val name: String? = null,

    /**
     * The resource key string of the folder.
     */
    val resourceKey: String? = null,

    /**
     * The URI of the folder.
     */
    val uri: String? = null,

    /**
     * The owner of the folder.
     */
    val user: User? = null
)
