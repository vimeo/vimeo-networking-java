package com.vimeo.networking2.internal

import com.vimeo.networking2.GrantType
import com.vimeo.networking2.Scopes
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * A [Converter.Factory] used for auth parameter conversion.
 */
class VimeoParametersConverterFactory : Converter.Factory() {
    override fun stringConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, String>? = when (type) {
        GrantType::class.java -> GrantTypeConverter()
        Scopes::class.java -> ScopesConverter()
        else -> super.stringConverter(type, annotations, retrofit)
    }
}
