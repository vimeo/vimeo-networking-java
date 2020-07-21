package com.vimeo.networking2.properties

class InlinePropertyContainer {

    val foo: String
        // Avoid platform types by casting to String
        inline get() = this::class.java.simpleName as String
}