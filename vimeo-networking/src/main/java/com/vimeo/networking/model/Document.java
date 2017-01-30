package com.vimeo.networking.model;

import com.vimeo.stag.GsonAdapterKey;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A model used when retrieving documents from the API, such as Privacy Policy,
 * Terms of Service, and Payment Addendum
 * <p>
 * Created by zetterstromk on 11/7/16.
 */
@UseStag(FieldOption.SERIALIZED_NAME)
public class Document implements Serializable {

    private static final long serialVersionUID = -8676604257868932660L;

    @GsonAdapterKey("html")
    @Nullable
    String mHtml;

    @Nullable
    public String getHtml() {
        return mHtml;
    }
}
