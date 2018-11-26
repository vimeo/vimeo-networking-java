package com.vimeo.networking2.enums

/**
 * Converts the [String] to its enum counterpart [T].
 */
inline fun <reified T> String?.asEnum(defaultValue: T): T where T : Enum<T>, T : StringValue =
    T::class.java.enumConstants
        .filterIsInstance<T>()
        .find { it.value == this }
        ?: defaultValue

/**
 * Converts the [List] of [String] to its enum counterpart as a non null [List] of [T].
 */
inline fun <reified T> List<String>?.asEnumList(defaultValue: T): List<T> where T : Enum<T>, T : StringValue =
    this?.map { it.asEnum(defaultValue) } ?: emptyList()

/**
 * A class that has a string value.
 */
interface StringValue {

    /**
     * The value held by this class, may be null.
     */
    val value: String?

}
