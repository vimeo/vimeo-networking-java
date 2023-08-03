package com.vimeo.networking2.richtext

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Attributes of [Mention] rich text node.
 *
 * @param label The label of the mention.
 * @param userId The user id of the user this mention represents.
 * @param name The name of the user this mention represents.
 * @param avatar The avatar URL of the user this mention represents.
 * @param email The email of the user this mention represents.
 */
@JsonClass(generateAdapter = true)
data class MentionAttrs(
    @Json(name = "label")
    val label: String? = null,

    @Json(name = "userId")
    val userId: Long? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "avatar")
    val avatar: String? = null,

    @Json(name = "email")
    val email: String? = null,
)
