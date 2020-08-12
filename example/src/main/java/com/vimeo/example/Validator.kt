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
package com.vimeo.example

import android.app.AlertDialog

/**
 * Warn the user that they haven't set the client ID or client secret and requests won't work.
 */
@Suppress("ConstantConditionIf")
fun MainActivity.validateClientIdAndClientSecret(): Boolean {
    return if (MainActivity.CLIENT_ID == "CLIENT ID" || MainActivity.CLIENT_SECRET == "CLIENT SECRET") {
        AlertDialog.Builder(this)
            .setTitle("Warning")
            .setMessage("Please update the CLIENT_ID and CLIENT_SECRET in MainActivity.kt in order to make requests")
            .setCancelable(false)
            .show()
        false
    } else {
        true
    }
}
