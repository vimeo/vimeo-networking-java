package com.vimeo.networking2.common

/**
 * The representation of a storage quota.
 */
interface StorageQuota {
    /**
     * The number of bytes remaining in your upload quota for the current period.
     */
    val free: Long?

    /**
     * The total number of bytes that you can upload per period.
     */
    val max: Long?

    /**
     * The number of bytes that you've already uploaded against your quota in the current period.
     */
    val used: Long?
}
