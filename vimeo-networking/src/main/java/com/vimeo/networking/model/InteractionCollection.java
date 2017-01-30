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

package com.vimeo.networking.model;

import com.vimeo.stag.GsonAdapterKey;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A collection of Interaction objects.
 * Created by zetterstromk on 6/5/15.
 */
@UseStag(FieldOption.SERIALIZED_NAME)
public class InteractionCollection implements Serializable {

    private static final long serialVersionUID = 489519386122782640L;
    @Nullable
    @GsonAdapterKey("watchlater")
    public Interaction watchlater;
    @Nullable
    @GsonAdapterKey("like")
    public Interaction like;
    @Nullable
    @GsonAdapterKey("follow")
    public Interaction follow;
    @Nullable
    @GsonAdapterKey("buy")
    public Interaction buy;
    @Nullable
    @GsonAdapterKey("rent")
    public Interaction rent;
    @Nullable
    @GsonAdapterKey("subscribe")
    public Interaction subscribe;
}
