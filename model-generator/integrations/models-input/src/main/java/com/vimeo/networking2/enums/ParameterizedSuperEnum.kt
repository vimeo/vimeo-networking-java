package com.vimeo.networking2.enums

enum class ParameterizedSuperEnum(override val value: String): Container<String> {
    FOO("foo"),
    BAR("bar")
}

interface Container<TYPE>{
    val value: TYPE
}