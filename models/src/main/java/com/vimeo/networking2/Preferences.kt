package com.vimeo.networking2

/**
 * Preferences that may have been set by a user.
 */
data class Preferences(

    /**
     * Video preferences set by the a user.
     */
    val videos: VideosPreference? = null

)
