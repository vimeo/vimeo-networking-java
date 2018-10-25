package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * All the error codes that can come back from the Vimeo Api.
 */
enum class ErrorCode {

    @Json(name = "1000")
    BAD_REQUEST,

    @Json(name = "1001")
    UNAUTHORIZED,

    @Json(name = "1002")
    FORBIDDEN,

    @Json(name = "1003")
    NOT_FOUND,

    @Json(name = "1004")
    INTERNAL_SERVER_ERROR,

    @Json(name = "1005")
    NOT_IMPLEMENTED,

    @Json(name = "1006")
    SERVICE_UNAVAILABLE,

    @Json(name = "2000")
    MISSING_REQUIRED_HEADER,

    @Json(name = "2001")
    MISSING_REQUIRED_QUERY_PARAM,

    @Json(name = "2002")
    MISSING_REQUIRED_BODY,

    @Json(name = "2100")
    UNSUPPORTED_HEADER,

    @Json(name = "2101")
    UNSUPPORTED_QUERY_PARAM,

    @Json(name = "2200")
    INVALID_HEADER_VALUE,

    @Json(name = "2201")
    INVALID_QUERY_PARAM_VALUE,

    @Json(name = "2202")
    INVALID_URI,

    @Json(name = "2203")
    INVALID_AUTHENTICATION_INFO,

    @Json(name = "2204")
    INVALID_INPUT,

    @Json(name = "2205")
    INVALID_BODY,

    @Json(name = "2206")
    INVALID_ACCEPT_HEADER,

    @Json(name = "2207")
    INVALID_NO_INPUT,

    @Json(name = "2219")
    INVALID_INPUT_GRANT_TYPE,

    @Json(name = "2220")
    INVALID_INPUT_EMBED_TYPE,

    @Json(name = "2221")
    INVALID_INPUT_VIEW_TYPE,

    @Json(name = "2222")
    INVALID_INPUT_VIDEO_PASSWORD_MISMATCH,

    @Json(name = "2223")
    INVALID_INPUT_VIDEO_NO_PASSWORD,

    @Json(name = "2300")
    INVALID_TOKEN,

    @Json(name = "2301")
    NON_EXISTENT_PROPERTY,

    @Json(name = "2302")
    MALFORMED_TOKEN,

    @Json(name = "2315")
    UNABLE_TO_CREATE_USER_CAN_NOT_VALIDATE_TOKEN,

    @Json(name = "2500")
    APP_DOES_NOT_HAVE_DELETE_CAPABILITY,

    @Json(name = "2700")
    INVALID_INPUT_NON_JSON_CONTENT_TYPE,

    @Json(name = "2507")
    PRODUCT_NOT_FOUND,

    @Json(name = "3113")
    INVALID_INPUT_GOOGLE_RECEIPT_VALIDATION_FAILED,

    @Json(name = "3115")
    INVALID_INPUT_RECEIPT_VALIDATION_UNSUCCESSFUL,

    @Json(name = "4000")
    OPERATION_TIMED_OUT,

    @Json(name = "5000")
    RESOURCE_NOT_FOUND,

    @Json(name = "5001")
    ACCESS_TOKEN_NOT_GENERATED,

    @Json(name = "6000")
    METHOD_NOT_IMPLEMENTED,

    @Json(name = "7000")
    SERVER_BUSY,

    @Json(name = "7100")
    SERVER_OVERLOADED,

    @Json(name = "8000")
    INVALID_CREDENTIALS,

    @Json(name = "8001")
    UNAUTHORIZED_CLIENT,

    @Json(name = "8003")
    EMPTY_AUTHENTICATION,

    @Json(name = "2208")
    INVALID_INPUT_NAME_TOO_LONG,

    @Json(name = "2209")
    INVALID_INPUT_NO_PASSWORD,

    @Json(name = "2210")
    INVALID_INPUT_PASSWORD_TOO_SHORT,

    @Json(name = "2211")
    INVALID_INPUT_PASSWORD_TOO_SIMPLE,

