package com.vimeo.networking.model.notifications;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link NotificationType}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class NotificationTypeTest {

    @Test
    public void verifyTypeAdapterWasNotGenerated() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(NotificationType.class);
    }
}