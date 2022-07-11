package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a live event.
 *
 * @param liveVideo The connection for the live video for a live event.
 * @param pictures The connection for the thumbnail pictures for a live event.
 * @param preLiveVideo The connection for the event's pre-live video, where applicable.
 * A pre-live video is either activated or in the process of being activated.
 * @param teamMembers The connection for team members of the live event owner's team.
 * @param videos The connection for the videos that belong to the live event.
 */
@JsonClass(generateAdapter = true)
data class LiveEventConnections(

    @Json(name = "live_video")
    val liveVideo: LiveVideoConnection? = null,

    @Json(name = "pictures")
    val pictures: DefaultConnection? = null,

    @Json(name = "pre_live_video")
    val preLiveVideo: LiveVideoConnection? = null,

    @Json(name = "team_member")
    val teamMembers: DefaultConnection? = null,

    @Json(name = "videos")
    val videos: BasicConnection? = null

)
