package com.vimeo.networking2.annotations

/**
 * Annotation to describe certain models, properties, or functions as being for internal Vimeo use only.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Internal
