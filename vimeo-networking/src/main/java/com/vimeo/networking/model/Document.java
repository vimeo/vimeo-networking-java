package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A model used when retrieving documents from the API, such as Privacy Policy,
 * Terms of Service, and Payment Addendum
 * <p>
 * Created by zetterstromk on 11/7/16.
 */
@SuppressWarnings("unused")
@UseStag
public class Document implements Serializable {

    private static final long serialVersionUID = -8676604257868932660L;

    @Nullable
    @SerializedName("html")
    protected String html;

    @Nullable
    public String getHtml() {
        return html;
    }
}
