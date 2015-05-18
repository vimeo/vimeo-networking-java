package com.vimeo.networking.model;

import java.util.ArrayList;

/**
 * Created by hanssena on 4/23/15.
 */
public class UserList
{
    public int total;
    public int page;
    public int perPage;
    public Paging paging;
    public ArrayList<User> data;
}
