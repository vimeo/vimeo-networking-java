package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity

@JsonClass(generateAdapter = true)
data class Tag(

    /**
     * The normalized canonical tag name.
     */
    @Json(name = "canonical")
    val canonical: String? = null,

    /**
     * AlbumMetadata about the group.
     */
    @Json(name = "metadata")
    val metadata: Metadata<AlbumConnections, AlbumInteractions>? = null,

    /**
     * The tag value.
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * The tag's resource key string.
     */
    @Json(name = "resource_key")
    val resourceKey: String? = null,

    /**
     * The canonical relative URI of the tag.
     */
    @Json(name = "uri")
    val uri: String? = null

) : Entity {

    override val identifier: String? = resourceKey

}
