package com.vimeo.networking2

import com.vimeo.networking2.enums.AccountType
import com.vimeo.networking2.enums.ContentFilterType
import java.util.*

data class User(

    /**
     * The user's account type
     */
    val account: AccountType?,

    /**
     * Information about the user's badge.
     *
     * Based on CAPABILITY_VIEW_USER_BADGE.
     */
    val badge: UserBadge?,

    /**
     * The user's bio.
     */
    val bio: String?,

    /**
     * The user's content filters:
     */
    val contentFilter: List<ContentFilterType>?,

    /**
     * The time in ISO 8601 format when the user account was created.
     */
    val createdTime: Date?,

    /**
     * The user's email address.
     */
    val email: String?,

    /**
     * An array of alternate emails for the user.
     */
    val emails: List<String>?,

    /**
     * The absolute URL of this user's profile page.
     */
    val link: String?,

    /**
     * Information about the user's live streaming quota.
     */
    val liveQuota: LiveQuota?,

    /**
     * The user's location.
     */
    val location: String?,

    /**
     * The user's metadata.
     */
    val metadata: UserMetadata?,

    /**
     * The user's display name.
     */
    val name: String?,

    /**
     * The user's stored payment information.
     */
    val payment: Payment?,

    /**
     * The active portrait of this user.
     */
    val pictures: PictureCollection?,

    /**
     * User's preferences.
     */
    val preferences: Preferences?,

    /**
     * The user's resource key string.
     */
    val resourceKey: String?,

    /**
     * Appears only when the user has upload access and is looking at their own user record.
     */
    val uploadQuota: UploadQuota?,

    /**
     * The user's canonical relative URI.
     */
    val uri: String?,

    /**
     * The user's email verification status.
     *
     * Based on CAPABILITY_API_APP_MANAGEMENT.
     */
    val verified: Boolean?,

    /**
     * The user's websites.
     */
    val websites: List<Website>?

)
