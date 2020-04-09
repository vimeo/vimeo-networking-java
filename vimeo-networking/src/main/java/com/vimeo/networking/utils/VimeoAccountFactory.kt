@file:JvmName("VimeoAccountFactory")

package com.vimeo.networking.utils

import com.vimeo.networking2.VimeoAccount

/**
 * Create a [VimeoAccount] with only the provided [accessToken] set.
 */
fun createVimeoAccountWithToken(accessToken: String?): VimeoAccount = VimeoAccount(
    accessToken = accessToken
)
