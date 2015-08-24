package com.vimeo.networking.model.appconfiguration;

/**
 * Created by vennk on 5/20/15.
 */
public class AppConfiguration {
    
    public FacebookConfiguration facebook;
    public ApiConfiguration api;
    public FeaturesConfiguration features;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppConfiguration that = (AppConfiguration) o;

        if (facebook != null ? !facebook.equals(that.facebook) : that.facebook != null) {
            return false;
        }
        if (api != null ? !api.equals(that.api) : that.api != null) {
            return false;
        }
        return !(features != null ? !features.equals(that.features) : that.features != null);

    }

    @Override
    public int hashCode() {
        int result = facebook != null ? facebook.hashCode() : 0;
        result = 31 * result + (api != null ? api.hashCode() : 0);
        result = 31 * result + (features != null ? features.hashCode() : 0);
        return result;
    }
}