    @Json(name = "2212")
    INVALID_INPUT_PASSWORD_TOO_OBVIOUS,

    @Json(name = "2213")
    INVALID_INPUT_NO_NAME,

    @Json(name = "2214")
    INVALID_INPUT_NO_EMAIL,

    @Json(name = "2215")
    INVALID_INPUT_NO_RFC_822_EMAIL,

    @Json(name = "2216")
    INVALID_INPUT_EMAIL_TOO_LONG,

    @Json(name = "2217")
    INVALID_INPUT_EMAIL_NOT_RECOGNIZED,

    @Json(name = "2218")
    INVALID_INPUT_PASSWORD_EMAIL_MISMATCH,

    // Auth Errors
    @Json(name = "2303")
    UNABLE_TO_CREATE_USER_INVALID_TOKEN,

    @Json(name = "2304")
    UNABLE_TO_CREATE_USER_NON_EXISTENT_PROPERTY,

    @Json(name = "2305")
    UNABLE_TO_CREATE_USER_MALFORMED_TOKEN,

    @Json(name = "2306")
    UNABLE_TO_CREATE_USER_NO_TOKEN,

    @Json(name = "2307")
    UNABLE_TO_CREATE_USER_TOKEN_CAN_NOT_DECRYPT,

    @Json(name = "2308")
    UNABLE_TO_CREATE_USER_TOKEN_TOO_LONG,

    @Json(name = "2310")
    UNABLE_TO_LOGIN_NON_EXISTENT_PROPERTY,

    @Json(name = "2311")
    UNABLE_TO_LOGIN_MALFORMED_TOKEN,

    @Json(name = "2312")
    UNABLE_TO_LOGIN_NO_TOKEN,

    @Json(name = "2313")
    UNABLE_TO_LOGIN_TOKEN_CAN_NOT_DECRYPT,

    @Json(name = "2314")
    UNABLE_TO_LOGIN_TOKEN_TOO_LONG,

    // Google Auth Errors
    @Json(name = "2325")
    UNABLE_TO_CREATE_USER_MISSING_EMAIL_GOOGLE,

    @Json(name = "2326")
    UNABLE_TO_CREATE_USER_TOKEN_TOO_LONG_GOOGLE,

    @Json(name = "2327")
    UNABLE_TO_LOGIN_NO_TOKEN_GOOGLE,

    @Json(name = "2328")
    UNABLE_TO_LOGIN_NON_EXISTENT_PROPERTY_GOOGLE,

    @Json(name = "2329")
    UNABLE_TO_LOGIN_EMAIL_NOT_FOUND_VIA_TOKEN_GOOGLE,

    @Json(name = "2330")
    UNABLE_TO_CREATE_USER_INSUFFICIENT_PERMISSIONS_GOOGLE,

    @Json(name = "2331")
    UNABLE_TO_CREATE_USER_CAN_NOT_VALIDATE_TOKEN_GOOGLE,

    @Json(name = "2332")
    UNABLE_TO_CREATE_USER_DAILY_LIMIT_GOOGLE,

    @Json(name = "2333")
    UNABLE_TO_LOGIN_INSUFFICIENT_PERMISSIONS_GOOGLE,

    @Json(name = "2334")
    UNABLE_TO_LOGIN_CAN_NOT_VALIDATE_TOKEN_GOOGLE,

    @Json(name = "2335")
    UNABLE_TO_LOGIN_DAILY_LIMIT_GOOGLE,

    @Json(name = "2336")
    UNABLE_TO_LOGIN_GOOGLE_COULD_NOT_VERIFY_TOKEN,

    @Json(name = "2337")
    UNABLE_TO_CREATE_USER_GOOGLE_COULD_NOT_VERIFY_TOKEN,

    // Generic Auth Errors
    @Json(name = "2400")
    USER_EXISTS,

    @Json(name = "2401")
    EMAIL_BLOCKED,

    @Json(name = "2402")
    SPAMMER_USER,

