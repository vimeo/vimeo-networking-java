package com.vimeo.networking2

import com.vimeo.networking2.findModel
import io.github.classgraph.ClassGraph
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberExtensionProperties
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.extensionReceiverParameter
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.typeOf

@OptIn(ExperimentalStdlibApi::class)
class PropertiesTest {
    private val models = ClassGraph()
        .addClassLoader(Thread.currentThread().contextClassLoader)
        .enableAllInfo()
        .acceptPackages(PACKAGE)
        .scan()
        .allClasses
        .loadClasses()
        .map { it.kotlin }

    @Test
    fun `all models contain properties`() {
        models.forEach { clazz -> clazz.assertContainsProperties() }
    }

    @Test
    fun `AnnotatedPropertyContainer is generated as expected`() {
        val model = models.findModel("AnnotatedPropertyContainer")

        model?.assertContainsProperties()

        val property = model?.declaredMemberProperties?.firstOrNull { it.name == "foo" }

        assertThat(property?.returnType).isEqualTo(typeOf<String>())
        assertThat(property?.hasAnnotation<Deprecated>()).isTrue()
    }

    @Test
    fun `BasicPropertyContainer is generated as expected`() {
        val model = models.findModel("BasicPropertyContainer")

        model?.assertContainsProperties()

        val property = model?.declaredMemberProperties?.firstOrNull { it.name == "foo" }

        assertThat(property?.returnType).isEqualTo(typeOf<String>())
    }

    @Test
    fun `ExtensionPropertyContainer is generated as expected`() {
        val model = models.findModel("ExtensionPropertyContainer")

        model?.assertContainsProperties()

        val property = model?.declaredMemberExtensionProperties?.firstOrNull { it.name == "doSomething" }

        assertThat(property?.returnType).isEqualTo(typeOf<String>())
        assertThat(property?.extensionReceiverParameter?.type).isEqualTo(typeOf<String>())
    }

    @Test
    fun `InternalPropertyContainer is generated as expected`() {
        val model = models.findModel("InternalPropertyContainer")

        model?.assertContainsProperties()

        val property = model?.declaredMemberProperties?.firstOrNull { it.name == "foo" }

        assertThat(property?.returnType).isEqualTo(typeOf<String>())
        assertThat(property?.visibility).isEqualTo(KVisibility.INTERNAL)
    }

    @Test
    fun `PrivatePropertyContainer is generated as expected`() {
        val model = models.findModel("PrivatePropertyContainer")

        model?.assertContainsProperties()

        val property = model?.declaredMemberProperties?.firstOrNull { it.name == "foo" }

        assertThat(property?.returnType).isEqualTo(typeOf<String>())
        assertThat(property?.visibility).isEqualTo(KVisibility.PRIVATE)
    }

    @Test
    fun `InlinePropertyContainer is generated as expected`() {
        val model = models.findModel("InlinePropertyContainer")

        model?.assertContainsProperties()

        val property = model?.declaredMemberProperties?.firstOrNull { it.name == "foo" }

        assertThat(property?.returnType).isEqualTo(typeOf<String>())
        assertThat(property?.getter?.isInline).isTrue()
    }

    private fun KClass<*>.assertContainsProperties() {
        assertThat(this).isNotNull
        assertThat(this.declaredMemberProperties.isNotEmpty()
                || this.declaredMemberExtensionProperties.isNotEmpty()).isTrue()
    }

    companion object {
        private const val PACKAGE = "com/vimeo/networking2/properties"
    }
}