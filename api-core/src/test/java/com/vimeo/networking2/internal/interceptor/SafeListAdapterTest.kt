package com.vimeo.networking2.internal.interceptor

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vimeo.networking2.internal.params.SafeListJsonAdapterFactory
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalStdlibApi::class)
class SafeListAdapterTest {

    private val moshi = Moshi.Builder()
        .add(SafeListJsonAdapterFactory())
        .add(KotlinJsonAdapterFactory())
        .build()

    @Test
    fun `one object test`() {
        val parent = moshi.adapter<Parent>().fromJson(ONE_OBJECT_JSON)
        Assert.assertNotNull(parent)
        assert(parent?.data?.size == 1)
    }

    @Test
    fun `list of objects test`() {
        val parent = moshi.adapter<Parent>().fromJson(LIST_OBJECT_JSON)
        Assert.assertNotNull(parent)
        assert(parent?.data?.size == 2)
    }

    @Test
    fun `empty object test`() {
        val parent = moshi.adapter<Parent>().fromJson(EMPTY_JSON)
        Assert.assertNotNull(parent)
        assert(parent?.data?.size == 0)
    }

    @Test
    fun `list to json test`() {
        val parent = Parent(listOf(Child(1), Child(2)))
        val json = moshi.adapter<Parent>().toJson(parent)
        assert(json.isNotEmpty())
        assert(json.contains("{\"a\":1}"))
    }

    @Test
    fun `empty list to json test`() {
        val parent = Parent(listOf())
        val json = moshi.adapter<Parent>().toJson(parent)
        assert(json.isNotEmpty())
        assert(json.contains("\"data\":[]"))
    }

    @Test
    fun `one object to json test`() {
        val parent = Parent(listOf(Child(1)))
        val json = moshi.adapter<Parent>().toJson(parent)
        assert(json.isNotEmpty())
        assert(json.contains("\"data\":{\"a\":1}"))
    }

    private data class Parent(val data: List<Child>? = null)

    private data class Child(val a: Int? = null)

    companion object {
        private const val EMPTY_JSON = """{
           "data": []
        }"""

        private const val ONE_OBJECT_JSON = """{
           "data": {"a":1}
        }"""

        private const val LIST_OBJECT_JSON = """{
           "data": [{"a":1}, {"a":2}]
        }"""
    }
}
