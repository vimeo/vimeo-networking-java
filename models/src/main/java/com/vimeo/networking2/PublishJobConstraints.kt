package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * An object representing the information on the publishing constraints for each social network.
 */
@JsonClass(generateAdapter = true)
data class PublishJobConstraints(
        /**
         * The publish constraints for Facebook.
         */
        @Json(name = "facebook")
        val facebook: PlatformConstraint? = null,

        /**
         * The publish constraints for LinkedIn.
         */
        @Json(name = "linkedin")
        val linkedin: PlatformConstraint? = null,

        /**
         * The publish constraints for YouTube.
         */
        @Json(name = "youtube")
        val youtube: PlatformConstraint? = null,

        /**
         * The publish constraints for Twitter.
         */
        @Json(name = "twitter")
        val twitter: PlatformConstraint? = null
) : Serializable {

    companion object {
        private const val serialVersionUID = -52L
    }
}
