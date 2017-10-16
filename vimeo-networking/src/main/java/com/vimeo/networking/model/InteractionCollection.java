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

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A collection of Interaction objects.
 * Created by zetterstromk on 6/5/15.
 */
@SuppressWarnings("unused")
@UseStag
public class InteractionCollection implements Serializable {

    private static final long serialVersionUID = 489519386122782640L;

    @Nullable
    @SerializedName("watchlater")
    protected Interaction mWatchLater;

    @Nullable
    @SerializedName("like")
    protected Interaction mLike;

    @Nullable
    @SerializedName("follow")
    protected Interaction mFollow;

    @Nullable
    @SerializedName("buy")
    protected Interaction mBuy;

    @Nullable
    @SerializedName("rent")
    protected Interaction mRent;

    @Nullable
    @SerializedName("subscribe")
    protected Interaction mSubscribe;

    @Nullable
    @SerializedName("channel")
    protected Interaction mChannelMembership;

    @Nullable
    @SerializedName("report")
    protected Interaction mReport;

    @Nullable
    @SerializedName("add_moderators")
    protected Interaction mAddModerators;

    @Nullable
    @SerializedName("moderate_videos")
    protected Interaction mModerateVideos;

    @Nullable
    public Interaction getWatchLater() {
        return mWatchLater;
    }

    /**
     * @return the {@link Interaction} for sending a terms of service violation report for the parent object
     */
    @Nullable
    public Interaction getReport() {
        return mReport;
    }

    @Nullable
    public Interaction getLike() {
        return mLike;
    }

    @Nullable
    public Interaction getFollow() {
        return mFollow;
    }

    @Nullable
    public Interaction getBuy() {
        return mBuy;
    }

    @Nullable
    public Interaction getRent() {
        return mRent;
    }

    @Nullable
    public Interaction getSubscribe() {
        return mSubscribe;
    }

    @Nullable
    public Interaction getChannelMembership() {
        return mChannelMembership;
    }

    public void setChannelMembership(@Nullable Interaction channelMembership) {
        mChannelMembership = channelMembership;
    }

    public void setReport(@Nullable Interaction report) {
        mReport = report;
    }

    @Nullable
    public Interaction getAddModerators() {
        return mAddModerators;
    }

    public void setAddModerators(@Nullable Interaction addModerators) {
        mAddModerators = addModerators;
    }

    @Nullable
    public Interaction getModerateVideos() {
        return mModerateVideos;
    }

    public void setModerateVideos(@Nullable Interaction moderateVideos) {
        mModerateVideos = moderateVideos;
    }

    public void setLike(@Nullable Interaction like) {
        mLike = like;
    }

    public void setWatchLater(@Nullable Interaction watchLater) {
        mWatchLater = watchLater;
    }

    public void setFollow(@Nullable Interaction follow) {
        mFollow = follow;
    }

    public void setBuy(@Nullable Interaction buy) {
        mBuy = buy;
    }

    public void setRent(@Nullable Interaction rent) {
        mRent = rent;
    }

    public void setSubscribe(@Nullable Interaction subscribe) {
        mSubscribe = subscribe;
    }

}
