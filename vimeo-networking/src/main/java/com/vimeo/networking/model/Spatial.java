package com.vimeo.networking.model;

import com.vimeo.stag.GsonAdapterKey;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * The spatial object representing the format
 * data for a 360 video object.
 * <p>
 * Created by restainoa on 1/3/17.
 */
public class Spatial implements Serializable {

    private static final long serialVersionUID = 5660325676029549468L;

    public enum Projection {
        EQUIRECTANGULAR,
        CYLINDRICAL,
        CUBICAL,
        PYRAMID,
        DOME
    }

    public enum Format {
        MONO,
        LEFT_RIGHT,
        TOP_BOTTOM
    }

    @NotNull
    @GsonAdapterKey("projection")
    protected String mProjection;

    @NotNull
    @GsonAdapterKey("stereo_format")
    protected String mStereoFormat;

    @Nullable
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
                return null;
        }
    }

    @Nullable
    public Format getFormat() {
        switch (mStereoFormat) {
            case "mono":
                return Format.MONO;
            case "left-right":
                return Format.LEFT_RIGHT;
            case "top-bottom":
                return Format.TOP_BOTTOM;
            default:
                return null;
        }
    }
}
