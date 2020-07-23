package com.vimeo.networking2.functions


class InlineFunctionContainer {
    inline fun inlineFunction(onClick: () -> Unit) {
        onClick()
    }
}