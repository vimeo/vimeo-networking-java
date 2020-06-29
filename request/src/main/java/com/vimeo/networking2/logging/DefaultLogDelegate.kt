package com.vimeo.networking2.logging

/**
 * Default log delegate that logs to the system output.
 */
@Suppress("FunctionMinLength")
internal class DefaultLogDelegate : LogDelegate {
    override fun e(error: String) = println(error)

    override fun e(error: String, exception: Exception) {
        println(error)
        exception.printStackTrace()
    }

    override fun d(message: String) = println(message)

    override fun v(message: String) = println(message)
}
