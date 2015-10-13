package com.vimeo.networking.model.Upload;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.User;

/**
 * Created by kylevenn on 8/19/15.
 */
public class UploadTicket {

    public String uri;
    @SerializedName("complete_uri")
    public String completeUri;
    @SerializedName("ticket_id")
    public String ticketId;
    public User user;
    @SerializedName("upload_link_secure")
    public String uploadLinkSecure;
}
