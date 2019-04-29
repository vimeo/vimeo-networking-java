package com.vimeo.networking2

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.FollowableInteractions
import kotlinx.android.parcel.Parcelize

/**
 * All actions that can be taken on a category.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class CategoryInteractions(

    @Json(name = "follow")
    override val follow: FollowInteraction? = null

): FollowableInteractions, Parcelable