    @Json(name = "2403")
    PURGATORY_USER,

    @Json(name = "2404")
    URL_UNAVAILABLE,

    @Json(name = "2406")
    USER_NOT_AUTHORIZED_TO_DELETE_ACCOUNT,

    @Json(name = "2230")
    INVALID_INPUT_BAD_UPLOAD_TYPE,

    @Json(name = "2231")
    INVALID_INPUT_NO_CLIP_NAME,

    @Json(name = "2232")
    INVALID_INPUT_BAD_CLIP_PRIVACY_VIEW,

    @Json(name = "2233")
    INVALID_INPUT_CLIP_PRIVACY_PASSWORD_MISSING_PASSWORD2233,

    @Json(name = "2234")
    INVALID_INPUT_BAD_LICENSE_TYPE,

    @Json(name = "2235")
    INVALID_INPUT_BAD_LANGUAGE_TYPE,

    @Json(name = "2236")
    INVALID_INPUT_BAD_REVIEW_LINK,

    @Json(name = "2237")
    INVALID_INPUT_BAD_CLIP_PRIVACY_ADD,

    @Json(name = "2238")
    INVALID_INPUT_BAD_CLIP_PRIVACY_DOWNLOAD,

    @Json(name = "2239")
    INVALID_INPUT_BAD_CLIP_PRIVACY_EMBED,

    @Json(name = "2240")
    INVALID_INPUT_BAD_CLIP_PRIVACY_COMMENTS,

    @Json(name = "2241")
    INVALID_INPUT_BAD_USER_URI,

    @Json(name = "2242")
    INVALID_INPUT_NO_USER_URI,

    @Json(name = "2244")
    INVALID_INPUT_NO_CLIP_USERS,

    @Json(name = "2245")
    INVALID_INPUT_EMPTY_USERS_ARRAY,

    @Json(name = "2246")
    CLIP_PRIVACY_NOT_SET_TO_USERS,

    @Json(name = "2247")
    INVALID_INPUT_NO_CLIP_PRIVACY_WHEN_SETTING_USERS,

    @Json(name = "2248")
    INVALID_INPUT_BAD_CLIP_PRIVACY_FOR_SETTING_USERS,

    @Json(name = "2249")
    INVALID_INPUT_BAD_CLIP_DESCRIPTION_TYPE,

    @Json(name = "2250")
    INVALID_INPUT_CLIP_NAME_TOO_LONG,

    @Json(name = "2251")
    INVALID_INPUT_CLIP_DESCRIPTION_TOO_LONG,

    @Json(name = "2252")
    INVALID_INPUT_BAD_CLIP_NAME_TYPE,

    @Json(name = "2253")
    INVALID_INPUT_EMPTY_USER_NAME,

    @Json(name = "2409")
    USER_NOT_ALLOWED_TO_SET_PUBLIC_OR_NOBODY_CLIP_PRIVACY,

    @Json(name = "2410")
    USER_NOT_ALLOWED_TO_SET_USERS_CLIP_PRIVACY,

    @Json(name = "2411")
    USER_NOT_ALLOWED_TO_SET_DISABLE_CLIP_PRIVACY,

    @Json(name = "2412")
    USER_NOT_ALLOWED_TO_SET_CONTACTS_CLIP_PRIVACY,

    // Upload
    @Json(name = "8002")
    UNABLE_TO_UPLOAD_VIDEO_MISSING_USER_ID_FOR_AUTHENTICATION_TOKEN,

    @Json(name = "3400")
    USER_NOT_ALLOWED_TO_UPLOAD_VIDEO_UNVERIFIED_EMAIL,

    @Json(name = "4003")
    UPLOAD_TICKET_CREATION_ERROR,

    @Json(name = "3428")
    UPLOAD_QUOTA_SIZE_EXCEEDED_CAP,

    @Json(name = "4101")
    UPLOAD_QUOTA_SIZE_EXCEEDED,

    @Json(name = "4102")
    UPLOAD_QUOTA_COUNT_EXCEEDED,

