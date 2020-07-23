package com.vimeo.networking2.data


data class ParameterizedSuperDataClass(override val foo: String): MyFoo<String>

private interface MyFoo<TYPE> {
    val foo: TYPE
}