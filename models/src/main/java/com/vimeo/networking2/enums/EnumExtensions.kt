package com.vimeo.networking2.enums

/**
 * Converts the [String] to its enum counterpart [T] reflectively.
 */
inline fun <reified T> String.asEnum(): T? where T : Enum<T>, T : StringValue =
    T::class.java.enumConstants
        .filterIsInstance<T>()
        .find { it.value == this }

/**
 * A class that has a string value.
 */
interface StringValue {

    /**
     * The value held by this class, may be null.
     */
    val value: String?

}
