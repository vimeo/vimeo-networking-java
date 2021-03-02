package com.vimeo.modelgenerator.visitor

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.vimeo.modelgenerator.createTypeVariables
import com.vimeo.modelgenerator.extensions.addIfConditionMet
import org.jetbrains.kotlin.psi.KtClass
import java.io.Serializable

/**
 * [ModifyVisitor] for [Serializable] code additions.
 *
 * This adds [Serializable] as a class super as well as adding a companion object with a serialVersionUID.
 */
class SerializableClassVisitor : ModifyVisitor {

    override fun modify(
        builder: TypeSpec.Builder,
        clazz: KtClass,
        packageName: String
    ): TypeSpec.Builder =
        builder.addSuperinterface(Serializable::class).addType(
            TypeSpec.companionObjectBuilder().addProperty(
                PropertySpec.builder(UID, Long::class, KModifier.CONST, KModifier.PRIVATE)
                    .initializer(calculateUid(clazz).toString())
                    .build()
            )
                .build()
        )
            .addIfConditionMet(clazz.typeParameters.isNotEmpty()) {
                addTypeVariables(
                    createTypeVariables(
                        clazz.typeParameters,
                        packageName = packageName
                    )
                )
            }

    private fun calculateUid(clazz: KtClass): Long {
        val constructorParamsHash = clazz.primaryConstructorParameters.map { it.hashCode() }.sum()
        val propertyHash = clazz.getProperties().map { it.hashCode() }.sum()

        return (constructorParamsHash + propertyHash).toLong()
    }

    companion object {
        private const val UID = "serialVersionUID"
    }
}

