@file:JvmName("FolderPrivacyUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.FolderViewPrivacyType
import com.vimeo.networking2.enums.asEnum

/**
 * The privacy set for a folder.
 */
@JsonClass(generateAdapter = true)
data class FolderPrivacy(

    /**
     * Who can view the folder.
     * @see FolderPrivacy.viewPrivacyType
     */
    @Json(name = "view")
    val viewPrivacy: String? = null
)

/**
 * @see FolderPrivacy.viewPrivacy
 * @see FolderViewPrivacyType
 */
val FolderPrivacy.viewPrivacyType: FolderViewPrivacyType
    get() = viewPrivacy.asEnum(FolderViewPrivacyType.UNKNOWN)
