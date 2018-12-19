package com.vimeo.networking2.enums

/**
 * All scopes that can be passed to the API. They determine what information you
 * could get and your permissions in the Vimeo API. Here is more info on all the
 * supported scopes @see https://developer.vimeo.com/api/authentication#scopes.
 */
enum class ScopeType {
    PRIVATE,
    PUBLIC,
    PURCHASED,
    PURCHASE,
    CREATE,
    EDIT,
    DELETE,
    INTERACT,
    UPLOAD,
    PROMO_CODES,
    VIDEO_FILES,
    STATS;
}
