package com.vimeo.networking2

import com.vimeo.networking2.enums.UploadSpaceType

data class Space(

    /**
     * The number of bytes remaining in your upload quota.
     */
    val free: Long?,

    /**
     * The maximum number of bytes allotted to your upload quota.
     */
    val max: Long?,

    /**
     * Whether the values of the upload_quota.space fields are for the lifetime quota or
     * the periodic quota.
     */
    val showing: UploadSpaceType?,

    /**
     * The number of bytes that you've already uploaded against your quota.
     */
    val used: Long?

)
