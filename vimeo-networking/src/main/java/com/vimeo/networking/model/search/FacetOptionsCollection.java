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

package com.vimeo.networking.model.search;

import com.vimeo.stag.GsonAdapterKey;

import java.io.Serializable;

/**
 * A set of all FacetOption objects
 * <p/>
 * Created by zetterstromk on 6/27/16.
 */
public class FacetOptionsCollection implements Serializable {

    private static final long serialVersionUID = -4855836791704928736L;
    // -----------------------------------------------------------------------------------------------------
    // Type Options
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Type Options">
    @GsonAdapterKey("clip")
    public FacetOptions mVideoOptions;
    @GsonAdapterKey("ondemand")
    public FacetOptions mVodOptions;
    @GsonAdapterKey("people")
    public FacetOptions mUserOptions;
    @GsonAdapterKey("channel")
    public FacetOptions mChannelOptions;
    @GsonAdapterKey("group")
    public FacetOptions mGroupOptions;
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Duration options
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Duration options">
    @GsonAdapterKey("short")
    public FacetOptions mDurationShortOptions;
    @GsonAdapterKey("medium")
    public FacetOptions mDurationMediumOptions;
    @GsonAdapterKey("long")
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Upload options
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Upload options">
    public FacetOptions mDurationLogOptions;
    @GsonAdapterKey("this-year")
    public FacetOptions mUploadedYearOptions;
    @GsonAdapterKey("this-month")
    public FacetOptions mUploadedMonthOptions;
    @GsonAdapterKey("this-week")
    public FacetOptions mUploadedWeekOptions;
    @GsonAdapterKey("today")
    public FacetOptions mUploadedTodayOptions;
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // License options
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="License options">
    @GsonAdapterKey("by")
    public FacetOptions mLicenseAttributionOptions;
    @GsonAdapterKey("by-nc")
    public FacetOptions mLicenseNonCommercialOptions;
    @GsonAdapterKey("by-nc-nd")
    public FacetOptions mLicenseNonCommericalNoDerivativesOptions;
    @GsonAdapterKey("by-nc-sa")
    public FacetOptions mLicenseNonCommercialShareAlikeOptions;
    @GsonAdapterKey("by-nd")
    public FacetOptions mLicenseNoDerivativesOptions;
    @GsonAdapterKey("by-sa")
    public FacetOptions mLicenseShareAlikeOptions;
    @GsonAdapterKey("cc0")
    public FacetOptions mLicensePublicDomainOptions;
    // </editor-fold>

    // TODO: category facet options 6/27/16 [KZ]
    // TODO: people/vod/channel/group facet options 6/27/16 [KZ]
}
