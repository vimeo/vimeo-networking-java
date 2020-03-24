package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.FollowableInteractions
import java.io.Serializable

/**
 * All actions that can be taken on groups.
 */
@JsonClass(generateAdapter = true)
data class GroupInteractions(

    /**
     * An action indicating that someone has joined the group.
     */
    @Json(name = "join")
    override val follow: GroupFollowInteraction? = null

) : FollowableInteractions, Serializable {

    companion object {
        private const val serialVersionUID = -30L
    }
}
