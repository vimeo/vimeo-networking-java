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

import com.vimeo.networking.Vimeo.LogLevel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Simple utility class to delegate logging when it is not known whether VimeoClient has been initialized yet
 * <p>
 * Created by zetterstromk on 6/22/16.
 */
@SuppressWarnings("UseOfSystemOutOrSystemErr")
public final class ClientLogger {

    @Nullable
    private static LogProvider sLoggerProvider;

    @NotNull
    private static LogLevel sLogLevel = LogLevel.DEBUG;


    private ClientLogger() {
    }

    public static void setLogProvider(@Nullable LogProvider logProvider) {
        sLoggerProvider = logProvider;
    }

    public static void setLogLevel(@NotNull LogLevel logLevel) {
        sLogLevel = logLevel;
    }

    public static LogLevel getLogLevel() {
        return sLogLevel;
    }

    public static void e(String error) {
        if (sLogLevel.ordinal() <= LogLevel.ERROR.ordinal()) {
            if (sLoggerProvider != null) {
                sLoggerProvider.e(error);
            } else {
                System.out.println(error);
            }
        }
    }

    public static void e(String error, Exception exception) {
        if (sLogLevel.ordinal() <= LogLevel.ERROR.ordinal()) {
            if (sLoggerProvider != null) {
                sLoggerProvider.e(error, exception);
            } else {
                System.out.println(error);
                exception.printStackTrace();
            }
        }
    }

    public static void d(String debug) {
        if (sLogLevel.ordinal() <= LogLevel.DEBUG.ordinal()) {
            if (sLoggerProvider != null) {
                sLoggerProvider.d(debug);
            } else {
                System.out.println(debug);
            }
        }
    }

    public static void v(String verbose) {
        if (sLogLevel.ordinal() <= LogLevel.VERBOSE.ordinal()) {
            if (sLoggerProvider != null) {
                sLoggerProvider.v(verbose);
            } else {
                System.out.println(verbose);
            }
        }
    }
}
