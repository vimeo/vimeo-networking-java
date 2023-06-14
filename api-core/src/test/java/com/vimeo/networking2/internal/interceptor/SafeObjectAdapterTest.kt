package com.vimeo.networking2.internal.interceptor

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vimeo.networking2.annotations.SafeObject
import com.vimeo.networking2.internal.params.SafeObjectJsonAdapterFactory
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalStdlibApi::class)
class SafeObjectAdapterTest {

    private val moshi = Moshi.Builder()
        .add(SafeObjectJsonAdapterFactory())
        .add(KotlinJsonAdapterFactory())
        .build()

    @Test
    fun `one object test`() {
        val parent = moshi.adapter<Parent>().fromJson(SINGLE_OBJECT_JSON)
        Assert.assertNotNull(parent)
        Assert.assertNotNull(parent?.data)
    }

    @Test
    fun `empty object test`() {
        val parent = moshi.adapter<Parent>().fromJson(EMPTY_JSON)
        Assert.assertNotNull(parent)
        Assert.assertNull(parent?.data)
    }

    @Test
    fun `empty list to json test`() {
        val parent = Parent()
        val json = moshi.adapter<Parent>().toJson(parent)
        assert(json.isNotEmpty())
        assert(json.contains("\"data\":[]"))
    }

    @Test
    fun `one object to json test`() {
        val parent = Parent(Child(1))
        val json = moshi.adapter<Parent>().toJson(parent)
        assert(json.isNotEmpty())
        assert(json.contains("\"data\":{\"a\":1}"))
    }

    private data class Parent(@SafeObject val data: Child? = null)

    private data class Child(val a: Int? = null)

    companion object {
        private const val EMPTY_JSON = """{
           "data": []
        }"""

        private const val SINGLE_OBJECT_JSON = """{
           "data": {"a":1}
        }"""
    }
}
