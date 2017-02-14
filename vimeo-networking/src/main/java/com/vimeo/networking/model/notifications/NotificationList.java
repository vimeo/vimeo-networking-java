package com.vimeo.networking.model.notifications;

import com.vimeo.networking.model.BaseResponseList;
import com.vimeo.stag.UseStag;

/**
 * The response from the notifications endpoint
 * <p>
 * Created by zetterstromk on 1/11/17.
 */
@SuppressWarnings("unused")
@UseStag
public class NotificationList extends BaseResponseList<Notification> {

    private static final long serialVersionUID = -6084940916920098832L;

    @Override
    public Class<Notification> getModelClass() {
        return Notification.class;
    }
}
