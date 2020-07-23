package com.vimeo.networking2.data

data class MultipleSupersDataClass(override val foo: String) : Foo, Bar, Baz

private interface Foo {
    val foo: String
}

private interface Bar

private interface Baz