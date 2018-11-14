package com.vimeo.networking2.annotations

/**
 * Annotation to describe certain models or properties as being for internal Vimeo use only.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Internal
