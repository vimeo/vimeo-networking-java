package com.vimeo.networking2.internal.params

import com.vimeo.networking2.enums.StringValue
import retrofit2.Converter

/**
 * Converts a [StringValue] to a [String].
 */
class StringValueConverter : Converter<StringValue, String> {
    override fun convert(value: StringValue): String? = value.value
}
