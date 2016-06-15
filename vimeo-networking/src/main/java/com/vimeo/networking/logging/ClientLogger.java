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

package com.vimeo.networking.logging;

import com.vimeo.networking.VimeoClient;

/**
 * Simple utility class to delegate logging when it is not known whether VimeoClient has been initialized yet
 * <p/>
 * Created by zetterstromk on 6/15/16.
 */
public final class ClientLogger {

    private static DebugLoggerInterface sLogger;

    private ClientLogger() {
    }

    private static void initializeLogger() {
        if (sLogger == null) {
            VimeoClient vimeoClient = null;
            try {
                vimeoClient = VimeoClient.getInstance();
            } catch (AssertionError e) {
                //VimeoClient has yet to be instantiated, but the user of this class may not care
            }
            if (vimeoClient != null) {
                sLogger = vimeoClient.getDebugLogger();
            } else {
                sLogger = new DebugLogger();
            }
        }
    }

    public static void e(String error) {
        initializeLogger();
        sLogger.e(error);
    }

    public static void e(String error, Exception exception) {
        initializeLogger();
        sLogger.e(error, exception);
    }

    public static void d(String debug) {
        initializeLogger();
        sLogger.d(debug);
    }

    public static void v(String verbose) {
        initializeLogger();
        sLogger.v(verbose);
    }
}
