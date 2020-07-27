package com.vimeo.networking2

import com.squareup.moshi.JsonClass

/**
 * This class is here for when you are declaring a generic type in Kotlin that also needs
 * to be used by Java and the type generator. We can't use Nothing because Java doesn't know
 * about this type and we can't use Void because it is not parcelable and breaks the type
 * generator.
 */
@JsonClass(generateAdapter = true)
data class Empty(private val neededForGenerator: Int = 666)