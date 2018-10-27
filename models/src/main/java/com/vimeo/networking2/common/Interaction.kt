package com.vimeo.networking2.common

import com.vimeo.networking2.enums.ApiOptionsType

/**
 * Information on how to take action on an entity. Take an action on the
 * entity by using the [uri] with a HTTP verb in the [options] list.
 * The [options] list contains all the possible type of HTTP requests that are
 * supported for the interaction.
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