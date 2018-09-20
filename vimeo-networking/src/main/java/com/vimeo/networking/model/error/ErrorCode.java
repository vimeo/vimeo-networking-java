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

package com.vimeo.networking.model.error;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

/**
 * All the error codes that can come back from the Vimeo Api.
 * <p>
 * Vimean reference: <a href="https://docs.google.com/a/vimeo.com/spreadsheets/d/1DlkbeOFDuogvwyG2QDqExqBd_Yb3M7w7ku25AJN7QU0/edit?usp=sharing">Spreadsheet</a>.
 * <p>
 * Created by kylevenn on 7/15/15.
 */
@SuppressWarnings("unused")
@UseStag
public enum ErrorCode {
    // The default code that will be returned if the code returned from the server isn't enumerated below
    // If that is the case, check the raw response for the code [KV]
    DEFAULT,

    // ---- General Codes ----
    // <editor-fold desc="General">
    @SerializedName("1000")
    BAD_REQUEST,
    @SerializedName("1001")
    UNAUTHORIZED,
    @SerializedName("1002")
    FORBIDDEN,
    @SerializedName("1003")
    NOT_FOUND,
    @SerializedName("1004")
    INTERNAL_SERVER_ERROR,
    @SerializedName("1005")
    NOT_IMPLEMENTED,
    @SerializedName("1006")
    SERVICE_UNAVAILABLE,
    @SerializedName("2000")
    MISSING_REQUIRED_HEADER,
    @SerializedName("2001")
    MISSING_REQUIRED_QUERY_PARAM,
    @SerializedName("2002")
    MISSING_REQUIRED_BODY,
    @SerializedName("2100")
    UNSUPPORTED_HEADER,
    @SerializedName("2101")
    UNSUPPORTED_QUERY_PARAM,
    @SerializedName("2200")
    INVALID_HEADER_VALUE,
    @SerializedName("2201")
    INVALID_QUERY_PARAM_VALUE,
    @SerializedName("2202")
    INVALID_URI,
    @SerializedName("2203")
    INVALID_AUTHENTICATION_INFO,
    @SerializedName("2204")
    INVALID_INPUT,
    @SerializedName("2205")
    INVALID_BODY,
    @SerializedName("2206")
    INVALID_ACCEPT_HEADER,
    @SerializedName("2207")
    INVALID_NO_INPUT,
    @SerializedName("2219")
    INVALID_INPUT_GRANT_TYPE,
    @SerializedName("2220")
    INVALID_INPUT_EMBED_TYPE,
    @SerializedName("2221")
    INVALID_INPUT_VIEW_TYPE,
    @SerializedName("2222")
    INVALID_INPUT_VIDEO_PASSWORD_MISMATCH,
    @SerializedName("2223")
    INVALID_INPUT_VIDEO_NO_PASSWORD,
    @SerializedName("2300")
    INVALID_TOKEN,
    @SerializedName("2301")
    NON_EXISTENT_PROPERTY,
    @SerializedName("2302")
    MALFORMED_TOKEN,
    @SerializedName("2315")
    UNABLE_TO_CREATE_USER_CAN_NOT_VALIDATE_TOKEN,
    @SerializedName("2500")
    APP_DOES_NOT_HAVE_DELETE_CAPABILITY,
    @SerializedName("2700")
    INVALID_INPUT_NON_JSON_CONTENT_TYPE,
    @SerializedName("2507")
    PRODUCT_NOT_FOUND,
    @SerializedName("3113")
    INVALID_INPUT_GOOGLE_RECEIPT_VALIDATION_FAILED,
    @SerializedName("3115")
    INVALID_INPUT_RECEIPT_VALIDATION_UNSUCCESSFUL,
    @SerializedName("4000")
    OPERATION_TIMED_OUT,
    @SerializedName("5000")
    RESOURCE_NOT_FOUND,
    @SerializedName("5001")
    ACCESS_TOKEN_NOT_GENERATED,
    @SerializedName("6000")
    METHOD_NOT_IMPLEMENTED,
    @SerializedName("7000")
    SERVER_BUSY,
    @SerializedName("7100")
    SERVER_OVERLOADED,
    @SerializedName("8000")
    INVALID_CREDENTIALS,
    @SerializedName("8001")
    UNAUTHORIZED_CLIENT,
    @SerializedName("8003")
    EMPTY_AUTHENTICATION,
    // </editor-fold>

