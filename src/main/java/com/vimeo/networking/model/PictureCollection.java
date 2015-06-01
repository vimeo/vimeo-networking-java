package com.vimeo.networking.model;

import java.util.ArrayList;

/**
 * Created by hanssena on 4/23/15.
 */
public class PictureCollection {

    public String uri;
    public boolean active;
    public ArrayList<Picture> sizes;

    public Picture pictureForWidth(int width) {
        if (sizes != null && !sizes.isEmpty()) {
            Picture selectedPicture = sizes.get(sizes.size() - 1);
            for (Picture picture : sizes) {
                if ((picture.width >= width) && ((picture.width - width) < (selectedPicture.width - width))) {
                    selectedPicture = picture;
                }
            }
            return selectedPicture;
        }

        return null;
    }
}
