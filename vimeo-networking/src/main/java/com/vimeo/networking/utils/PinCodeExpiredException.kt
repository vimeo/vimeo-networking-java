package com.vimeo.networking.utils

import java.lang.IllegalStateException

/**
 * An exception indicating that a pin code has expired.
 */
class PinCodeExpiredException(override val message: String) : IllegalStateException(message)
