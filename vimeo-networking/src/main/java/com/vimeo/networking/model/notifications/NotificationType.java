/*
 * Copyright (c) 2017 Vimeo (https://vimeo.com)
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

package com.vimeo.networking.model.notifications;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A simple enum describing notification types.
 * This enum is not directly returned by the API.
 * Instead, a string is returned by the API in
 * {@link Notification#mType}, and then the
 * {@link Notification#getNotificationType()}
 * chooses the correct enum.
 * <p>
 * Created by zetterstromk on 1/11/17.
 */
@SuppressWarnings("unused")
public enum NotificationType {
    NOTIFICATION_TYPE_COMMENT,
    NOTIFICATION_TYPE_CREDIT,
    NOTIFICATION_TYPE_FOLLOW,
    NOTIFICATION_TYPE_LIKE,
    NOTIFICATION_TYPE_REPLY,
    NOTIFICATION_TYPE_VIDEO_AVAILABLE,
    NOTIFICATION_TYPE_FOLLOWED_USER_VIDEO_AVAILABLE,
    NOTIFICATION_TYPE_UNKNOWN;

    @NotNull
    public static NotificationType notificationTypeFromString(@NotNull String type) {
        switch (type) {
            case NotificationConstants.NOTIFICATION_COMMENT:
                return NOTIFICATION_TYPE_COMMENT;
            case NotificationConstants.NOTIFICATION_CREDIT:
                return NOTIFICATION_TYPE_CREDIT;
            case NotificationConstants.NOTIFICATION_FOLLOW:
                return NOTIFICATION_TYPE_FOLLOW;
            case NotificationConstants.NOTIFICATION_LIKE:
                return NOTIFICATION_TYPE_LIKE;
            case NotificationConstants.NOTIFICATION_REPLY:
                return NOTIFICATION_TYPE_REPLY;
            case NotificationConstants.NOTIFICATION_VIDEO_AVAILABLE:
                return NOTIFICATION_TYPE_VIDEO_AVAILABLE;
            case NotificationConstants.NOTIFICATION_FOLLOWED_USER_VIDEO_AVAILABLE:
                return NOTIFICATION_TYPE_FOLLOWED_USER_VIDEO_AVAILABLE;
            default:
                return NOTIFICATION_TYPE_UNKNOWN;
        }
    }

    @Nullable
    public static String stringFromNotificationType(@NotNull NotificationType type) {
        switch (type) {
            case NOTIFICATION_TYPE_COMMENT:
                return NotificationConstants.NOTIFICATION_COMMENT;
            case NOTIFICATION_TYPE_CREDIT:
                return NotificationConstants.NOTIFICATION_CREDIT;
            case NOTIFICATION_TYPE_FOLLOW:
                return NotificationConstants.NOTIFICATION_FOLLOW;
            case NOTIFICATION_TYPE_LIKE:
                return NotificationConstants.NOTIFICATION_LIKE;
            case NOTIFICATION_TYPE_REPLY:
                return NotificationConstants.NOTIFICATION_REPLY;
            case NOTIFICATION_TYPE_VIDEO_AVAILABLE:
                return NotificationConstants.NOTIFICATION_VIDEO_AVAILABLE;
            case NOTIFICATION_TYPE_FOLLOWED_USER_VIDEO_AVAILABLE:
                return NotificationConstants.NOTIFICATION_FOLLOWED_USER_VIDEO_AVAILABLE;
            case NOTIFICATION_TYPE_UNKNOWN:
            default:
                return null;
        }
    }

}
