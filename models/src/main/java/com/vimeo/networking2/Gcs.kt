package com.vimeo.networking2

data class Gcs(

        /**
         * Expected ending byte range for the current upload_link.
         */
        val endByte: Int?,

        /**
         * Expected starting byte size for the current upload_link.
         */
        val startByte: Int?,

        /**
         * Link for uploading file chunk to.
         */
        val uploadLink: String?
)
