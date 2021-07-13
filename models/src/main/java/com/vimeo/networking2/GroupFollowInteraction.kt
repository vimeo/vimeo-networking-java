@file:JvmName("GroupFollowInteractionUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.UpdatableInteraction
import com.vimeo.networking2.enums.FollowType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * Follow a group interaction.
 *
 * @param rawType Whether the authenticated user is a moderator or subscriber. See [GroupFollowInteraction.type].
 * @param title The user's title, or the null value if not applicable.
 */
@JsonClass(generateAdapter = true)
data class GroupFollowInteraction(

    @Json(name = "added")
    override val added: Boolean? = null,

    @Json(name = "added_time")
    override val addedTime: Date? = null,

    @Json(name = "options")
    override val options: List<String>? = null,

    @Json(name = "uri")
    override val uri: String? = null,

    @Json(name = "type")
    val rawType: String? = null,

    @Json(name = "title")
    val title: String? = null

) : UpdatableInteraction

/**
 * @see GroupFollowInteraction.rawType
 * @see FollowType
 */
val GroupFollowInteraction.type: FollowType
    get() = rawType.asEnum(FollowType.UNKNOWN)
