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

/**
 * Constants shared among notification related classes.
 * <p>
 * Created by zetterstromk on 1/11/17.
 */
public final class NotificationConstants {

    public static final String NOTIFICATION_COMMENT = "comment";
    public static final String NOTIFICATION_CREDIT = "credit";
    public static final String NOTIFICATION_LIKE = "like";
    public static final String NOTIFICATION_REPLY = "reply";
    public static final String NOTIFICATION_FOLLOW = "follow";
    public static final String NOTIFICATION_VIDEO_AVAILABLE = "video_available";
    public static final String NOTIFICATION_SHARE = "share";
    public static final String NOTIFICATION_MENTION = "mention";
    public static final String NOTIFICATION_STORAGE_WARNING = "storage_warning";
    public static final String NOTIFICATION_ACCOUNT_EXPIRATION_WARNING = "account_expiration_warning";
    public static final String NOTIFICATION_TVOD_PURCHASE = "vod_purchase";
    public static final String NOTIFICATION_TVOD_PREORDER_AVAILABLE = "vod_preorder_available";
    public static final String NOTIFICATION_TVOD_RENTAL_EXPIRATION_WARNING = "vod_rental_expiration_warning";
    public static final String NOTIFICATION_FOLLOWED_USER_VIDEO_AVAILABLE = "followed_user_video_available";

    private NotificationConstants() {
    }
}
