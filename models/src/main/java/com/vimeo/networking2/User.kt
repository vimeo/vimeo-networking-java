@file:JvmName("UserUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.common.Followable
import com.vimeo.networking2.enums.ContentFilterType
import com.vimeo.networking2.enums.asEnumList
import java.util.Date

/**
 * User information.
 *
 * @param bio The user's bio.
 * @param contentFilters The user's content filters. See [User.contentFilterTypes].
 * @param createdTime The time in ISO 8601 format when the user account was created.
 * @param email The user's email address.
 * @param emails An array of alternate emails for the user.
 * @param isCreator Whether or not this user is the creator of the parent resource (e.g. [Video]) in which it is
 * embedded.
 * @param link The absolute URL of this user's profile page.
 * @param liveQuota Information about the user's live streaming quota.
 * @param location The user's location.
 * @param name The user's display name.
 * @param pictures The active portrait of this user.
 * @param preferences User's preferences.
 * @param resourceKey The user's resource key string.
 * @param uploadQuota The user's upload quota. Appears only when the logged in user has upload access and is looking at
 * their own user record.
 * @param uri The user's canonical relative URI.
 * @param websites The user's websites.
 * @param membership The user's membership.
 */
@JsonClass(generateAdapter = true)
data class User(

    @Json(name = "bio")
    val bio: String? = null,

    @Json(name = "content_filter")
    val contentFilters: List<String>? = null,

    @Json(name = "created_time")
    val createdTime: Date? = null,

    @Json(name = "email")
    val email: String? = null,

    @Internal
    @Json(name = "emails")
    val emails: List<Email>? = null,

    @Json(name = "is_creator")
    val isCreator: Boolean? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "live_quota")
    val liveQuota: LiveQuota? = null,

    @Json(name = "location")
    val location: String? = null,

    @Json(name = "metadata")
    override val metadata: Metadata<UserConnections, UserInteractions>? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    @Json(name = "preferences")
    val preferences: Preferences? = null,

    @Json(name = "resource_key")
    val resourceKey: String? = null,

    @Json(name = "upload_quota")
    val uploadQuota: UploadQuota? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "websites")
    val websites: List<Website>? = null,

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
