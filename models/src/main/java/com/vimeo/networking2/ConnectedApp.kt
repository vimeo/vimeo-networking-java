@file:JvmName("ConnectedAppUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.ConnectedAppType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * A [ConnectedApp] represents a connection to a social media platform. Some activities, like simultaneously live
 * stream to multiple destinations, or publishing across platforms, require requesting specific scopes. The scopes
 * required will always be returned in the [neededScopes] array.
 * - Note: Some properties are specific to a particular platform. These cases have been noted in the documentation
 * where relevant.
 *
 * @param dateAdded The time in ISO 8601 format when the connected app was added.
 * @param neededScopes The list of remaining scopes on this connected app that the user needs for a particular feature.
 * @param pages The list of third party pages that is associated with the user's account (Facebook and LinkedIn only).
 * @param publishCategories The list of third party categories that can be selected for a publish to social job
 * (Facebook and YouTube only).
 * @param userId The unique identifier for the user on this connected app.
 * @param userName  The user's display name on the connected app.
 * @param uri The API URI of this connected app.
 * @param isDataAccessExpired Whether or not the user's data access is expired (Facebook only).
 * @param rawType The type of the connected app. See [ConnectedApp.type].
 * @param resourceKey The resource key string of the ConnectedApp.
 */
@JsonClass(generateAdapter = true)
data class ConnectedApp(

    @Json(name = "add_date")
    val dateAdded: Date? = null,

    @Json(name = "needed_scopes")
    val neededScopes: ConnectedScopes? = null,

    @Json(name = "pages")
    val pages: List<PublishOptionItem>? = null,

    @Json(name = "publish_categories")
    val publishCategories: List<PublishOptionItem>? = null,

    @Json(name = "third_party_user_id")
    val userId: String? = null,

    @Json(name = "third_party_user_display_name")
    val userName: String? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "data_access_is_expired")
    val isDataAccessExpired: Boolean? = null,

    @Json(name = "type")
    val rawType: String? = null,

    @Json(name = "resource_key")
    val resourceKey: String? = null

) : Entity {
    override val identifier: String? = resourceKey
}

/**
 * @see ConnectedApp.rawType
 * @see ConnectedAppType
 */
val ConnectedApp.type: ConnectedAppType
    get() = rawType.asEnum(ConnectedAppType.UNKNOWN)
