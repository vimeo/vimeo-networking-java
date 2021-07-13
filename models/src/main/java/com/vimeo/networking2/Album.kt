@file:JvmName("AlbumUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.AlbumLayoutType
import com.vimeo.networking2.enums.AlbumThemeType
import com.vimeo.networking2.enums.SortType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * Group of videos to share publicly or privately.
 *
 * @param brandColor Hexadecimal color code for the decorative color. For example, album videos use this color for
 * player buttons.
 * @param createdTime The time in ISO 8601 format that the album was created.
 * @param customLogo Custom logo for the album.
 * @param description A brief description of the album's content.
 * @param duration The total duration in seconds of all the videos in the album.
 * @param embed Embed data for the album.
 * @param hideNav Whether to hide the Vimeo navigation when viewing the album.
 * @param layout The album's layout preference. See [Album.layoutType].
 * @param link The URL to access the album.
 * @param metadata Metadata about the album.
 * @param modifiedTime The time in ISO 8601 format when the album was last modified.
 * @param name The album's display name.
 * @param pictures A list of 3 most recently added videos to the album.
 * @param privacy The privacy settings of the album.
 * @param resourceKey The album resource key.
 * @param reviewMode Whether album videos should use the review mode URL.
 * @param sort Sort type of the album. See [Album.sortType].
 * @param theme The album's color theme preference. See [Album.themeType].
 * @param uri The album's URI.
 * @param user The owner of the album.
 */
@JsonClass(generateAdapter = true)
data class Album(

    @Json(name = "brand_color")
    val brandColor: String? = null,

    @Json(name = "created_time")
    val createdTime: Date? = null,

    @Json(name = "custom_logo")
    val customLogo: PictureCollection? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "duration")
    val duration: Int? = null,

    @Json(name = "embed")
    val embed: AlbumEmbed? = null,

    @Json(name = "hide_nav")
    val hideNav: Boolean? = null,

    @Json(name = "layout")
    val layout: String? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "metadata")
    val metadata: Metadata<AlbumConnections, AlbumInteractions>? = null,

    @Json(name = "modified_time")
    val modifiedTime: Date? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "pictures")
    val pictures: List<PictureCollection>? = null,

    @Json(name = "privacy")
    val privacy: AlbumPrivacy? = null,

    @Json(name = "resource_key")
    val resourceKey: String? = null,

    @Json(name = "review_mode")
    val reviewMode: Boolean? = null,

    @Json(name = "sort")
    val sort: String? = null,

    @Json(name = "theme")
    val theme: String? = null,

    @Json(name = "uri")
    val uri: String? = null,

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
