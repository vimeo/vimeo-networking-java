package com.vimeo.networking.model;

import java.io.Serializable;

import javax.annotation.Nullable;

/**
 * Created by kylevenn on 9/29/15.
 */
public abstract class BaseVideo implements Serializable {

    private static final long serialVersionUID = -4054120629377152001L;

    @Nullable
    abstract public Video getVideo();

    @Nullable
    abstract public String getRelatedUri();

    @Nullable
    public String getVideoUri() {
        return getVideo() == null ? null : getVideo().uri;
    }

    @Nullable
    public String getName() {
        return getVideo() == null ? null : getVideo().name;
    }

    @Nullable
    public String getDescription() {
        return getVideo() == null ? null : getVideo().description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseVideo that = (BaseVideo) o;

        return (this.getVideoUri() != null && that.getVideoUri() != null) &&
               (this.getVideoUri().equals(that.getVideoUri()));
    }

    @Override
    public int hashCode() {
        return this.getVideoUri() != null ? this.getVideoUri().hashCode() : 0;
    }
}
