package com.vimeo.networking2

data class Picture(

        /**
         * Whether this picture is the active picture for its parent resource.
         */
        val active: Boolean? = null,

        /**
         * The upload URL for the picture. This field appears when you create the
         * picture resource for the first time.
         */
        val link: String? = null,

        /**
         * The picture's resource key string.
         */
        val resourceKey: String? = null,

        


)
