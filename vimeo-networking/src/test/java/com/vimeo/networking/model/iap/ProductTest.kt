package com.vimeo.networking.model.iap

import com.vimeo.networking.Utils
import org.junit.Test

/**
 * Tests for the [Product] class
 *
 * Created by brentwatson on 10/30/17.
 */
class ProductTest {

    @Test
    fun verifyTypeAdapterWasGenerated() {
        Utils.verifyTypeAdapterGeneration(Product::class.java)
    }
}
