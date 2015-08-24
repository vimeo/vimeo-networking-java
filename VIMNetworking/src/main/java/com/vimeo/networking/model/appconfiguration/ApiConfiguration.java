package com.vimeo.networking.model.appconfiguration;

import com.vimeo.networking.Vimeo;

/**
 * Created by vennk on 5/20/15.
 */
public class ApiConfiguration {

    // Default host so if not set (from no internet connection) then we still have a base url
    public String host = Vimeo.VIMEO_BASE_URL_STRING;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApiConfiguration that = (ApiConfiguration) o;

        return !(host != null ? !host.equals(that.host) : that.host != null);

    }

    @Override
    public int hashCode() {
        return host != null ? host.hashCode() : 0;
    }
}
