package com.vimeo.networking2.enums

/**
 * Privacy settings for albums.
 */
enum class FolderViewPrivacyType(override val value: String?) : StringValue {

    /**
     * Anyone with the link can access the contents of the folder.
     */
    ANYBODY("anybody"),

    /**
     * Only the owner and those team members that the owner has explicitly invited
     * can access the contents of the folder.
     */
    NOBODY("nobody"),

    /**
     * Only those team members with the link can access the contents of the folder.
     */
    TEAM("team"),

    /**
     * Unknown privacy setting.
     */
    UNKNOWN(null)
}
