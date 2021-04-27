@file:JvmName("FolderUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.SlackLanguagePreferenceType
import com.vimeo.networking2.enums.SlackUserPreferenceType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * A folder that acts as a container for a list of [Videos][Video] that can be fetched via
 * a uri defined in [Metadata.connections].
 *
 * @param createdDate The time in ISO 8601 format when the folder was created.
 * @param lastUserActionEventDate The time in ISO 8601 format when a user last performed an action on the folder.
 * @param link The link to the folder on Vimeo.
 * @param metadata The folder's metadata.
 * @param lastModifiedDate The time in ISO 8601 format when the folder was last modified.
 * @param name The name of the folder.
 * @param privacy The [FolderPrivacy] that defines the public visibility of the folder.
 * @param resourceKey The resource key string of the folder.
 * @param slackIncomingWebhooksId The Slack webhook ID for the folder.
 * @param slackIntegrationChannel The Slack integration channel for the folder.
 * @param slackLanguagePreference The language preference for Slack notifications about the folder. See
 * [slackLanguagePreferenceType].
 * @param slackUserPreference User preferences for Slack notifications about the folder. See [slackUserPreferenceType].
 * @param uri The folder's canonical relative URI.
 * @param user The folder's owner.
 */
@JsonClass(generateAdapter = true)
data class Folder(

    @Json(name = "created_time")
    val createdDate: Date? = null,

    @Json(name = "last_user_action_event_date")
    val lastUserActionEventDate: Date? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "metadata")
    val metadata: Metadata<FolderConnections, FolderInteractions>? = null,

    @Json(name = "modified_time")
    val lastModifiedDate: Date? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "privacy")
    val privacy: FolderPrivacy? = null,

    @Json(name = "resource_key")
    val resourceKey: String? = null,

    @Json(name = "slack_incoming_webhooks_id")
    val slackIncomingWebhooksId: String? = null,

    @Json(name = "slack_integration_channel")
    val slackIntegrationChannel: String? = null,

    @Json(name = "slack_language_preference")
    val slackLanguagePreference: String? = null,

    @Json(name = "slack_user_preferences")
    val slackUserPreference: String? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "user")
    val user: User? = null
) : Entity {
    override val identifier: String? = resourceKey
}

/**
 * @see Folder.slackLanguagePreference
 * @see SlackLanguagePreferenceType
 */
val Folder.slackLanguagePreferenceType: SlackLanguagePreferenceType
    get() = slackLanguagePreference.asEnum(SlackLanguagePreferenceType.UNKNOWN)

/**
 * @see Folder.slackUserPreference
 * @see SlackUserPreferenceType
 */
val Folder.slackUserPreferenceType: SlackUserPreferenceType
    get() = slackUserPreference.asEnum(SlackUserPreferenceType.UNKNOWN)
