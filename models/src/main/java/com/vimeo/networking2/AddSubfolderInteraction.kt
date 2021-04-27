package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Interaction

/**
 * The interaction for adding a subfolder to a parent folder.
 *
 * @param canAddSubfolders Whether the folder can contain a subfolder.
 * @param contentType The content type.
 * @param subfolderDepthLimitReached Whether the user has reached the maximum subfolder depth.
 */
@JsonClass(generateAdapter = true)
data class AddSubfolderInteraction(

    @Json(name = "can_add_subfolders")
    val canAddSubfolders: Boolean? = null,

    @Json(name = "content_type")
    val contentType: String? = null,

    @Json(name = "subfolder_depth_limit_reached")
    val subfolderDepthLimitReached: Boolean? = null,

    @Json(name = "options")
    override val options: List<String>? = null,

    @Json(name = "uri")
    override val uri: String? = null
) : Interaction
