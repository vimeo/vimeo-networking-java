package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import java.io.Serializable

/**
 * A Connection to provide the Publish to Social data for this video.
 */
@JsonClass(generateAdapter = true)
data class PublishJobConnection(

        /**
         * An object representing the blockers for each platform preventing the video from being published.
         */
        @Json(name = "publish_blockers")
        val publishBlockers: PublishJobBlockers? = null,

        /**
         * An object representing publish constraints for each social media platform.
         */
        @Json(name = "publish_constraints")
        val publishJobConstraints: PublishJobConstraints? = null,

        /**
         * An object representing whether attempts have been made to publish
         * the video to third party social platform destinations.
         */
        @Json(name = "publish_destinations")
        val publishJobAttempts: PublishJobAttempts? = null,

        /**
         * An array of HTTP methods permitted on this URI
         */
        @Json(name = "options")
        val options: List<String>? = null,

        /**
         *  The API URI that resolves to the connection data.
         */
        @Json(name = "uri")
        val uri: String? = null

) : Entity, Serializable {

    override val identifier: String? = uri

    companion object {
        private const val serialVersionUID = -35L
    }
}
