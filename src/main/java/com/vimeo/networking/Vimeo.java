package com.vimeo.networking;

/**
 * Created by kylevenn on 7/7/15.
 */
public class Vimeo {

    public static final String VIMEO_BASE_URL_STRING = "https://api.vimeo.com/";

    // Grant Types
    public static final String CODE_GRANT_PATH = "oauth/authorize";
    public static final String CODE_GRANT_RESPONSE_TYPE = "code";
    public static final String CODE_GRANT_STATE = "state";
    public static final String CODE_GRANT_TYPE = "authorization_code";
    public static final String FACEBOOK_GRANT_TYPE = "facebook";
    public static final String PASSWORD_GRANT_TYPE = "password";
    public static final String CLIENT_CREDENTIALS_GRANT_TYPE = "client_credentials";

    // Parameters
    public static final String PARAMETER_REDIRECT_URI = "redirect_uri";
    public static final String PARAMETER_RESPONSE_TYPE = "response_type";
    public static final String PARAMETER_STATE = "state";
    public static final String PARAMETER_SCOPE = "scope";
    public static final String PARAMETER_TOKEN = "token";
    public static final String PARAMETER_CLIENT_ID = "client_id";

    public static final String PARAMETER_USERS_NAME = "name";
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_USERS_LOCATION = "location";

    public static final String PARAMETER_VIDEO_VIEW = "view";
    public static final String PARAMETER_VIDEO_NAME = "name";
    public static final String PARAMETER_VIDEO_DESCRIPTION = "description";
    public static final String PARAMETER_VIDEO_PRIVACY = "privacy";

    public static final String PARAMETER_GET_SORT = "sort";
    public static final String PARAMETER_GET_FIELD_FILTER = "fields";
    public static final String PARAMETER_GET_QUERY = "query";

    // Sorting
    public static final String SORT_DEFAULT = "default";
    public static final String SORT_RELEVANCE = "relevant";

    // Header Parameters
    public static final String HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String HEADER_ACCEPT = "Accept";
}
