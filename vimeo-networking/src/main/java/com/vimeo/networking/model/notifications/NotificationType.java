package com.vimeo.networking.model.notifications;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A simple enum describing notification types
 * Created by zetterstromk on 1/11/17.
 */
public enum NotificationType {
    NOTIFICATION_TYPE_COMMENT,
    NOTIFICATION_TYPE_CREDIT,
    NOTIFICATION_TYPE_FOLLOW,
    NOTIFICATION_TYPE_LIKE,
    NOTIFICATION_TYPE_REPLY,
    NOTIFICATION_TYPE_VIDEO_AVAILABLE,
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
            default:
                return NOTIFICATION_TYPE_UNKNOWN;
        }
    }

    @Override
    public String toString() {
        return stringFromNotificationType(this);
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
            case NOTIFICATION_TYPE_UNKNOWN:
            default:
                return null;
        }
    }

}
