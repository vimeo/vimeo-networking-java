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
    protected String projection;

    @NotNull
    @SerializedName("stereo_format")
    protected String stereoFormat;

    @NotNull
    public Projection getProjection() {
        switch (projection) {
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
        switch (stereoFormat) {
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

        return projection.equals(spatial.projection) && stereoFormat.equals(spatial.stereoFormat);
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
               "projection='" + projection + '\'' +
               ", stereoFormat='" + stereoFormat + '\'' +
               '}';
    }

    @Override
    public int hashCode() {
        int result = projection.hashCode();
        result = 31 * result + stereoFormat.hashCode();
        result = 31 * result + super.hashCode();
        return result;
    }
}
