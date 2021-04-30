package com.vimeo.networking2

import android.os.Parcel
import android.os.Parcelable
import io.github.classgraph.ClassGraph
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import uk.co.jemos.podam.api.PodamFactoryImpl


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ModelTest {

    private val factory = PodamFactoryImpl()

    private val models = ClassGraph()
            .addClassLoader(Thread.currentThread().contextClassLoader)
            .enableAllInfo()
            .acceptPackages(PACKAGE)
            .scan()
            .getClassesImplementing(PARCELABLE)
            .loadClasses()

    @Test
    fun `models exist`() {
        assertThat(models).isNotEmpty
    }

    @Test
    fun `models are Parcelable`() {
        models
                .map {
                    // Creates a instance of the model with randomly generated test data and sets `Parcelable`
                    // to any requested generic types.
                    factory.manufacturePojo(
                            it,
                            *(it.typeParameters.map { Parcelable::class.java }.toTypedArray())
                    )
                }
                .filterIsInstance<Parcelable>()
                .forEach { model ->

                    // Serialize object into bytes
                    val serializedBytes = Parcel.obtain().run {
                        writeParcelable(model, 0)
                        marshall()
                    }

                    // Confirm bytes have been created
                    assertThat(serializedBytes).isNotNull()

                    // Deserialize object from bytes
                    val result = Parcel.obtain().run {
                        unmarshall(serializedBytes, 0, serializedBytes.size)
                        setDataPosition(0)
                        readParcelable<Parcelable>(this::class.java.classLoader)
                    }


                    assertThat(result).isEqualToComparingFieldByField(model)
                }
    }

    companion object {
        private const val PACKAGE = "com.vimeo.networking2"
        private const val PARCELABLE = "android.os.Parcelable"
    }
}
