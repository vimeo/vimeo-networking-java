/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Vimeo
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

package com.vimeo.networking.model.playback;

import com.vimeo.networking.model.VideoFile;
import com.vimeo.stag.GsonAdapterKey;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * An object that holds on to the drm content. There are three types, fairplay, widevine, and playready.
 * Since this is a Java library and only Apple products support fairplay, that type is omitted. Clients will
 * only receive these if given the app-specific permission, essentially this class is not consumable by the
 * general public.
 * <p/>
 * Created by zetterstromk on 6/22/16.
 */
@SuppressWarnings("unused")
public class Drm implements Serializable {

    private static final long serialVersionUID = 3048847922257143776L;

    @Nullable
    @GsonAdapterKey("widevine")
    public VideoFile mWidevine;

    @Nullable
    @GsonAdapterKey("playready")
    public VideoFile mPlayReady;

    @Nullable
    public VideoFile getWidevine() {
        return mWidevine;
    }

    @Nullable
    public VideoFile getPlayReady() {
        return mPlayReady;
    }
}
