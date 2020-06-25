package com.vimeo.networking2.internal

import com.vimeo.networking2.GrantType
import retrofit2.Converter

/**
 * A converter for [GrantType] parameters.
 */
class GrantTypeConverter : Converter<GrantType, String> {
    override fun convert(value: GrantType): String = value.value
}
