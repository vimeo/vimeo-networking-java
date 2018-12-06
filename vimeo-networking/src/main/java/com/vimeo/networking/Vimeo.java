/*
 * Copyright (c) 2015 Vimeo (https://vimeo.com)
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

package com.vimeo.networking;

/**
 * The constants class for Vimeo API calls. This includes parameters, fields, and defaults
 * <p>
 * Created by kylevenn on 7/7/15.
 */
@SuppressWarnings("unused")
public final class Vimeo {

    public static final String VIMEO_BASE_URL_STRING = "https://api.vimeo.com/";

    public static final String API_VERSION = "3.4.2";

    // Global Constants
    public static final int NOT_FOUND = -1;

    // Grant Types
    public static final String CODE_GRANT_PATH = "/oauth/authorize";
    public static final String CODE_GRANT_RESPONSE_TYPE = "code";
    public static final String CODE_GRANT_STATE = "state";
    public static final String CODE_GRANT_TYPE = "authorization_code";
    public static final String DEVICE_GRANT_TYPE = "device_grant";
    public static final String FACEBOOK_GRANT_TYPE = "facebook";
    public static final String GOOGLE_GRANT_TYPE = "google";
    public static final String PASSWORD_GRANT_TYPE = "password";
    public static final String CLIENT_CREDENTIALS_GRANT_TYPE = "client_credentials";
    public static final String OAUTH_ONE_GRANT_TYPE = "vimeo_oauth1";

    // Endpoints
    public static final String ENDPOINT_ME = "me";
    public static final String ENDPOINT_RECOMMENDATIONS = "/recommendations";
    public static final String ENDPOINT_TERMS_OF_SERVICE = "documents/termsofservice";
    public static final String ENDPOINT_PRIVACY_POLICY = "documents/privacy";
    public static final String ENDPOINT_PAYMENT_ADDENDUM = "documents/paymentaddendum";

    // Parameters
    public static final String PARAMETER_REDIRECT_URI = "redirect_uri";
    public static final String PARAMETER_RESPONSE_TYPE = "response_type";
    public static final String PARAMETER_STATE = "state";
    public static final String PARAMETER_SCOPE = "scope";
    public static final String PARAMETER_TOKEN = "token";
    public static final String PARAMETER_ID_TOKEN = "id_token";
    public static final String PARAMETER_CLIENT_ID = "client_id";

    public static final String PARAMETER_USERS_NAME = "name";
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_USERS_LOCATION = "location";
    public static final String PARAMETER_USERS_BIO = "bio";
    public static final String PARAMETER_MARKETING_OPT_IN = "marketing_opt_in";

    public static final String PARAMETER_VIDEO_VIEW = "view";
    public static final String PARAMETER_VIDEO_COMMENTS = "comments";
    public static final String PARAMETER_VIDEO_EMBED = "embed";
    public static final String PARAMETER_VIDEO_DOWNLOAD = "download";
    public static final String PARAMETER_VIDEO_ADD = "add";
    public static final String PARAMETER_VIDEO_NAME = "name";
    public static final String PARAMETER_VIDEO_DESCRIPTION = "description";
    public static final String PARAMETER_VIDEO_PRIVACY = "privacy";
    public static final String PARAMETER_VIDEO_PASSWORD = "password";

    public static final String PARAMETER_COMMENT_TEXT_BODY = "text";

    public static final String PARAMETER_ACTIVE = "active";

    // Header Parameters
    public static final String HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";

    // Header Values
    public static final String HEADER_CACHE_PUBLIC = "public";

    // Video Analytics Parameters
    public static final String PARAMETER_SESSION_ID = "session_id";
    public static final String PARAMETER_SESSION_TIME = "session_time";
    public static final String PARAMETER_VUID = "vuid";
    public static final String PARAMETER_LOCALE = "locale";
    public static final String PARAMETER_EXIT_WATCHED_TIME = "exit_watched_time_code";
    public static final String PARAMETER_FURTHEST_WATCHED_TIME = "furthest_watched_time_code";
    public static final String PARAMETER_PROGRESS = "progress";
    public static final String PARAMETER_EVENTS = "events";
    public static final String PARAMETER_EVENT_TYPE = "type";
    public static final String PARAMETER_EVENT_TYPE_WATER_LATER = "watchlater";
    public static final String PARAMETER_EVENT_TYPE_LIKE = "like";
    public static final String PARAMETER_EVENT_ACTION = "action";
    public static final String PARAMETER_EVENT_ACTION_ADDED = "added";
    public static final String PARAMETER_EVENT_ACTION_REMOVED = "removed";

