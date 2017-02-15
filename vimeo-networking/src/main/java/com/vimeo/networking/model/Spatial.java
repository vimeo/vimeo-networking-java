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
    @SerializedName("projection")
    protected String mProjection;

    @NotNull
    @SerializedName("stereo_format")
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
