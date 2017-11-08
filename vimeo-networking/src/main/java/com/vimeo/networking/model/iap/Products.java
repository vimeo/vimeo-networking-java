package com.vimeo.networking.model.iap;

import com.vimeo.networking.model.BaseResponseList;
import com.vimeo.stag.UseStag;

/**
 * List of {@link Product} objects.
 *
 * Created by brentwatson on 10/30/17.
 */
@UseStag
public class Products extends BaseResponseList<Product> {
    private static final long serialVersionUID = -7018406547919579349L;

    @Override
    public Class<Product> getModelClass() {
        return Product.class;
    }
}
