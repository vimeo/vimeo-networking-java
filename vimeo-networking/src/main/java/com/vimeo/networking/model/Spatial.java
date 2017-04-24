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

package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * The spatial object representing the format
 * data for a 360 video object.
 * <p>
 * Created by restainoa on 1/3/17.
 */
@SuppressWarnings("unused")
@UseStag
public class Spatial implements Serializable {

    private static final long serialVersionUID = 5660325676029549468L;

    public enum Projection {
        EQUIRECTANGULAR,
        CYLINDRICAL,
        CUBICAL,
        PYRAMID,
        DOME,
        UNKNOWN
    }

    public enum Format {
        MONO,
        LEFT_RIGHT,
        TOP_BOTTOM,
        UNKNOWN
    }

    @NotNull
    @SerializedName(value = "projection", alternate = "m_projection")
    protected String mProjection;

    @NotNull
    @SerializedName(value = "stereo_format", alternate = "m_stereo_format")
    protected String mStereoFormat;

    @NotNull
    public Projection getProjection() {
        switch (mProjection) {
            case "equirectangular":
                return Projection.EQUIRECTANGULAR;
            case "cylindrical":
                return Projection.CYLINDRICAL;
            case "cubical":
                return Projection.CUBICAL;
            case "pyramid":
                return Projection.PYRAMID;
            case "dome":
                return Projection.DOME;
            default:
                return Projection.UNKNOWN;
        }
    }

    @NotNull
    public Format getFormat() {
        switch (mStereoFormat) {
            case "mono":
                return Format.MONO;
            case "left-right":
                return Format.LEFT_RIGHT;
            case "top-bottom":
                return Format.TOP_BOTTOM;
            default:
                return Format.UNKNOWN;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Spatial spatial = (Spatial) o;

        return mProjection.equals(spatial.mProjection) && mStereoFormat.equals(spatial.mStereoFormat);
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
               "projection='" + mProjection + '\'' +
               ", stereoFormat='" + mStereoFormat + '\'' +
               '}';
    }

    @Override
    public int hashCode() {
        int result = mProjection.hashCode();
        result = 31 * result + mStereoFormat.hashCode();
        result = 31 * result + super.hashCode();
        return result;
    }
}
