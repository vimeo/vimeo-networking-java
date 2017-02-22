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

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.Connection;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A specialized {@link Connection} returned for notification connections
 * <p>
 * Created by zetterstromk on 2/22/17.
 */
@UseStag(FieldOption.SERIALIZED_NAME)
public class NotificationConnection extends Connection {

    private static final long serialVersionUID = 4908222195478449252L;

    @SerializedName("new_total")
    protected int newTotal;

    @SerializedName("unread_total")
    protected int unreadTotal;

    @Nullable
    @SerializedName("type_count")
    protected NotificationTypeCount mTypeCount;

    public int getNewTotal() {
        return newTotal;
    }

    public int getUnreadTotal() {
        return unreadTotal;
    }

    @Nullable
    public NotificationTypeCount getTypeCount() {
        return mTypeCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        NotificationConnection that = (NotificationConnection) o;

        if (newTotal != that.newTotal) { return false; }
        if (unreadTotal != that.unreadTotal) { return false; }
        if (total != that.total) { return false; }
        if (mTypeCount != null ? !mTypeCount.equals(that.mTypeCount) : that.mTypeCount != null) { return false; }
        return uri != null ? uri.equals(that.uri) : that.uri == null;

    }

    @Override
    public int hashCode() {
        int result = newTotal;
        result = 31 * result + unreadTotal;
        result = 31 * result + (mTypeCount != null ? mTypeCount.hashCode() : 0);
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        result = 31 * result + total;
        return result;
    }

    @Override
    public String toString() {
        return "NotificationConnection{" +
               "newTotal=" + newTotal +
               ", unreadTotal=" + unreadTotal +
               ", mTypeCount=" + mTypeCount +
               ", uri='" + uri + '\'' +
               ", total=" + total +
               '}';
    }

    @UseStag(FieldOption.SERIALIZED_NAME)
    public static final class NotificationTypeCount implements Serializable {

        private static final long serialVersionUID = 6893381498380227512L;

        @SerializedName(NotificationConstants.NOTIFICATION_COMMENT)
        protected int mCommentTotal;

        @SerializedName(NotificationConstants.NOTIFICATION_CREDIT)
        protected int mCreditTotal;

        @SerializedName(NotificationConstants.NOTIFICATION_LIKE)
        protected int mLikeTotal;

        @SerializedName(NotificationConstants.NOTIFICATION_SHARE)
        protected int mShareTotal;

        @SerializedName(NotificationConstants.NOTIFICATION_VIDEO_AVAILABLE)
        protected int mVideoAvailableTotal;

        @SerializedName(NotificationConstants.NOTIFICATION_MENTION)
        protected int mMentionTotal;

        @SerializedName(NotificationConstants.NOTIFICATION_REPLY)
        protected int mReplyTotal;

        @SerializedName(NotificationConstants.NOTIFICATION_STORAGE_WARNING)
        protected int mStorageWarningTotal;

        @SerializedName(NotificationConstants.NOTIFICATION_FOLLOW)
        protected int mFollowTotal;

        @SerializedName(NotificationConstants.NOTIFICATION_ACCOUNT_EXPIRATION_WARNING)
        protected int mAccountExpirationWarningTotal;

        @SerializedName(NotificationConstants.NOTIFICATION_VOD_PURCHASE)
        protected int mVodPurchaseTotal;

        @SerializedName(NotificationConstants.NOTIFICATION_VOD_PREORDER_AVAILABLE)
        protected int mVodPreorderAvailableTotal;

        @SerializedName(NotificationConstants.NOTIFICATION_VOD_RENTAL_EXPIRATION_WARNING)
        protected int mVodRentailExpirationWarningTotal;

        public int getCommentTotal() {
            return mCommentTotal;
        }

        public int getCreditTotal() {
            return mCreditTotal;
        }

        public int getLikeTotal() {
            return mLikeTotal;
        }

        public int getShareTotal() {
            return mShareTotal;
        }

        public int getVideoAvailableTotal() {
            return mVideoAvailableTotal;
        }

        public int getMentionTotal() {
            return mMentionTotal;
        }

        public int getReplyTotal() {
            return mReplyTotal;
        }

        public int getStorageWarningTotal() {
            return mStorageWarningTotal;
        }

        public int getFollowTotal() {
            return mFollowTotal;
        }

        public int getAccountExpirationWarningTotal() {
            return mAccountExpirationWarningTotal;
        }

        public int getVodPurchaseTotal() {
            return mVodPurchaseTotal;
        }

        public int getVodPreorderAvailableTotal() {
            return mVodPreorderAvailableTotal;
        }

        public int getVodRentailExpirationWarningTotal() {
            return mVodRentailExpirationWarningTotal;
        }
    }
}
