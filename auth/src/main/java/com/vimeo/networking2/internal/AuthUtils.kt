package com.vimeo.networking2.internal

import com.vimeo.networking2.InvalidParameter
import com.vimeo.networking2.Scopes

/**
 * Validates any authentication params given by the client.
 *
 * @return a list of invalid parameters.
 */
internal fun Map<AuthParam, Any>.validate(): List<InvalidParameter> =
        filter { (_, value) ->
            (value is String && value.isEmpty()) || (value is Scopes && value.scopes.isEmpty())
        }.map { InvalidParameter(it.key.name, it.key.errorCode?.value, it.key.developerMessage) }
