package com.vimeo.networking.model;

import java.util.ArrayList;

/**
 * Created by hanssena on 4/23/15.
 */
public class UserList extends BaseResponseList<User> {

    @Override
    public Class getModelClass() {
        return User.class;
    }
}
