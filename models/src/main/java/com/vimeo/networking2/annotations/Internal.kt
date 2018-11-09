package com.vimeo.networking2.annotations

/**
 * Annotation to describe certain models as internal.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Internal
