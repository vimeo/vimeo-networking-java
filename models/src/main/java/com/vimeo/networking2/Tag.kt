package com.vimeo.networking2

data class Tag(
        /**
         * The normalized canonical tag name.
         */
        val canonical: String,

        /**
         * Metadata about the group.
         */
        val metadata: Metadata,

        /**
         * The tag value.
         */
        val name: String,

        /**
         * The tag's resource key string.
         */
        val resourceKey: String,

        /**
         * The canonical relative URI of the tag.
         */
        val uri: String

)
