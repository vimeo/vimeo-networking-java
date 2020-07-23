@file:JvmName("ConfigurationUtils")

package com.vimeo.networking2.config

import com.vimeo.networking2.ApiConstants

/**
 * The User-Agent that identifies the consumer as a user of the library.
 */
val Configuration.compositeUserAgent: String
    get() = userAgent.let { "$it $USER_AGENT_HEADER_VALUE" }

private const val USER_AGENT_HEADER_VALUE = "Kotlin VimeoNetworking/${ApiConstants.SDK_VERSION}"
