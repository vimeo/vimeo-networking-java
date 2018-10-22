package com.vimeo.networking2.common

import com.vimeo.networking2.enums.ApiOptionsType

/**
 * Information on taking an action on an entity.
 */
interface Interaction {

    /**
     * An array of the HTTP methods permitted on this URI.
     */
    val options: List<ApiOptionsType>?

    /**
     * The API URI that resolves to the connection data.
     */
    val uri: String?

}