    // ---- Auth ----
    // <editor-fold desc="Auth">
    // Input
    @SerializedName("2208")
    INVALID_INPUT_NAME_TOO_LONG,
    @SerializedName("2209")
    INVALID_INPUT_NO_PASSWORD,
    @SerializedName("2210")
    INVALID_INPUT_PASSWORD_TOO_SHORT,
    @SerializedName("2211")
    INVALID_INPUT_PASSWORD_TOO_SIMPLE,
    @SerializedName("2212")
    INVALID_INPUT_PASSWORD_TOO_OBVIOUS,
    @SerializedName("2213")
    INVALID_INPUT_NO_NAME,
    @SerializedName("2214")
    INVALID_INPUT_NO_EMAIL,
    @SerializedName("2215")
    INVALID_INPUT_NO_RFC_822_EMAIL,
    @SerializedName("2216")
    INVALID_INPUT_EMAIL_TOO_LONG,
    @SerializedName("2217")
    INVALID_INPUT_EMAIL_NOT_RECOGNIZED,
    @SerializedName("2218")
    INVALID_INPUT_PASSWORD_EMAIL_MISMATCH,
    // Auth Errors
    @SerializedName("2303")
    UNABLE_TO_CREATE_USER_INVALID_TOKEN,
    @SerializedName("2304")
    UNABLE_TO_CREATE_USER_NON_EXISTENT_PROPERTY,
    @SerializedName("2305")
    UNABLE_TO_CREATE_USER_MALFORMED_TOKEN,
    @SerializedName("2306")
    UNABLE_TO_CREATE_USER_NO_TOKEN,
    @SerializedName("2307")
    UNABLE_TO_CREATE_USER_TOKEN_CAN_NOT_DECRYPT,
    @SerializedName("2308")
    UNABLE_TO_CREATE_USER_TOKEN_TOO_LONG,
    @SerializedName("2310")
    UNABLE_TO_LOGIN_NON_EXISTENT_PROPERTY,
    @SerializedName("2311")
    UNABLE_TO_LOGIN_MALFORMED_TOKEN,
    @SerializedName("2312")
    UNABLE_TO_LOGIN_NO_TOKEN,
    @SerializedName("2313")
    UNABLE_TO_LOGIN_TOKEN_CAN_NOT_DECRYPT,
    @SerializedName("2314")
    UNABLE_TO_LOGIN_TOKEN_TOO_LONG,
    // Google Auth Errors
    @SerializedName("2325")
    UNABLE_TO_CREATE_USER_MISSING_EMAIL_GOOGLE,
    @SerializedName("2326")
    UNABLE_TO_CREATE_USER_TOKEN_TOO_LONG_GOOGLE,
    @SerializedName("2327")
    UNABLE_TO_LOGIN_NO_TOKEN_GOOGLE,
    @SerializedName("2328")
    UNABLE_TO_LOGIN_NON_EXISTENT_PROPERTY_GOOGLE,
    @SerializedName("2329")
    UNABLE_TO_LOGIN_EMAIL_NOT_FOUND_VIA_TOKEN_GOOGLE,
    @SerializedName("2330")
    UNABLE_TO_CREATE_USER_INSUFFICIENT_PERMISSIONS_GOOGLE,
    @SerializedName("2331")
    UNABLE_TO_CREATE_USER_CAN_NOT_VALIDATE_TOKEN_GOOGLE,
    @SerializedName("2332")
    UNABLE_TO_CREATE_USER_DAILY_LIMIT_GOOGLE,
    @SerializedName("2333")
    UNABLE_TO_LOGIN_INSUFFICIENT_PERMISSIONS_GOOGLE,
    @SerializedName("2334")
    UNABLE_TO_LOGIN_CAN_NOT_VALIDATE_TOKEN_GOOGLE,
    @SerializedName("2335")
    UNABLE_TO_LOGIN_DAILY_LIMIT_GOOGLE,
    @SerializedName("2336")
    UNABLE_TO_LOGIN_GOOGLE_COULD_NOT_VERIFY_TOKEN,
    @SerializedName("2337")
    UNABLE_TO_CREATE_USER_GOOGLE_COULD_NOT_VERIFY_TOKEN,
    // Generic Auth Errors
    @SerializedName("2400")
    USER_EXISTS,
    @SerializedName("2401")
    EMAIL_BLOCKED,
    @SerializedName("2402")
    SPAMMER_USER,
    @SerializedName("2403")
    PURGATORY_USER,
    @SerializedName("2404")
    URL_UNAVAILABLE,
    @SerializedName("2406")
    USER_NOT_AUTHORIZED_TO_DELETE_ACCOUNT,
    // </editor-fold>

