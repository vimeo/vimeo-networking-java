package com.vimeo.networking.model;

import com.vimeo.networking.Utils;
import com.vimeo.networking.model.Privacy.CommentValue;
import com.vimeo.networking.model.Privacy.EmbedValue;
import com.vimeo.networking.model.Privacy.ViewValue;

import org.junit.Test;

/**
 * Unit tests for {@link Privacy}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class PrivacyTest {

    @Test
    public void verifyTypeAdapterWasNotGenerated() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(Privacy.class);
    }

    @Test
    public void verifyTypeAdapterWasGenerated_PrivacyCommentValue() throws Exception {
        Utils.verifyTypeAdapterGeneration(CommentValue.class);
    }

    @Test
    public void verifyTypeAdapterWasGenerated_PrivacyEmbedValue() throws Exception {
        Utils.verifyTypeAdapterGeneration(EmbedValue.class);
    }

    @Test
    public void verifyTypeAdapterWasGenerated_PrivacyViewValue() throws Exception {
        Utils.verifyTypeAdapterGeneration(ViewValue.class);
    }

}
