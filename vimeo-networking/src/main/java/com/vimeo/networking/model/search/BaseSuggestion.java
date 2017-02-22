package com.vimeo.networking.model.search;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * A base class for search suggestions. Each suggest must contain a <code>text</code>
 * string.
 * <p>
 * Created by zetterstromk on 2/21/17.
 */
@UseStag(FieldOption.SERIALIZED_NAME)
abstract class BaseSuggestion implements Serializable {

    @NotNull
    @SerializedName("text")
    String mText;

    /**
     * Returns the title associated with this suggestion.
     */
    @NotNull
    public String getText() {
        return mText;
    }
}
