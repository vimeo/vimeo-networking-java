package com.vimeo.modelgenerator.visitor

import com.squareup.kotlinpoet.TypeSpec
import org.jetbrains.kotlin.psi.KtClass

/**
 * A base interface for the Visitor pattern for modifying a [TypeSpec.Builder].
 *
 * Classes that implement this can use it to add specific behavior to the [TypeSpec.Builder].
 */
interface ModifyVisitor {

    /**
     * A function to make changes to a given [TypeSpec.Builder].
     *
     * @param builder a [TypeSpec.Builder] that can be modified and returned.
     * @param clazz a [KtClass] that can be used to pull extra information for the [TypeSpec.Builder].
     * @param packageName the package of the given [KtClass],
     */
    fun modify(builder: TypeSpec.Builder, clazz: KtClass, packageName: String): TypeSpec.Builder
}