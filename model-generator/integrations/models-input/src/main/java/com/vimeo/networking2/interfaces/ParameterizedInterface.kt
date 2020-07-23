package com.vimeo.networking2.interfaces

interface ParameterizedInterface<FIRST_TYPE : String, SECOND_TYPE, THIRD_TYPE> {
    val foo: List<FIRST_TYPE>
    val bar: SECOND_TYPE
    val baz: Map<FIRST_TYPE, THIRD_TYPE>
}