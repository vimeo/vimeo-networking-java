package com.vimeo.networking2

/**
 * Represents a list of [PermissionPolicy]
 *
 * @param total the number of policies in the list returned in [data]
 * @param data the actual list of [PermissionPolicy]s
 */
class PermissionPolicyList(
    val total: Int? = null,
    val data: List<PermissionPolicy>
)
