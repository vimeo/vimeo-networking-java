package com.vimeo.networking2

import com.vimeo.networking2.findModel
import io.github.classgraph.ClassGraph
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.superclasses

class EnumModelsTest {

    private val models = ClassGraph()
        .addClassLoader(Thread.currentThread().contextClassLoader)
        .enableAllInfo()
        .acceptPackages(PACKAGE)
        .scan()
        .allEnums
        .loadClasses()
        .map { it.kotlin }


    @Test
    fun `all classes are enums`() {
        models.forEach { clazz -> clazz.assertIsEnum() }
    }

    @Test
    fun `AnnotatedEnum is generated as expected`() {
        val model = models.findModel("AnnotatedEnum")

        model?.assertIsEnum()

        assertThat(model?.annotations?.size).isEqualTo(1)

        val foo = (model as KClass<out Enum<*>>).enumValues().firstOrNull { it.name == "FOO" }


        assertThat(foo!!::class.annotations.size).isEqualTo(1)
    }

    @Test
    fun `BasicEnum is generated as expected`() {
        val model = models.findModel("BasicEnum")

        model?.assertIsEnum()
    }

    @Test
    fun `DefaultValueEnum is generated as expected`() {
        val model = models.findModel("DefaultValueEnum")

        model?.assertIsEnum()

        assertThat(model?.primaryConstructor?.parameters?.size).isEqualTo(1)

        model?.primaryConstructor?.parameters?.forEach { param ->
            assertThat(param.isOptional).isTrue()
        }
    }

    @Test
    fun `InternalEnum is generated as expected`() {
        val model = models.findModel("InternalEnum")

        model?.assertIsEnum()

        assertThat(model?.visibility).isEqualTo(KVisibility.INTERNAL)
    }

    @Test
    fun `ParameterizedSuperEnum is generated as expected`() {
        val model = models.findModel("ParameterizedSuperEnum")

        model?.assertIsEnum()

        // Container, Any
        assertThat(model?.supertypes?.size).isEqualTo(2)

        val modelSuper = model?.superclasses?.first()

        assertThat(modelSuper?.typeParameters?.size).isEqualTo(1)
    }

    @Test
    fun `PrivateEnum is generated as expected`() {
        val model = models.findModel("PrivateEnum")

        model?.assertIsEnum()

        assertThat(model?.visibility).isEqualTo(KVisibility.PRIVATE)
    }

    @Test
    fun `SuperEnum is generated as expected`() {
        val model = models.findModel("SuperEnum")

        model?.assertIsEnum()

        // FooContainer, Foo, Bar, and Any
        assertThat(model?.supertypes?.size).isEqualTo(4)
    }

    @Test
    fun `ValueContainingEnum is generated as expected`() {
        val model = models.findModel("ValueContainingEnum")

        assertThat(model?.primaryConstructor?.parameters?.size).isEqualTo(1)

        model?.assertIsEnum()

        model?.primaryConstructor?.parameters?.forEach { param ->
            assertThat(param.isOptional).isFalse()
        }
    }

    private fun KClass<out Enum<*>>.enumValues(): Array<out Enum<*>> = this.java.enumConstants

    private fun KClass<*>.assertIsEnum() {
        assertThat(this).isNotNull
        assertThat(this.isSubclassOf(Enum::class)).isTrue()
        assertThat((this as KClass<out Enum<*>>).enumValues().size).isEqualTo(2)
    }

    companion object {
        private const val PACKAGE = "com/vimeo/networking2/enums"
    }
}