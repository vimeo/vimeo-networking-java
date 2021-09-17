package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.ExplorePageType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * Data representing an Explore Page in the Vimeo Explore CMS system;  This is content generally shown on the "Watch"
 * section of Vimeo.com.  A page is contains an array of components. which are exposed through a connection within the
 * associated Metadata of this class.
 *
 * @param uri The uri of the explore page.
 * @param title The title of the explore page.
 * @param createdTime The timestamp for when the explore page was created.
 * @param rawType A string value for the type of page; [type] is an enumeration of this value.
 * @param isActive Is the page is active or not.
 * @param metadata metadata about the explore page;  You can find connections here for [ExploreComponent] which live
 * under this page.
 */
@JsonClass(generateAdapter = true)
data class ExplorePage(
    @Json(name = "uri")
    val uri: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "created_time")
    val createdTime: Date? = null,
    @Json(name = "type")
    val rawType: String? = null,
    @Json(name = "active")
    val isActive: Boolean? = null,
    @Json(name = "metadata")
    val metadata: MetadataConnections<ExplorePageConnections>? = null
) : Entity {
    override val identifier: String? = uri
}

/**
 * Provides an enumeration of [ExplorePage.rawType].
 */
val ExplorePage.type: ExplorePageType get() = rawType.asEnum(ExplorePageType.UNKNOWN)
