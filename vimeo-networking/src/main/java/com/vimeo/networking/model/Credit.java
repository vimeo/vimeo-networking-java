package com.vimeo.networking.model;

import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A model representing a credit.
 * Created by zetterstromk on 1/11/17.
 */
@SuppressWarnings("unused")
@UseStag
public class Credit implements Serializable {

    private static final long serialVersionUID = 6037404487282167384L;

    @Nullable
    protected String uri;

    @Nullable
    protected String role;

    @Nullable
    protected String name;

    @Nullable
    protected Video video;

    @Nullable
    protected User user;

    @Nullable
    public String getUri() {
        return uri;
    }

    @Nullable
    public String getRole() {
        return role;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public Video getVideo() {
        return video;
    }

    public void setVideo(@Nullable Video video) {
        this.video = video;
    }

    @Nullable
    public User getUser() {
        return user;
    }

    public void setUser(@Nullable User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Credit credit = (Credit) o;

        if (uri != null ? !uri.equals(credit.uri) : credit.uri != null) {
            return false;
        }
        if (role != null ? !role.equals(credit.role) : credit.role != null) {
            return false;
        }
        if (name != null ? !name.equals(credit.name) : credit.name != null) {
            return false;
        }
        if (video != null ? !video.equals(credit.video) : credit.video != null) {
            return false;
        }
        return user != null ? user.equals(credit.user) : credit.user == null;

    }

    @Override
    public int hashCode() {
        int result = uri != null ? uri.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (video != null ? video.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Credit{" +
               "uri='" + uri + '\'' +
               ", role='" + role + '\'' +
               ", name='" + name + '\'' +
               ", video=" + video +
               ", user=" + user +
               '}';
    }
}
