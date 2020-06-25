package com.vimeo.networking2.internal

import com.vimeo.networking2.Scopes
import retrofit2.Converter

/**
 * A converter for [Scopes] parameters.
 */
class ScopesConverter : Converter<Scopes, String> {
    override fun convert(value: Scopes): String =
            value.scopes.joinToString(separator = " ", transform = { it.name.toLowerCase() })
}
