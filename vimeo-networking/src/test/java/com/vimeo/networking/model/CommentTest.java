package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link Comment}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class CommentTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(Comment.class);
    }

    @Test
    public void verifyTypeAdapterWasGenerated_CommentType() throws Exception {
        Utils.verifyTypeAdapterGeneration(Comment.CommentType.class);
    }
}