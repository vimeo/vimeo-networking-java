package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity

/**
 * A tag which is used to tag videos.
 *
 * @param canonical The normalized canonical tag name.
 * @param metadata AlbumMetadata about the group.
 * @param name The tag value.
 * @param resourceKey The tag's resource key string.
 * @param uri The canonical relative URI of the tag.
 */
@JsonClass(generateAdapter = true)
data class Tag(

    @Json(name = "canonical")
    val canonical: String? = null,

    @Json(name = "metadata")
    val metadata: Metadata<AlbumConnections, AlbumInteractions>? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "resource_key")
    val resourceKey: String? = null,

    @Json(name = "uri")
    val uri: String? = null

) : Entity {
    override val identifier: String? = resourceKey
}
