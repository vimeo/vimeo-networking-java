package com.vimeo.networking2

import java.util.*

data class Folder(

    /**
     * The time in ISO 8601 format when the folder was created.
     */
    val createdTime: String?,

    /**
     * The folder's metadata.
     */
    val metadata: Metadata?,

    /**
     * The time in ISO 8601 format when the folder was last modified.
     */
    val modifiedTime: Date?,

    /**
     * The name of the folder.
     */
    val name: String?,

    /**
     * The resource key string of the folder.
     */
    val resourceKey: String?,

    /**
     * The URI of the folder.
     */
    val uri: String?,

    /**
     * The owner of the folder.
     */
    val user: User?
)
