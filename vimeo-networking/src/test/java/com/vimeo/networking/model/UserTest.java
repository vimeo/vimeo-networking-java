package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link User}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class UserTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(User.class);
    }

    @Test
    public void verifyTypeAdapterWasNotGenerated_AccountType() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(User.AccountType.class);
    }
}