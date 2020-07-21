package com.vimeo.networking2

import com.vimeo.networking2.findModel
import io.github.classgraph.ClassGraph
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor

class AnnotationModelTest {
    private val models = ClassGraph()
        .addClassLoader(Thread.currentThread().contextClassLoader)
        .enableAllInfo()
        .acceptPackages(PACKAGE)
        .scan()
        .allAnnotations
        .loadClasses()
        .map { it.kotlin }

    @Test
    fun `all classes are annotations`() {
        models.forEach { clazz -> clazz.assertIsAnnotation() }
    }

    @Test
    fun `AnnotatedAnnotation is generated as expected`() {
        val model = models.findModel("AnnotatedAnnotation")

        model?.assertIsAnnotation()

        assertThat(model?.annotations?.size).isEqualTo(3)
    }

    @Test
    fun `BasicAnnotation is generated as expected`() {
        val model = models.findModel("BasicAnnotation")

        model?.assertIsAnnotation()
    }

    @Test
    fun `InternalAnnotation is generated as expected`() {
        val model = models.findModel("InternalAnnotation")

        model?.assertIsAnnotation()

        assertThat(model?.visibility).isEqualTo(KVisibility.INTERNAL)
    }

    @Test
    fun `ParametersAnnotation is generated as expected`() {
        val model = models.findModel("ParametersAnnotation")

        model?.assertIsAnnotation()

        assertThat(model?.primaryConstructor?.parameters?.size).isEqualTo(1)
    }

    @Test
    fun `PrivateAnnotation is generated as expected`() {
        val model = models.findModel("PrivateAnnotation")

        model?.assertIsAnnotation()

        assertThat(model?.visibility).isEqualTo(KVisibility.PRIVATE)
    }

    private fun KClass<*>.assertIsAnnotation() {
        assertThat(this).isNotNull
        assertThat(this.isSubclassOf(Annotation::class)).isTrue()
    }

    companion object {
        private const val PACKAGE = "com/vimeo/networking2/annotation"
    }
}