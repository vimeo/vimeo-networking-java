package com.vimeo.networking2.functions


class ReifiedFunctionContainer {
    // Cast to String to avoid issues with platform types
    inline fun <reified T> reifiedFunction(): String = T::class.java.simpleName as String
}