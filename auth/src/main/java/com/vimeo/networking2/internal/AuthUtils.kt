package com.vimeo.networking2.internal

import com.vimeo.networking2.InvalidParameter

/**
 * Validates any authentication params given by the client.
 *
 * @return a list of invalid parameters.
 */
internal fun Map<AuthParam, String>.validate(): List<InvalidParameter> =
        filter { it.value.isEmpty() }
        .map { InvalidParameter(it.key.name, it.key.errorCode?.value, it.key.developerMessage) }
