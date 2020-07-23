package com.vimeo.modelgenerator.visitor

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeSpec
import com.vimeo.modelgenerator.createTypeVariables
import com.vimeo.modelgenerator.extensions.addIfConditionMet
import kotlinx.android.parcel.Parcelize
import org.jetbrains.kotlin.psi.KtClass

/**
 * [ModifyVisitor] for [android.os.Parcelable] code additions.
 *
 * This adds the [Parcelize] annotation and [android.os.Parcelable] as a super to classes.
 */
class ParcelableClassVisitor : ModifyVisitor {

    override fun modify(
        builder: TypeSpec.Builder,
        clazz: KtClass,
        packageName: String
    ): TypeSpec.Builder = builder.addAnnotation(Parcelize::class)
        .addSuperinterface(PARCELABLE)
        .addIfConditionMet(clazz.typeParameters.isNotEmpty()) {
            addTypeVariables(
                createTypeVariables(
                    clazz.typeParameters,
                    packageName = packageName,
                    isParcelable = true
                )
            )
        }

    companion object {
        /**
         * A [ClassName] for [android.os.Parcelable].
         */
        val PARCELABLE = ClassName("android.os", "Parcelable")
    }
}
