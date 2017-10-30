package com.vimeo.networking.model.iap

import com.vimeo.networking.Utils
import org.junit.Test

/**
 * Tests for the [Products] class
 *
 * Created by brentwatson on 10/30/17.
 */
class ProductsTest {

    @Test
    fun verifyTypeAdapterWasGenerated() {
        Utils.verifyTypeAdapterGeneration(Products::class.java)
    }
}
