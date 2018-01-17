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
 * A specialized {@link Connection} returned for notification connections.
 * <p>
 * Created by zetterstromk on 2/22/17.
 */
@UseStag(FieldOption.SERIALIZED_NAME)
public class NotificationConnection extends Connection {

    private static final long serialVersionUID = -8043872922883259170L;

    @SerializedName("new_total")
    protected int mNewTotal;

    @SerializedName("unread_total")
    protected int mUnreadTotal;

    @Nullable
    @SerializedName("type_count")
    protected NotificationTypeCount mTypeCount;

    @Nullable
    @SerializedName("type_unseen_count")
    protected NotificationTypeCount mTypeUnseenCount;

    public int getNewTotal() {
        return mNewTotal;
    }

    public int getUnreadTotal() {
        return mUnreadTotal;
    }

    @Nullable
    public NotificationTypeCount getTypeCount() {
        return mTypeCount;
    }

    @Nullable
    public NotificationTypeCount getTypeUnseenCount() {
        return mTypeUnseenCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        NotificationConnection that = (NotificationConnection) o;

        if (mNewTotal != that.mNewTotal) { return false; }
        if (mUnreadTotal != that.mUnreadTotal) { return false; }
        if (mTotal != that.mTotal) { return false; }
        if (mTypeCount != null ? !mTypeCount.equals(that.mTypeCount) : that.mTypeCount != null) { return false; }
        if (mTypeUnseenCount != null ? !mTypeUnseenCount.equals(that.mTypeUnseenCount) : that.mTypeUnseenCount != null) { return false; }
        return mUri != null ? mUri.equals(that.mUri) : that.mUri == null;

    }

    @Override
    public int hashCode() {
        int result = mNewTotal;
        result = 31 * result + mUnreadTotal;
        result = 31 * result + (mTypeCount != null ? mTypeCount.hashCode() : 0);
        result = 31 * result + (mTypeUnseenCount != null ? mTypeUnseenCount.hashCode() : 0);
        result = 31 * result + (mUri != null ? mUri.hashCode() : 0);
        result = 31 * result + mTotal;
        return result;
    }

    @Override
    public String toString() {
        return "NotificationConnection{" +
               "newTotal=" + mNewTotal +
               ", unreadTotal=" + mUnreadTotal +
               ", mTypeCount=" + mTypeCount +
               ", mTypeUnseenCount=" + mTypeUnseenCount +
               ", uri='" + mUri + '\'' +
               ", total=" + mTotal +
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

        @SerializedName(NotificationConstants.NOTIFICATION_FOLLOWED_USER_VIDEO_AVAILABLE)
        protected int mFollowedUserVideoAvailableTotal;

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

        @SerializedName(NotificationConstants.NOTIFICATION_TVOD_PURCHASE)
        protected int mTvodPurchaseTotal;

        @SerializedName(NotificationConstants.NOTIFICATION_TVOD_PREORDER_AVAILABLE)
        protected int mTvodPreorderAvailableTotal;

        @SerializedName(NotificationConstants.NOTIFICATION_TVOD_RENTAL_EXPIRATION_WARNING)
        protected int mTvodRentailExpirationWarningTotal;

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

        /**
         * @return the count of followed user video available notifications
         */
        public int getFollowedUserVideoAvailableTotal() {
            return mFollowedUserVideoAvailableTotal;
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

        public int getTvodPurchaseTotal() {
            return mTvodPurchaseTotal;
        }

        public int getTvodPreorderAvailableTotal() {
            return mTvodPreorderAvailableTotal;
        }

        public int getTvodRentailExpirationWarningTotal() {
            return mTvodRentailExpirationWarningTotal;
        }
    }
}
