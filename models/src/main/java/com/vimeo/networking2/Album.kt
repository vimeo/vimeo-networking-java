@file:JvmName("AlbumUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.AlbumLayoutType
import com.vimeo.networking2.enums.AlbumThemeType
import com.vimeo.networking2.enums.SortType
import com.vimeo.networking2.enums.asEnum
import java.util.*

/**
 * Group of videos to share publicly or privately.
 */
@JsonClass(generateAdapter = true)
data class Album(

    /**
     * Hexadecimal color code for the decorative color.
     * For example, album videos use this color for player buttons.
     */
    @Json(name = "brand_color")
    val brandColor: String? = null,

    /**
     * The time in ISO 8601 format that the album was created.
     */
    @Json(name = "created_time")
    val createdTime: Date? = null,

    /**
     * Custom logo for the album.
     */
    @Json(name = "custom_logo")
    val customLogo: PictureCollection? = null,

    /**
     * A brief description of the album's content.
     */
    @Json(name = "description")
    val description: String? = null,

    /**
     * The total duration in seconds of all the videos in the album.
     */
    @Json(name = "duration")
    val duration: Int? = null,

    /**
     * Embed data for the album.
     */
    @Json(name = "embed")
    val embed: AlbumEmbed? = null,

    /**
     * Whether to hide the Vimeo navigation when viewing the album.
     */
    @Json(name = "hide_nav")
    val hideNav: Boolean? = null,

    /**
     * The album's layout preference.
     * @see Album.layoutType
     */
    @Json(name = "layout")
    val layout: String? = null,

    /**
     * The URL to access the album.
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * Metadata about the album.
     */
    @Json(name = "metadata")
    val metadata: Metadata<AlbumConnections, AlbumInteractions>? = null,

    /**
     * The time in ISO 8601 format when the album was last modified.
     */
    @Json(name = "modified_time")
    val modifiedTime: Date? = null,

    /**
     * The album's display name.
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * A list of 3 most recently added videos to the album.
     */
    @Json(name = "pictures")
    val pictures: List<PictureCollection>? = null,

    /**
     * The privacy settings of the album.
     */
    @Json(name = "privacy")
    val privacy: AlbumPrivacy? = null,

    /**
     * The album resource key.
     */
    @Json(name = "resource_key")
    val resourceKey: String? = null,

    /**
     * Whether album videos should use the review mode URL.
     */
    @Json(name = "review_mode")
    val reviewMode: Boolean? = null,

    /**
     * Sort type of the album.
     * @see Album.sortType
     */
    @Json(name = "sort")
    val sort: String? = null,

    /**
     * The album's color theme preference.
     * @see Album.themeType
     */
    @Json(name = "theme")
    val theme: String? = null,

    /**
     * The album's URI.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The owner of the album.
     */
    @Json(name = "user")
    val user: User? = null

) : Entity {

    override val identifier: String? = resourceKey

}

/**
 * @see Album.layout
 * @see AlbumLayoutType
 */
val Album.layoutType: AlbumLayoutType
    get() = layout.asEnum(AlbumLayoutType.UNKNOWN)

/**
 * @see Album.theme
 * @see AlbumThemeType
 */
val Album.themeType: AlbumThemeType
    get() = theme.asEnum(AlbumThemeType.UNKNOWN)

/**
 * @see Album.sort
 * @see SortType
 */
val Album.sortType: SortType
    get() = sort.asEnum(SortType.UNKNOWN)
