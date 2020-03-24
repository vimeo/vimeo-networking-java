package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * An object representing information on previous attempts to publish to
 * third-party social networks.
 */
@JsonClass(generateAdapter = true)
data class PublishJobAttempts(

    /**
     * @return true or false depending on whether a previous attempt was made to publish the
     * video to Facebook. Note that if a previous attempt failed, this value will still be true.
     */
    @Json(name = "facebook")
    val facebook: Boolean? = null,

    /**
     * @return true or false depending on whether a previous attempt was made to publish the
     * video to YouTube. Note that if a previous attempt failed, this value will still be true.
     */
    @Json(name = "youtube")
    val youtube: Boolean? = null,

    /**
     * @return true or false depending on whether a previous attempt was made to publish the
     * video to LinkedIn. Note that if a previous attempt failed, this value will still be true.
     */
    @Json(name = "linkedin")
    val linkedin: Boolean? = null,

    /**
     * @return true or false depending on whether a previous attempt was made to publish the
     * video to Twitter. Note that if a previous attempt failed, this value will still be true.
     */
    @Json(name = "twitter")
    val twitter: Boolean? = null

) : Serializable {

    companion object {
        private const val serialVersionUID = -64L
    }
}
