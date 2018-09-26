package com.vimeo.networking2

import com.vimeo.networking2.enums.AlbumLayout
import java.util.*

data class Album(

        /**
         * Hexadecimal color code for the decorative color.
         * For example, album videos use this color for player buttons.
         */
        val brandColor: String?,

        /**
         * The time in ISO 8601 format that the album was created.
         */
        val createdTime: Date?,

        /**
         * A brief description of the album's content.
         */
        val description: String?,

        /**
         * The total duration in seconds of all the videos in the album.
         */
        val duration: Int?,

        /**
         * Embed data for the album.
         */
        val embed: Embed?,

        /**
         * Whether to hide the Vimeo navigation when viewing the album.
         */
        val hideNav: Boolean?,

        /**
         * The album's layout preference.
         */
        val layout: AlbumLayout?,

        /**
         * The URL to access the album.
         */
        val link: String?,

        /**
         * Metadata about the album.
         */
        val metadata: Metadata?,

        /**
         * The time in ISO 8601 format when the album was last modified.
         */
        val modifiedTime: Date?,

        /**
         * The album's display name.
         */
        val name: String?,

        /**
         * The active image for the album; defaults to the thumbnail of the last
         * video added to the album.
         */
        val pictures: PictureCollection?,

        /**
         * The privacy settings of the album.
         */
        val privacy: Privacy?,

        /**
         * Whether album videos should use the review mode URL.
         */
        val reviewMode: Boolean?,

        /**
         * Sort type of the album.
         */
        val sort: SortOptions?,

        /**
         * The album's color theme preference.
         */
        val theme: AlbumTheme?,

        /**
         * The album's URI.
         */
        val uri: String?,

        /**
         * The owner of the album.
         */
        val user: User?

)
