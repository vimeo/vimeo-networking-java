package com.vimeo.networking2

import com.squareup.moshi.Json
import com.vimeo.networking2.enums.EmbedPrivacyType
import com.vimeo.networking2.enums.ViewPrivacyType
import com.vimeo.networking2.enums.asEnum

/**
 * Stream privacy data.
 *
 * @param embedPrivacy The event's embed permission setting.
 * @param unlistedHash The hash for unlisted recurring live events.
 * @param viewPrivacy The general privacy setting for generated videos and the embed privacy of the entire collection.
 */
data class StreamPrivacy(

    @Json(name = "embed")
    val embedPrivacy: String? = null,

    @Json(name = "unlisted_hash")
    val unlistedHash: String? = null,

    @Json(name = "view")
    val viewPrivacy: String? = null,
)

/**
 * @see StreamPrivacy.embedPrivacy
 * @see EmbedPrivacyType
 */
val StreamPrivacy.embedPrivacyType: EmbedPrivacyType
    get() = embedPrivacy.asEnum(EmbedPrivacyType.UNKNOWN)

/**
 * @see Privacy.viewPrivacy
 * @see ViewPrivacyType
 */
val StreamPrivacy.viewPrivacyType: ViewPrivacyType
    get() = viewPrivacy.asEnum(ViewPrivacyType.UNKNOWN)
