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
package com.vimeo.networking2.internal

import com.vimeo.networking2.VimeoAccount
import com.vimeo.networking2.VimeoCallback
import com.vimeo.networking2.VimeoResponse
import com.vimeo.networking2.account.AccountStore

/**
 * A [VimeoCallback] that stores the account received on a successful response.
 *
 * @param accountStore The store which will be used to save the received account.
 * @param actualVimeoCallback The callback which we are intercepting and will eventually be notified.
 */
internal class AccountStoringVimeoCallback(
    private val accountStore: AccountStore,
    private val actualVimeoCallback: VimeoCallback<VimeoAccount>
) : VimeoCallback<VimeoAccount> {
    override fun onSuccess(response: VimeoResponse.Success<VimeoAccount>) {
        accountStore.storeAccount(response.data)
        actualVimeoCallback.onSuccess(response)
    }

    override fun onError(error: VimeoResponse.Error) {
        actualVimeoCallback.onError(error)
    }
}
