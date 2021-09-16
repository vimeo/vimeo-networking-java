package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.ExploreComponentType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * A component within the Explore CMS system; This represents a modular piece of content within the CMS;  Each Component
 * may potentially have more components underneath it through it's metadata, via [ExploreComponentConnections].
 *
 * @param uri The explore component's uri
 * @param title The title of the explore component
 * @param shortTitle A shortened title for the explore component
 * @param createdTime A timestamp of when the component was first created
 * @param position The position of the component amongst it's siblings in an array of components, used for UX ordering
 * @param rawType A string value for the type of component; [type] is an enumeration of this value
 * @param metadata metadata about the explore component; You can find connections here for [ExploreComponent]s which
 * have a child relationship to this component.
 * @param source The underlying source data that belongs to this component
 *
 */
@JsonClass(generateAdapter = true)
data class ExploreComponent(
    @Json(name = "uri")
    val uri: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "short_title")
    val shortTitle: String? = null,
    @Json(name = "created_time")
    val createdTime: Date? = null,
    @Json(name = "position")
    val position: Int? = null,
    @Json(name = "type")
    val rawType: String? = null,
    @Json(name = "metadata")
    val metadata: MetadataConnections<ExploreComponentConnections>? = null,
    @Json(name = "source")
    val source: ExploreComponentSource? = null
) : Entity {
    override val identifier: String? = uri
}

/**
 *  Provides an enumeration of [ExploreComponent.rawType]
 */
val ExploreComponent.type: ExploreComponentType get() = rawType.asEnum(ExploreComponentType.UNKNOWN)
