package com.vimeo.networking2

import kotlin.reflect.KClass

/**
 * Finds the the corresponding class to the given [simpleName]
 */
fun List<KClass<*>>.findModel(simpleName: String): KClass<*>? =
    this.firstOrNull { it.simpleName == simpleName }