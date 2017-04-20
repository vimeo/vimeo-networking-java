package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link UserBadge}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class UserBadgeTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(UserBadge.class);
    }

    @Test
    public void verifyTypeAdapterWasNotGenerated_UserBadgeType() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(UserBadge.UserBadgeType.class);
    }
}