package com.vimeo.networking2

data class VideoBadge(

        /**
         * The festival that this badge represents.
         */
        val festival: String?,

        /**
         * The link for the badge
         */
        val link: String,

        /**
         * The badge image.
         */
        val pictures: PictureCollection,

        /**
         * The name of the badge.
         */
        val text: String,

        /**
         * The type of the badge.
         */
        val type: VideoBadgeType

)
