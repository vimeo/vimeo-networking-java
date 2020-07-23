package com.vimeo.networking2.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnnotatedDataClass(
    @Json(name = "foo")
    val foo: String
)