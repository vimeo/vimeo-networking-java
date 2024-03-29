@file:JvmName("ChannelFollowInteractionUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.UpdatableInteraction
import com.vimeo.networking2.enums.FollowType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * Follow a channel interaction.
 *
 * @param rawType Whether the authenticated user is a moderator or subscriber. See [ChannelFollowInteraction.rawType].
 */
@JsonClass(generateAdapter = true)
data class ChannelFollowInteraction(

    @Json(name = "added")
    override val added: Boolean? = null,

    @Json(name = "added_time")
    override val addedTime: Date? = null,

    @Json(name = "options")
    override val options: List<String>? = null,

    @Json(name = "uri")
    override val uri: String? = null,

    @Json(name = "type")
    val rawType: String? = null

) : UpdatableInteraction

/**
 * @see ChannelFollowInteraction.rawType
 * @see FollowType
 */
val ChannelFollowInteraction.type: FollowType
    get() = rawType.asEnum(FollowType.UNKNOWN)
