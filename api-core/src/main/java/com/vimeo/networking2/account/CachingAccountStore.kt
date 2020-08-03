/*
 * Copyright (c) 2020 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.vimeo.networking2.account

import com.vimeo.networking2.VimeoAccount

/**
 * An [AccountStore] that caches the stored account for fast retrieval and delegates actual storage to another
 * [AccountStore].
 *
 * @param actualAccountStore The store which will be used to persist the account outside the lifecycle of this
 * in-memory cache.
 */
class CachingAccountStore(private val actualAccountStore: AccountStore) : AccountStore {

    private var vimeoAccount: VimeoAccount? = null

    override fun loadAccount(): VimeoAccount? {
        return vimeoAccount ?: actualAccountStore.loadAccount().also { vimeoAccount = it }
    }

    override fun storeAccount(vimeoAccount: VimeoAccount) {
        this.vimeoAccount = vimeoAccount
        actualAccountStore.storeAccount(vimeoAccount)
    }

    override fun removeAccount() {
        vimeoAccount = null
        actualAccountStore.removeAccount()
    }
}
