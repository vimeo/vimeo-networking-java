/*
 * Copyright (c) 2015 Vimeo (https://vimeo.com)
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

package com.vimeo.networking;

import com.vimeo.networking.model.VimeoAccount;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Interface responsible for handling the creation, deletion, and loading of Vimeo accounts on the client.
 * <p/>
 * Created by hanssena on 4/27/15.
 */
public interface AccountStore {

    /**
     * Load the previously saved {@link VimeoAccount} and return it
     *
     * @return null if no VimeoAccount could be loaded
     */
    @Nullable
    VimeoAccount loadAccount();

    /**
     * Saves a {@link VimeoAccount} for loading later.
     *
     * @param vimeoAccount the VimeoAccount to save, should not be null when calling this method
     * @param accountName  the <i>name</i> of the account that helps uniquely identify it in account
     *                     stores, such as the Android AccountManager
     */
    void saveAccount(@NotNull VimeoAccount vimeoAccount, String accountName);

    /**
     * Removes the saved {@link VimeoAccount} from storage
     *
     * @param vimeoAccount the account to delete
     */
    void deleteAccount(@NotNull VimeoAccount vimeoAccount);
}
