package com.vimeo.networking2.functions

class AnnotatedFunctionContainer {

    @Deprecated("Use something else")
    fun doSomething() {
        println("Hello World")
    }
}