package com.vimeo.networking2.common

import com.vimeo.networking2.Metadata

/**
 * All DTOs that can be reported should conform this interface.
 */
interface Reportable {

    val metadata: Metadata<*, out ReportableInteractions>?
}
