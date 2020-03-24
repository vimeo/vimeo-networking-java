package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * All actions that can be taken on albums.
 */
@JsonClass(generateAdapter = true)
data class AlbumInteractions(

        /**
         * An action indicating that the authenticated user is an admin of the album and may therefore
         * add custom logos. This data requires a bearer token with the private scope.
         */
        @Json(name = "add_logos")
        val addLogos: BasicInteraction? = null,

        /**
         * An action indicating that the authenticated user is an admin of the album and may therefore
         * add videos. This data requires a bearer token with the private scope.
         */
        @Json(name = "add_videos")
        val addVideos: BasicInteraction? = null,

        /**
         * An interaction that will be present in [Album] objects returned from a
         * request to an "available_albums" connection on a video object
         * (/videos/{video_id}/available_albums). The [BasicInteraction.uri]
         * will provide the endpoint needed to complete a subsequent add/remove
         * action to add/remove the related video to/from the album. In the event that
         * the video is not yet added to the album, [BasicInteraction.options] will
         * contain "PUT". In the event that the video is already added to the album,
         * [BasicInteraction.options] will contain "DELETE".This data requires a
         * bearer token with the private scope.
         */
        @Json(name = "add_to")
        val addTo: BasicInteraction? = null

) : Serializable {
    companion object {
        private const val serialVersionUID = -53L
    }
}
