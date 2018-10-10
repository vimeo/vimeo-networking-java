package com.vimeo.networking2

import com.vimeo.networking2.enums.AlbumLayoutType
import com.vimeo.networking2.enums.AlbumThemeType
import com.vimeo.networking2.enums.SortType
import java.util.*

/**
 * Group of videos to share publicly or privately.
 */
data class Album(

    /**
     * Hexadecimal color code for the decorative color.
     * For example, album videos use this color for player buttons.
     */
    val brandColor: String? = null,

    /**
     * The time in ISO 8601 format that the album was created.
     */
    val createdTime: Date? = null,

    /**
     * A brief description of the album's content.
     */
    val description: String? = null,

    /**
     * The total duration in seconds of all the videos in the album.
     */
    val duration: Int? = null,

    /**
     * Embed data for the album.
     */
    val embed: AlbumEmbed? = null,

    /**
     * Whether to hide the Vimeo navigation when viewing the album.
     */
    val hideNav: Boolean? = null,

    /**
     * The album's layout preference.
     */
    val layout: AlbumLayoutType? = null,

    /**
     * The URL to access the album.
     */
    val link: String? = null,

    /**
     * Metadata about the album.
     */
    val metadata: Metadata<AlbumConnections, AlbumInteractions>? = null,

    /**
     * The time in ISO 8601 format when the album was last modified.
     */
    val modifiedTime: Date? = null,

    /**
     * The album's display name.
     */
    val name: String? = null,

    /**
     * The active image for the album; defaults to the thumbnail of the last
     * video added to the album.
     */
    val pictures: PictureCollection? = null,

    /**
     * The privacy settings of the album.
     */
    val privacy: AlbumPrivacy? = null,

    /**
     * Whether album videos should use the review mode URL.
     */
    val reviewMode: Boolean? = null,

    /**
     * Sort type of the album.
     */
    val sort: SortType? = null,

    /**
     * The album's color theme preference.
     */
    val theme: AlbumThemeType? = null,

    /**
     * The album's URI.
     */
    val uri: String? = null,

    /**
     * The owner of the album.
     */
    val user: User? = null

)