    // GET and Sorting Parameters
    public static final String PARAMETER_GET_PAGE_SIZE = "per_page";
    public static final String PARAMETER_GET_QUERY = "query";
    public static final String PARAMETER_GET_SORT = "sort";
    public static final String PARAMETER_GET_DIRECTION = "direction";
    public static final String PARAMETER_GET_FIELD_FILTER = "fields";
    public static final String PARAMETER_GET_CONTAINER_FIELD_FILTER = "container_fields";
    public static final String PARAMETER_GET_LENGTH_MIN_DURATION = "min_duration";
    public static final String PARAMETER_GET_LENGTH_MAX_DURATION = "max_duration";
    public static final String PARAMETER_GET_FILTER = "filter";
    public static final String PARAMETER_GET_UPLOAD_DATE_FILTER = "filter_upload_date";
    public static final String PARAMETER_GET_NOTIFICATION_TYPES_FILTER = "filter_notification_types";
    public static final String PARAMETER_PATCH_LATEST_NOTIFICATION_URI = "latest_notification_uri";

    // Sorting (sort) Values
    public static final String SORT_DEFAULT = "default";
    public static final String SORT_RELEVANCE = "relevant";
    public static final String SORT_POPULAR = "popularity";
    public static final String SORT_DATE = "date";
    public static final String SORT_PURCHASE_TIME = "purchase_time";
    public static final String SORT_FOLLOWERS = "followers";
    public static final String SORT_ALPHABETICAL = "alphabetical";
    public static final String SORT_MANUAL = "manual";
    public static final String SORT_DURATION = "duration";
    public static final String SORT_LAST_USER_ACTION_EVENT_DATE = "last_user_action_event_date";
    public static final String SORT_PLAYS = "plays";
    public static final String SORT_LIKES = "likes";
    public static final String SORT_MODIFIED_TIME = "modified_time";
    public static final String SORT_COMMENTS = "comments";

    // Sort Direction Values
    public static final String SORT_DIRECTION_ASCENDING = "asc";
    public static final String SORT_DIRECTION_DESCENDING = "desc";

    // Filter (filter) Values
    public static final String FILTER_RELATED = "related";
    public static final String FILTER_UPLOAD = "upload_date";
    public static final String FILTER_VIEWABLE = "viewable";
    public static final String FILTER_PLAYABLE = "playable";
    public static final String FILTER_TRENDING = "trending";
    public static final String FILTER_TVOD_RENTALS = "rented";
    public static final String FILTER_TVOD_SUBSCRIPTIONS = "subscription";
    public static final String FILTER_TVOD_PURCHASES = "purchased";
    public static final String FILTER_NOTIFICATION_TYPES = "notification_types";
    // Filter Upload Date Values
    public static final String FILTER_UPLOAD_DATE_TODAY = "day";
    public static final String FILTER_UPLOAD_DATE_WEEK = "week";
    public static final String FILTER_UPLOAD_DATE_MONTH = "month";
    public static final String FILTER_UPLOAD_DATE_YEAR = "year";

    public static final String PAGE_SIZE_MAX = "100";

    public static final String OPTIONS_POST = "POST";

    // Fields (for invalid params)
    public static final String FIELD_NAME = "name";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_TOKEN = "token";
    public static final String FIELD_USERNAME = "username";

    public enum RefineLength {
        ANY,
        UNDER_FIVE_MINUTES,
        OVER_FIVE_MINUTES
    }

    public enum RefineSort {
        DEFAULT(SORT_DEFAULT),
        RELEVANCE(SORT_RELEVANCE),
        POPULARITY(SORT_POPULAR),
        RECENT(SORT_DATE),
        // Channels
        FOLLOWERS(SORT_FOLLOWERS),
        // Users
        AZ(SORT_DIRECTION_ASCENDING),
        ZA(SORT_DIRECTION_DESCENDING);


        private String mText;

        RefineSort(String text) {
            this.mText = text;
        }

        public String getText() {
            return this.mText;
        }

        public static RefineSort fromString(String text) {
            if (text != null) {
                for (RefineSort b : RefineSort.values()) {
                    if (text.equalsIgnoreCase(b.mText)) {
                        return b;
                    }
                }
            }
            throw new IllegalArgumentException("No constant with mText " + text + " found");
        }
    }

    public enum RefineUploadDate {
        ANYTIME(""),
        TODAY(FILTER_UPLOAD_DATE_TODAY),
        THIS_WEEK(FILTER_UPLOAD_DATE_WEEK),
        THIS_MONTH(FILTER_UPLOAD_DATE_MONTH),
        THIS_YEAR(FILTER_UPLOAD_DATE_YEAR);

        private String mText;

        RefineUploadDate(String text) {
            this.mText = text;
        }

        public String getText() {
            return this.mText;
        }

        public static RefineUploadDate fromString(String text) {
            if (text != null) {
                for (RefineUploadDate b : RefineUploadDate.values()) {
                    if (text.equalsIgnoreCase(b.mText)) {
                        return b;
                    }
                }
            }
            throw new IllegalArgumentException("No constant with mText " + text + " found");
        }
    }

    public enum LogLevel {
        // 0      1       2     3
        VERBOSE, DEBUG, ERROR, NONE
    }

    private Vimeo() {
    }
}