    // ---- Video Settings ----
    // <editor-fold desc="Video Settings">
    // Inputs
    @SerializedName("2230")
    INVALID_INPUT_BAD_UPLOAD_TYPE,
    @SerializedName("2231")
    INVALID_INPUT_NO_CLIP_NAME,
    @SerializedName("2232")
    INVALID_INPUT_BAD_CLIP_PRIVACY_VIEW,
    @SerializedName("2233")
    INVALID_INPUT_CLIP_PRIVACY_PASSWORD_MISSING_PASSWORD2233,
    @SerializedName("2234")
    INVALID_INPUT_BAD_LICENSE_TYPE,
    @SerializedName("2235")
    INVALID_INPUT_BAD_LANGUAGE_TYPE,
    @SerializedName("2236")
    INVALID_INPUT_BAD_REVIEW_LINK,
    @SerializedName("2237")
    INVALID_INPUT_BAD_CLIP_PRIVACY_ADD,
    @SerializedName("2238")
    INVALID_INPUT_BAD_CLIP_PRIVACY_DOWNLOAD,
    @SerializedName("2239")
    INVALID_INPUT_BAD_CLIP_PRIVACY_EMBED,
    @SerializedName("2240")
    INVALID_INPUT_BAD_CLIP_PRIVACY_COMMENTS,
    @SerializedName("2241")
    INVALID_INPUT_BAD_USER_URI,
    @SerializedName("2242")
    INVALID_INPUT_NO_USER_URI,
    @SerializedName("2244")
    INVALID_INPUT_NO_CLIP_USERS,
    @SerializedName("2245")
    INVALID_INPUT_EMPTY_USERS_ARRAY,
    @SerializedName("2246")
    CLIP_PRIVACY_NOT_SET_TO_USERS,
    @SerializedName("2247")
    INVALID_INPUT_NO_CLIP_PRIVACY_WHEN_SETTING_USERS,
    @SerializedName("2248")
    INVALID_INPUT_BAD_CLIP_PRIVACY_FOR_SETTING_USERS,
    @SerializedName("2249")
    INVALID_INPUT_BAD_CLIP_DESCRIPTION_TYPE,
    @SerializedName("2250")
    INVALID_INPUT_CLIP_NAME_TOO_LONG,
    @SerializedName("2251")
    INVALID_INPUT_CLIP_DESCRIPTION_TOO_LONG,
    @SerializedName("2252")
    INVALID_INPUT_BAD_CLIP_NAME_TYPE,
    @SerializedName("2253")
    INVALID_INPUT_EMPTY_USER_NAME,
    @SerializedName("2409")
    USER_NOT_ALLOWED_TO_SET_PUBLIC_OR_NOBODY_CLIP_PRIVACY,
    @SerializedName("2410")
    USER_NOT_ALLOWED_TO_SET_USERS_CLIP_PRIVACY,
    @SerializedName("2411")
    USER_NOT_ALLOWED_TO_SET_DISABLE_CLIP_PRIVACY,
    @SerializedName("2412")
    USER_NOT_ALLOWED_TO_SET_CONTACTS_CLIP_PRIVACY,
    // Upload
    @SerializedName("8002")
    UNABLE_TO_UPLOAD_VIDEO_MISSING_USER_ID_FOR_AUTHENTICATION_TOKEN,
    @SerializedName("3400")
    USER_NOT_ALLOWED_TO_UPLOAD_VIDEO_UNVERIFIED_EMAIL,
    @SerializedName("4003")
    UPLOAD_TICKET_CREATION_ERROR,
    @SerializedName("3428")
    UPLOAD_QUOTA_SIZE_EXCEEDED_CAP,
    @SerializedName("4101")
    UPLOAD_QUOTA_SIZE_EXCEEDED,
    @SerializedName("4102")
    UPLOAD_QUOTA_COUNT_EXCEEDED,
    // Unused
    // These most likely won't affect the Vimeo app since we don't currently have these settings
    @SerializedName("2254")
    INVALID_INPUT_BAD_CLIP_SIZE_TYPE,
    @SerializedName("2255")
    INVALID_INPUT_BAD_CLIP_UPGRADE_TO_1080_TYPE,
    @SerializedName("2256")
    INVALID_INPUT_BAD_CLIP_REDIRECT_URL_TYPE,
    @SerializedName("2257")
    INVALID_INPUT_BAD_CLIP_MACHINE_ID_TYPE,
    @SerializedName("2258")
    INVALID_INPUT_BAD_CLIP_CREATE_CLIP_TYPE,
    @SerializedName("2259")
    INVALID_INPUT_BAD_CLIP_CONTENT_RATINGS_TYPE,
    @SerializedName("2260")
    INVALID_INPUT_BAD_CLIP_SHOW_LIKE_BUTTON_TYPE,
    @SerializedName("2261")
    INVALID_INPUT_BAD_CLIP_SHOW_WATCH_LATER_BUTTON,
    @SerializedName("2262")
    INVALID_INPUT_BAD_CLIP_SHOW_SHARE_BUTTON_TYPE,
    @SerializedName("2263")
    INVALID_INPUT_BAD_CLIP_SHOW_EMBED_BUTTON_TYPE,
    @SerializedName("2264")
    INVALID_INPUT_BAD_CLIP_ALLOW_HD_EMBED_TYPE,
    @SerializedName("2265")
    INVALID_INPUT_BAD_CLIP_SHOW_FULLSCREEN_BUTTON_TYPE,
    @SerializedName("2266")
    INVALID_INPUT_BAD_CLIP_SHOW_VIMEO_LOGO_TYPE,
    @SerializedName("2267")
    INVALID_INPUT_BAD_CLIP_SHOW_CUSTOM_LOGO_TYPE,
    @SerializedName("2268")
    INVALID_INPUT_BAD_CLIP_CUSTOM_LOGO_STICKY_TYPE,
    @SerializedName("2269")
    INVALID_INPUT_BAD_CLIP_CUSTOM_LOGO_LINK_URL_TYPE,
    @SerializedName("2270")
    INVALID_INPUT_BAD_CLIP_SHOW_PLAYBAR_URL_TYPE,
    @SerializedName("2271")
    INVALID_INPUT_BAD_CLIP_SHOW_VOLUME_TYPE,
    @SerializedName("2272")
    INVALID_INPUT_BAD_CLIP_COLOR_TYPE,
    @SerializedName("2273")
    INVALID_INPUT_BAD_CLIP_PRIVACY_PASSWORD_EMPTY_PASSWORD,
    @SerializedName("2274")
    INVALID_INPUT_BAD_CLIP_SHOW_BYLINE_TYPE,
    @SerializedName("2275")
    INVALID_INPUT_BAD_CLIP_SHOW_PORTRAIT_TYPE,
    @SerializedName("2276")
    INVALID_INPUT_BAD_CLIP_SHOW_TITLE_TYPE,
    @SerializedName("2277")
    INVALID_INPUT_BAD_CLIP_SHOW_SCALING_BUTTON_TYPE,
    @SerializedName("2501")
    APP_DOES_NOT_HAVE_PROTECTED_VIDEO_CAPABILITY,
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // DRM
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="DRM">
    @SerializedName("2297")
    INVALID_INPUT_DRM_NOT_ENABLED_ON_CLIP,
    @SerializedName("2298")
    INVALID_INPUT_BAD_LOGGING_PLAY_TYPE,
    @SerializedName("3419")
    USER_CANNOT_STREAM_CLIP,
    @SerializedName("3420")
    USER_HIT_STREAM_LIMITS_FOR_VIDEO,
    @SerializedName("3421")
    USER_HIT_DEVICE_LIMIT,
    @SerializedName("8300")
    DRM_INVALID_CREDENTIALS
    // </editor-fold>
}
