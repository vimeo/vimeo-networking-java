/*
 * Copyright (c) 2020 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.vimeo.networking2

/**
 * API constants.
 */
@Suppress("UndocumentedPublicProperty")
object ApiConstants {

    const val API_VERSION = "3.4.4"

    const val BASE_URL = "https://api.vimeo.com"

    const val SSL_URL_PATTERN = "*.vimeo.com"

    const val SDK_VERSION = "3.12.0"

    const val NONE = -1

    const val MAX_PER_PAGE = 100

    /**
     * Fixed endpoints.
     */
    object Endpoints {
        const val ENDPOINT_ME = "me"
        const val ENDPOINT_RECOMMENDATIONS = "/recommendations"
        const val ENDPOINT_TERMS_OF_SERVICE = "documents/termsofservice"
        const val ENDPOINT_PRIVACY_POLICY = "documents/privacy"
        const val ENDPOINT_PAYMENT_ADDENDUM = "documents/paymentaddendum"
    }

    /**
     * Parameters.
     */
    object Parameters {
        // Common parameters
        private const val PRIVACY = "privacy"
        private const val NAME = "name"
        private const val DESCRIPTION = "description"

        const val PARAMETER_REDIRECT_URI = "redirect_uri"
        const val PARAMETER_RESPONSE_TYPE = "response_type"
        const val PARAMETER_STATE = "state"
        const val PARAMETER_SCOPE = "scope"
        const val PARAMETER_TOKEN = "token"
        const val PARAMETER_ID_TOKEN = "id_token"
        const val PARAMETER_CLIENT_ID = "client_id"
        const val PARAMETER_USERS_NAME = NAME
        const val PARAMETER_EMAIL = "email"
        const val PARAMETER_PASSWORD = "password"
        const val PARAMETER_USERS_LOCATION = "location"
        const val PARAMETER_USERS_BIO = "bio"
        const val PARAMETER_MARKETING_OPT_IN = "marketing_opt_in"
        const val PARAMETER_VIDEO_VIEW = "view"
        const val PARAMETER_VIDEO_COMMENTS = "comments"
        const val PARAMETER_VIDEO_EMBED = "embed"
        const val PARAMETER_VIDEO_DOWNLOAD = "download"
        const val PARAMETER_VIDEO_ADD = "add"
        const val PARAMETER_VIDEO_NAME = NAME
        const val PARAMETER_VIDEO_DESCRIPTION = DESCRIPTION
        const val PARAMETER_VIDEO_PRIVACY = PRIVACY
        const val PARAMETER_VIDEO_PASSWORD = PARAMETER_PASSWORD
        const val PARAMETER_ALBUM_NAME = NAME
        const val PARAMETER_ALBUM_DESCRIPTION = DESCRIPTION
        const val PARAMETER_ALBUM_PRIVACY = PRIVACY
        const val PARAMETER_ALBUM_PASSWORD = PARAMETER_PASSWORD
        const val PARAMETER_AUTH_CODE = "auth_code"
        const val PARAMETER_APP_TYPE = "app_type"
        const val PARAMETER_COMMENT_TEXT_BODY = "text"
        const val PARAMETER_ACTIVE = "active"
        const val PARAMETER_FOLDER_NAME = NAME
        const val PARAMETER_FOLDER_PRIVACY = PRIVACY
        const val PARAMETER_PERMISSION_LEVEL = "permission_level"
        const val PARAMETER_FOLDER_URI = "folder_uri"
        const val PARAMETER_VIDEO_URI = "video_uri"
        const val PARAMETER_ROLE = "role"
        const val PARAMETER_PERMISSION_POLICY_URI = "permission_policy_uri"
        const val PARAMETER_TEAM_ENTITY_TYPE = "team_entity_type"
        const val PARAMETER_TEAM_ENTITY_URI = "team_entity_uri"
        const val PARAMETER_LIVE_EVENT_TITLE = "title"
        const val PARAMETER_LIVE_EVENT_STREAM_TITLE = "stream_title"
        const val PARAMETER_LIVE_EVENT_DESCRIPTION = "stream_description"
        const val PARAMETER_LIVE_EVENT_PASSWORD = "stream_password"
        const val PARAMETER_AUTOMATICALLY_TITLE_STREAM = "automatically_title_stream"
        const val PARAMETER_STREAMING_PRIVACY = "stream_privacy"
        const val PARAMETER_LIVE_SCHEDULE = "schedule"
        const val PARAMETER_LIVE_CHAT_ENABLED = "chat_enabled"

        // Generic parameters
        const val PARAMETER_GET_PAGE_SIZE = "per_page"
        const val PARAMETER_GET_QUERY = "query"
        const val PARAMETER_GET_SORT = "sort"
        const val PARAMETER_GET_DIRECTION = "direction"
        const val PARAMETER_GET_CONTAINER_FIELD_FILTER = "container_fields"
        const val PARAMETER_GET_LENGTH_MIN_DURATION = "min_duration"
        const val PARAMETER_GET_LENGTH_MAX_DURATION = "max_duration"
        const val PARAMETER_GET_FILTER = "filter"
        const val PARAMETER_GET_UPLOAD_DATE_FILTER = "filter_upload_date"
        const val PARAMETER_GET_NOTIFICATION_TYPES_FILTER = "filter_notification_types"
        const val PARAMETER_PATCH_LATEST_NOTIFICATION_URI = "latest_notification_uri"

        // Search parameters
        const val FILTER_CATEGORY = "filter_category"
        const val FILTER_UPLOADED = "filter_uploaded"
        const val FILTER_DURATION = "filter_duration"
        const val FILTER_TYPE = "filter_type"
        const val FILTER_FEATURED_COUNT = "featured_clip_count"
        const val PARAMETER_GET_FACETS = "facets"

        // Sorting values
        const val SORT_DEFAULT = "default"
        const val SORT_RELEVANCE = "relevant"
        const val SORT_POPULAR = "popularity"
        const val SORT_DATE = "date"
        const val SORT_PURCHASE_TIME = "purchase_time"
        const val SORT_FOLLOWERS = "followers"
        const val SORT_ALPHABETICAL = "alphabetical"
        const val SORT_MANUAL = "manual"
        const val SORT_DURATION = "duration"
        const val SORT_LAST_USER_ACTION_EVENT_DATE = "last_user_action_event_date"
        const val SORT_PLAYS = "plays"
        const val SORT_LIKES = "likes"
        const val SORT_MODIFIED_TIME = "modified_time"
        const val SORT_COMMENTS = "comments"

        // Filter values
        const val FILTER_RELATED = "related"
        const val FILTER_UPLOAD = "upload_date"
        const val FILTER_VIEWABLE = "viewable"
        const val FILTER_PLAYABLE = "playable"
        const val FILTER_TRENDING = "trending"
        const val FILTER_TVOD_RENTALS = "rented"
        const val FILTER_TVOD_SUBSCRIPTIONS = "subscription"
        const val FILTER_TVOD_PURCHASES = "purchased"
        const val FILTER_NOTIFICATION_TYPES = "notification_types"
        const val FILTER_NO_LIVE = "nolive"
    }
}
