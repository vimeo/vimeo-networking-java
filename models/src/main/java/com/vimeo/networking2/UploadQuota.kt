package com.vimeo.networking2

data class UploadQuota(

    val lifetime: Lifetime?,

    val periodic: Periodic?,

    val quota: Quota?,

    val space: Space?

)
