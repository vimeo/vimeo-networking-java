package com.vimeo.networking2

import io.github.classgraph.ClassGraph
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import uk.co.jemos.podam.api.PodamFactoryImpl
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class ModelTest {

    private val factory = PodamFactoryImpl()

    private val models = ClassGraph()
            .addClassLoader(Thread.currentThread().contextClassLoader)
            .enableAllInfo()
            .acceptPackages(PACKAGE)
            .scan()
            .getClassesImplementing(SERIALIZABLE)
            .loadClasses()

    @Test
    fun `models are Serializable`() {
        models
                .map {
                    // Creates a instance of the model with randomly generated test data and sets `Any`
                    // to any requested generic types.
                    factory.manufacturePojo(
                            it,
                            *(it.typeParameters.map { Any::class.java }.toTypedArray())
                    )
                }
                .forEach { model ->
                    // Serialize object into bytes
                    val serializedBytes = ByteArrayOutputStream().use { byteArrayOutputStream ->
                        ObjectOutputStream(byteArrayOutputStream).use {
                            it.writeObject(model)
                        }
                        byteArrayOutputStream.toByteArray()
                    }

                    // Confirm bytes have been created
                    assertThat(serializedBytes).isNotNull()

                    // Deserialize object from bytes
                    val result = ByteArrayInputStream(serializedBytes).use {
                        ObjectInputStream(it).use {
                            it.readObject()
                        }
                    }

                    assertThat(result).isEqualToComparingFieldByField(model)
                }
    }

    companion object {
        private const val PACKAGE = "com.vimeo.networking2"
        private const val SERIALIZABLE = "java.io.Serializable"
    }
}