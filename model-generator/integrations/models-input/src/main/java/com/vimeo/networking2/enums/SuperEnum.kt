package com.vimeo.networking2.enums

enum class SuperEnum(override val foo: Long) : FooContainer, Foo, Bar {
    FOO(100L),
    BAR(10L)
}


interface FooContainer {
    val foo: Long
}

interface Foo
interface Bar