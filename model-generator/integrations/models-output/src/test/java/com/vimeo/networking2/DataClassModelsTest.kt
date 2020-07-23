package com.vimeo.networking2

import com.vimeo.networking2.findModel
import io.github.classgraph.ClassGraph
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.*

class DataClassModelsTest {

    private val models = ClassGraph()
        .addClassLoader(Thread.currentThread().contextClassLoader)
        .enableAllInfo()
        .acceptPackages(PACKAGE)
        .scan()
        .getClassesImplementing(SERIALIZABLE)
        .loadClasses()
        .map { it.kotlin }

    @Test
    fun `all classes are data classes`() {
        models.forEach { clazz -> clazz.assertIsDataClass() }
    }

    @Test
    fun `all classes implement Serializable and contain a companion object`() {
        models.forEach { clazz ->
            val serializable = clazz.superclasses.firstOrNull { it.simpleName == "Serializable" }
            assertThat(serializable).isNotNull
            assertThat(clazz.companionObject).isNotNull
            assertThat(clazz.companionObject?.declaredMemberProperties?.size).isEqualTo(1)
        }
    }

    @Test
    fun `BasicDataClass is generated as expected`() {
        val model = models.findModel("BasicDataClass")

        model?.assertIsDataClass()

        model?.primaryConstructor?.parameters?.forEach { param ->
            assertThat(param.isOptional).isFalse()
        }
    }

    @Test
    fun `AnnotatedDataClass is generated as expected`() {
        val model = models.findModel("AnnotatedDataClass")

        model?.assertIsDataClass()

        assertThat(model?.typeParameters).isEmpty()

        assertThat(model?.annotations).isNotEmpty

        model?.primaryConstructor?.parameters?.forEach { param ->
            assertThat(param.annotations).isNotEmpty
        }
    }

    @Test
    fun `DefaultValuesDataClass is generated as expected`() {
        val model = models.findModel("DefaultValuesDataClass")

        model?.assertIsDataClass()

        assertThat(model?.typeParameters).isEmpty()

        model?.primaryConstructor?.parameters?.forEach { param ->
            assertThat(param.isOptional).isTrue()
        }
    }

    @Test
    fun `ParameterizedDataClass is generated as expected`() {
        val model = models.findModel("ParameterizedDataClass")

        model?.assertIsDataClass()

        assertThat(model?.typeParameters?.size).isEqualTo(3)
    }


    @Test
    fun `SecondaryConstructorDataClass is generated as expected`() {
        val model = models.findModel("SecondaryConstructorDataClass")

        model?.assertIsDataClass()

        assertThat(model?.constructors?.size).isEqualTo(2)
    }

    @Test
    fun `MultipleSupersDataClass is generated as expected`() {
        val model = models.findModel("MultipleSupersDataClass")

        model?.assertIsDataClass()

        // Foo, Bar, Baz, Serializable and Any
        assertThat(model?.supertypes?.size).isEqualTo(5)
    }

    @Test
    fun `ParameterizedSuperDataClass is generated as expected`() {
        val model = models.findModel("ParameterizedSuperDataClass")

        model?.assertIsDataClass()

        // MyFoo, Serializable, Any
        assertThat(model?.supertypes?.size).isEqualTo(3)

        val modelSuper = model?.superclasses?.first()

        assertThat(modelSuper?.typeParameters?.size).isEqualTo(1)
    }

    @Test
    fun `PropertyDataClass is generated as expected`() {
        val model = models.findModel("PropertyDataClass")

        model?.assertIsDataClass()

        // foo, bar, baz
        assertThat(model?.declaredMemberProperties?.size).isEqualTo(3)
    }

    @Test
    fun `PrivateDatClass is generated as expected`() {
        val model = models.findModel("PrivateDataClass")

        model?.assertIsDataClass()

        assertThat(model?.visibility).isEqualTo(KVisibility.PRIVATE)
    }

    @Test
    fun `InternalDataClass is generated as expected`() {
        val model = models.findModel("InternalDataClass")

        model?.assertIsDataClass()

        assertThat(model?.visibility).isEqualTo(KVisibility.INTERNAL)
    }

    @Test
    fun `FunctionDataClass is generated as expected`() {
        val model = models.findModel("FunctionDataClass")

        model?.assertIsDataClass()

        // equals, copy, hashcode, component1, toString, doSomething
        assertThat(model?.declaredMemberFunctions?.size).isEqualTo(6)
    }

    private fun KClass<*>?.assertIsDataClass() {
        assertThat(this).isNotNull

        assertThat(this?.primaryConstructor?.parameters).isNotEmpty

        assertThat(this?.isData).isTrue()
    }

    companion object {
        private const val PACKAGE = "com/vimeo/networking2/data"
        private const val SERIALIZABLE = "java.io.Serializable"
    }
}