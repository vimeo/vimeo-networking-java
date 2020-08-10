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
package com.vimeo.networking2.common

import com.vimeo.networking2.enums.ApiOptionsType
import com.vimeo.networking2.enums.asEnumList

/**
 * Information on how to take access data related to an entity. The [options] list contains all the possible type of
 * HTTP requests that are supported for the connection.
 */
interface Connection {
    /**
     * An array of HTTP methods permitted on this URI.
     */
    val options: List<String>?

    /**
     * The API URI that resolves to the connection data.
     */
    val uri: String?
}

/**
 * @see Connection.options
 * @see ApiOptionsType
 */
val Connection.optionsTypes: List<ApiOptionsType>
    get() = options.asEnumList(ApiOptionsType.UNKNOWN)
