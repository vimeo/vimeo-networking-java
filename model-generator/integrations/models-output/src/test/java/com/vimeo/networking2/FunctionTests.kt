package com.vimeo.networking2

import com.vimeo.networking2.findModel
import io.github.classgraph.ClassGraph
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.extensionReceiverParameter
import kotlin.reflect.full.functions
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.valueParameters
import kotlin.reflect.typeOf

@OptIn(ExperimentalStdlibApi::class)
class FunctionTests {

    private val classes = ClassGraph()
        .addClassLoader(Thread.currentThread().contextClassLoader)
        .enableAllInfo()
        .acceptPackages(PACKAGE)
        .scan()
        .allClasses
        .loadClasses()
        .map { it.kotlin }


    @Test
    fun `all classes contain functions`() {
        classes.forEach { clazz -> clazz.assertContainsFunction() }
    }

    @Test
    fun `AnnotatedFunctionContainer is generated as expected`() {
        val model = classes.findModel("AnnotatedFunctionContainer")

        model?.assertContainsFunction()

        val function = model?.functions?.firstOrNull { it.name == "doSomething" }

        assertThat(function?.returnType).isEqualTo(typeOf<Unit>())
        assertThat(function?.hasAnnotation<Deprecated>()).isTrue()
    }

    @Test
    fun `BasicFunctionContainer is generated as expected`() {
        val model = classes.findModel("BasicFunctionContainer")

        model?.assertContainsFunction()

        val function = model?.functions?.firstOrNull { it.name == "basicFunction" }

        assertThat(function?.returnType).isEqualTo(typeOf<String>())
        assertThat(function?.visibility).isEqualTo(KVisibility.PUBLIC)
    }

    @Test
    fun `ExtensionFunctionContainer is generated as expected`() {
        val model = classes.findModel("ExtensionFunctionContainer")

        model?.assertContainsFunction()

        val function = model?.functions?.firstOrNull { it.name == "doSomething" }

        assertThat(function?.returnType).isEqualTo(typeOf<String>())
        assertThat(function?.extensionReceiverParameter?.type).isEqualTo(typeOf<String>())
    }

    @Test
    fun `GenericFunctionContainer is generated as expected`() {
        val model = classes.findModel("GenericFunctionContainer")

        model?.assertContainsFunction()

        val function = model?.functions?.firstOrNull { it.name == "doSomething" }

        assertThat(function?.returnType).isEqualTo(typeOf<String>())
        assertThat(function?.typeParameters?.size).isEqualTo(1)
        assertThat(function?.valueParameters?.size).isEqualTo(1)
    }

    @Test
    fun `InlineFunctionContainer is generated as expected`() {
        val model = classes.findModel("InlineFunctionContainer")

        model?.assertContainsFunction()

        val function = model?.functions?.firstOrNull { it.name == "inlineFunction" }

        val param = function?.valueParameters?.first()

        assertThat(param?.type).isEqualTo(typeOf<() -> Unit>())
        assertThat(function?.isInline).isTrue()
    }

    @Test
    fun `InternalFunctionContainer is generated as expected`() {
        val model = classes.findModel("InternalFunctionContainer")

        model?.assertContainsFunction()

        val function = model?.functions?.firstOrNull { it.name == "internalFunction" }

        assertThat(function?.returnType).isEqualTo(typeOf<String>())
        assertThat(function?.visibility).isEqualTo(KVisibility.INTERNAL)
    }

    @Test
    fun `PrivateFunctionContainer is generated as expected`() {
        val model = classes.findModel("PrivateFunctionContainer")

        model?.assertContainsFunction()

        val function = model?.functions?.firstOrNull { it.name == "privateFunction" }

        assertThat(function?.returnType).isEqualTo(typeOf<String>())
        assertThat(function?.visibility).isEqualTo(KVisibility.PRIVATE)
    }

    @Test
    fun `ReifiedFunctionContainer is generated as expected`() {
        val model = classes.findModel("ReifiedFunctionContainer")

        model?.assertContainsFunction()

        val function = model?.functions?.firstOrNull { it.name == "reifiedFunction" }

        assertThat(function?.typeParameters?.size).isEqualTo(1)
        assertThat(function?.returnType).isEqualTo(typeOf<String>())
        assertThat(function?.isInline).isTrue()

        val typeParam = function?.typeParameters?.first()

        assertThat(typeParam?.isReified).isTrue()
    }

    private fun KClass<*>.assertContainsFunction() {
        assertThat(this).isNotNull
        assertThat(this.functions).isNotEmpty
    }


    companion object {
        private const val PACKAGE = "com/vimeo/networking2/functions"
    }
}