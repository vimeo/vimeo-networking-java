package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.ExploreComponentSourceType
import com.vimeo.networking2.enums.asEnum

/**
 * Source data hosted under an [ExploreComponent].  Generally there will always be a [type]/[rawType], and depending on
 * the value of that type, one of the other members will have a non null value. For instance, if [type] was
 * [ExploreComponentSourceType.VIDEO] then we would expect [video] to have a value, but other members(other than [video]
 * or [rawType]/[type]) would be null.
 *
 * @param video Video data for the source when [type] has a value of [ExploreComponentSourceType.VIDEO]
 * @param album Album data for the source when [type] has a value of [ExploreComponentSourceType.ALBUM]
 * @param channel Channel data for the source when [type] has a value of [ExploreComponentSourceType.CHANNEL]
 * @param rawType The type of data exposed through this class representing a data source
 */
@JsonClass(generateAdapter = true)
data class ExploreComponentSource(
    @Json(name = "video")
    val video: Video? = null,
    @Json(name = "album")
    val album: Album? = null,
    @Json(name = "channel")
    val channel: Channel? = null,
    @Json(name = "type")
    val rawType: String? = null
)

/**
 *  Provides an enumeration of [ExploreComponentSource.rawType].
 */
val ExploreComponentSource.type: ExploreComponentSourceType get() = rawType.asEnum(ExploreComponentSourceType.UNKNOWN)
