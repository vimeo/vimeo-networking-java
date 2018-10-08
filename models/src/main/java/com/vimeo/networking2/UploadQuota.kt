package com.vimeo.networking2

data class UploadQuota(

    val lifetime: Lifetime? = null,

    val periodic: Periodic? = null,

    val quota: Quota? = null,

    val space: Space? = null

)
