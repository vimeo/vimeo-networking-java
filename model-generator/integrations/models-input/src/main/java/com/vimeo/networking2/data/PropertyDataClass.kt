package com.vimeo.networking2.data

data class PropertyDataClass(val foo: String, val bar: Long) {
    val baz: String = "$foo $bar"
}