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
package com.vimeo.networking2

/**
 * Interface responsible for handling the creation, deletion, and loading of Vimeo accounts on the client.
 */
interface AccountStore {
    /**
     * Load an account that has been saved previously. Returns `null` if no account was saved.
     */
    fun loadAccount(): VimeoAccount?

    /**
     * Save the provided account associated with an email.
     *
     * @param vimeoAccount The account to save.
     * @param email The email associated with the account.
     */
    fun saveAccount(vimeoAccount: VimeoAccount, email: String)

    /**
     * Delete the provided account from the store.
     *
     * @param vimeoAccount The account to delete.
     */
    fun deleteAccount(vimeoAccount: VimeoAccount)
}
