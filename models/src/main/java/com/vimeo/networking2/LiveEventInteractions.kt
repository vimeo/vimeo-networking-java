package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Interactions for a live event.
 *
 * @param activate Information about where and how to activate the live event.
 * @param delete Information about where and how to delete an item.
 * @param edit Information about where and how to edit an item.
 *
 */
@JsonClass(generateAdapter = true)
data class LiveEventInteractions(

    @Json(name = "activate")
    val activate: BasicInteraction? = null,

    @Json(name = "delete")
    val delete: BasicInteraction? = null,

    @Json(name = "edit")
    val edit: BasicInteraction? = null
)
