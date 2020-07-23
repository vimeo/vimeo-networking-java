package com.vimeo.networking2.properties

class ExtensionPropertyContainer {
    val String.doSomething: String
        get() = this.toString()
}