package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity

/**
 * Created by nicholas.doglio on 2020-01-06.
 */

@JsonClass(generateAdapter = true)
data class PublishJobConnection(
        @Json(name = "publish_blockers")
        val publishBlockers: PublishBlockers? = null,
        @Json(name = "publish_constraints")
        val publishConstraints: PublishConstraints? = null,
        @Json(name = "publish_destinations")
        val publishDestinations: PublishDestinations? = null,
        @Json(name = "options")
        val options: List<String>? = null,
        @Json(name = "uri")
        val uri: String? = null
) : Entity {
    override val identifier: String? = uri
}