    @Json(name = "2254")
    INVALID_INPUT_BAD_CLIP_SIZE_TYPE,

    @Json(name = "2255")
    INVALID_INPUT_BAD_CLIP_UPGRADE_TO_1080_TYPE,

    @Json(name = "2256")
    INVALID_INPUT_BAD_CLIP_REDIRECT_URL_TYPE,

    @Json(name = "2257")
    INVALID_INPUT_BAD_CLIP_MACHINE_ID_TYPE,

    @Json(name = "2258")
    INVALID_INPUT_BAD_CLIP_CREATE_CLIP_TYPE,

    @Json(name = "2259")
    INVALID_INPUT_BAD_CLIP_CONTENT_RATINGS_TYPE,

    @Json(name = "2260")
    INVALID_INPUT_BAD_CLIP_SHOW_LIKE_BUTTON_TYPE,

    @Json(name = "2261")
    INVALID_INPUT_BAD_CLIP_SHOW_WATCH_LATER_BUTTON,

    @Json(name = "2262")
    INVALID_INPUT_BAD_CLIP_SHOW_SHARE_BUTTON_TYPE,

    @Json(name = "2263")
    INVALID_INPUT_BAD_CLIP_SHOW_EMBED_BUTTON_TYPE,

    @Json(name = "2264")
    INVALID_INPUT_BAD_CLIP_ALLOW_HD_EMBED_TYPE,

    @Json(name = "2265")
    INVALID_INPUT_BAD_CLIP_SHOW_FULLSCREEN_BUTTON_TYPE,

    @Json(name = "2266")
    INVALID_INPUT_BAD_CLIP_SHOW_VIMEO_LOGO_TYPE,

    @Json(name = "2267")
    INVALID_INPUT_BAD_CLIP_SHOW_CUSTOM_LOGO_TYPE,

    @Json(name = "2268")
    INVALID_INPUT_BAD_CLIP_CUSTOM_LOGO_STICKY_TYPE,

    @Json(name = "2269")
    INVALID_INPUT_BAD_CLIP_CUSTOM_LOGO_LINK_URL_TYPE,

    @Json(name = "2270")
    INVALID_INPUT_BAD_CLIP_SHOW_PLAYBAR_URL_TYPE,

    @Json(name = "2271")
    INVALID_INPUT_BAD_CLIP_SHOW_VOLUME_TYPE,

    @Json(name = "2272")
    INVALID_INPUT_BAD_CLIP_COLOR_TYPE,

    @Json(name = "2273")
    INVALID_INPUT_BAD_CLIP_PRIVACY_PASSWORD_EMPTY_PASSWORD,

    @Json(name = "2274")
    INVALID_INPUT_BAD_CLIP_SHOW_BYLINE_TYPE,

    @Json(name = "2275")
    INVALID_INPUT_BAD_CLIP_SHOW_PORTRAIT_TYPE,

    @Json(name = "2276")
    INVALID_INPUT_BAD_CLIP_SHOW_TITLE_TYPE,

    @Json(name = "2277")
    INVALID_INPUT_BAD_CLIP_SHOW_SCALING_BUTTON_TYPE,

    @Json(name = "2501")
    APP_DOES_NOT_HAVE_PROTECTED_VIDEO_CAPABILITY,

    @Json(name = "2297")
    INVALID_INPUT_DRM_NOT_ENABLED_ON_CLIP,

    @Json(name = "2298")
    INVALID_INPUT_BAD_LOGGING_PLAY_TYPE,

    @Json(name = "3419")
    USER_CANNOT_STREAM_CLIP,

    @Json(name = "3420")
    USER_HIT_STREAM_LIMITS_FOR_VIDEO,

    @Json(name = "3421")
    USER_HIT_DEVICE_LIMIT,

    @Json(name = "8300")
    DRM_INVALID_CREDENTIALS,

    /**
     * The default code that will be returned if the code returned from the server isn't
     * enumerated below.
     */
    DEFAULT

}
