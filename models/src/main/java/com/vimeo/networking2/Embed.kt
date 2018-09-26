package com.vimeo.networking2

data class Embed(
        /**
         * The responsive HTML code to embed the playlist on a website. This is present only
         * when privacy.view is not password and when the album has embeddable clips.
         */
        val html: String
)
