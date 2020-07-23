package com.vimeo.networking2

import com.vimeo.networking2.findModel
import io.github.classgraph.ClassGraph
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.functions
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.superclasses

class InterfaceModelsTest {

    private val models = ClassGraph()
        .addClassLoader(Thread.currentThread().contextClassLoader)
        .enableAllInfo()
        .acceptPackages(PACKAGE)
        .scan()
        .allInterfaces
        .loadClasses()
        .map { it.kotlin }

    @Test
    fun `all models are interfaces`() {
        models.forEach { clazz -> assertThat(clazz.isAbstract).isTrue() }
    }

    @Test
    fun `AnnotatedInterface is generated as expected`() {
        val model = models.findModel("AnnotatedInterface")

        model?.assertIsInterface()

        assertThat(model?.annotations?.size).isEqualTo(1)
        assertThat(model?.declaredMemberProperties?.size).isEqualTo(0)
    }

    @Test
    fun `BasicInterface is generated as expected`() {
        val model = models.findModel("BasicInterface")

        model?.assertIsInterface()

        assertThat(model?.declaredMemberProperties?.size).isEqualTo(1)
    }

    @Test
    fun `DefaultMethodInterface is generated as expected`() {
        val  model = models.findModel("DefaultMethodInterface")

        model?.assertIsInterface()

        model?.functions?.forEach {function ->
            assertThat(!function.isAbstract).isTrue()
        }
    }

    @Test
    fun `FunctionInterface is generated as expected`() {
        val model = models.findModel("FunctionInterface")

        model?.assertIsInterface()

        // equals, hashcode, toString, doSomething
        assertThat(model?.functions?.size).isEqualTo(4)

        val function = model?.functions?.firstOrNull { it.name == "doSomething" }

        assertThat(function?.isAbstract).isTrue()
    }

    @Test
    fun `InternalInterface is generated as expected`() {
        val model = models.findModel("InternalInterface")

        model?.assertIsInterface()

        assertThat(model?.visibility).isEqualTo(KVisibility.INTERNAL)
    }

    @Test
    fun `MultipleSupersInterface is generated as expected`() {
        val model = models.findModel("MultipleSupersInterface")

        model?.assertIsInterface()

        // Foo, Bar, Baz, and Any
        assertThat(model?.supertypes?.size).isEqualTo(4)
    }

    @Test
    fun `ParameterizedInterface is generated as expected`() {
        val model = models.findModel("ParameterizedInterface")

        model?.assertIsInterface()

        assertThat(model?.typeParameters?.size).isEqualTo(3)
    }

    @Test
    fun `ParameterizedSuperInterface is generated as expected`() {
        val  model = models.findModel("ParameterizedSuperInterface")

        model?.assertIsInterface()

        // MyFoo, Any
        assertThat(model?.supertypes?.size).isEqualTo(2)

        val modelSuper = model?.superclasses?.first()

        assertThat(modelSuper?.typeParameters?.size).isEqualTo(1)
    }

    @Test
    fun `PrivateInterface is generated as expected`() {
        val model = models.findModel("PrivateInterface")

        model?.assertIsInterface()

        assertThat(model?.visibility).isEqualTo(KVisibility.PRIVATE)
    }

    private fun KClass<*>?.assertIsInterface() {
        assertThat(this).isNotNull
        assertThat(this?.primaryConstructor).isNull()
        assertThat(this?.isAbstract).isTrue()
    }

    companion object {
        private const val PACKAGE = "com/vimeo/networking2/interfaces"
    }
}