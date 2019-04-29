@file:JvmName("InteractionUtils")

package com.vimeo.networking2.common

import com.vimeo.networking2.enums.ApiOptionsType
import com.vimeo.networking2.enums.asEnumList

/**
 * Information on how to take action on an entity. Take an action on the
 * entity by using the [uri] with a HTTP verb in the [options] list.
 * The [options] list contains all the possible type of HTTP requests that are
 * supported for the interaction.
 */
interface Interaction {

    /**
     * An array of the HTTP methods permitted on this URI.
     * @see Interaction.optionsTypes
     */
    val options: List<String>?

    /**
     * The API URI that resolves to the connection data.
     */
    val uri: String?

}

/**
 * @see Interaction.options
 * @see ApiOptionsType
 */
val Interaction.optionsTypes: List<ApiOptionsType>
    get() = options.asEnumList(ApiOptionsType.UNKNOWN)
