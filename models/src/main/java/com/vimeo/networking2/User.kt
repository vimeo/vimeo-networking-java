@file:JvmName("UserUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.common.Followable
import com.vimeo.networking2.enums.ContentFilterType
import com.vimeo.networking2.enums.asEnumList
import java.util.*

/**
 * User information.
 */
@JsonClass(generateAdapter = true)
data class User(

    /**
     * The user's bio.
     */
    @Json(name = "bio")
    val bio: String? = null,

    /**
     * The user's content filters.
     * @see User.contentFilterTypes
     */
    @Json(name = "content_filter")
    val contentFilters: List<String>? = null,

    /**
     * The time in ISO 8601 format when the user account was created.
     */
    @Json(name = "created_time")
    val createdTime: Date? = null,

    /**
     * The user's email address.
     */
    @Json(name = "email")
    val email: String? = null,

    /**
     * An array of alternate emails for the user.
     */
    @Internal
    @Json(name = "emails")
    val emails: List<Email>? = null,

    /**
     * The absolute URL of this user's profile page.
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * Information about the user's live streaming quota.
     */
    @Json(name = "live_quota")
    val liveQuota: LiveQuota? = null,

    /**
     * The user's location.
     */
    @Json(name = "location")
    val location: String? = null,

    /**
     * The user's metadata.
     */
    @Json(name = "metadata")
    override val metadata: Metadata<UserConnections, UserInteractions>? = null,

    /**
     * The user's display name.
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * The active portrait of this user.
     */
    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    /**
     * User's preferences.
     */
    @Json(name = "preferences")
    val preferences: Preferences? = null,

    /**
     * The user's resource key string.
     */
    @Json(name = "resource_key")
    val resourceKey: String? = null,

    /**
     * Appears only when the user has upload access and is looking at their own user record.
     */
    @Json(name = "upload_quota")
    val uploadQuota: UploadQuota? = null,

    /**
     * The user's canonical relative URI.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The user's websites.
     */
    @Json(name = "websites")
    val websites: List<Website>? = null,

    /**
     * The user's membership.
     */
    @Json(name = "membership")
    val membership: Membership? = null

) : Followable, Entity {

    override val identifier: String? = resourceKey

}

/**
 * @see User.contentFilters
 * @see ContentFilterType
 */
val User.contentFilterTypes: List<ContentFilterType>
    get() = contentFilters.asEnumList(ContentFilterType.UNKNOWN)
