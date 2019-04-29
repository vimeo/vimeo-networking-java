package com.vimeo.networking2

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.UpdatableInteraction
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Follow a object.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class FollowInteraction(

    @Json(name = "added")
    override val added: Boolean? = null,

    @Json(name = "added_time")
    override val addedTime: Date? = null,

    @Json(name = "options")
    override val options: List<String>? = null,

    @Json(name = "uri")
    override val uri: String? = null

) : UpdatableInteraction, Parcelable
