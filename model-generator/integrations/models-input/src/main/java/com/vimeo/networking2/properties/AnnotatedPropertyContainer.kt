package com.vimeo.networking2.properties

class AnnotatedPropertyContainer {
    @Deprecated("Use bar instead")
    val foo: String = "foo"
}