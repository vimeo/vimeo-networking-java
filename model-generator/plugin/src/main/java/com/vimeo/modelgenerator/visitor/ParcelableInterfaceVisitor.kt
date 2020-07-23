package com.vimeo.modelgenerator.visitor

import com.squareup.kotlinpoet.TypeSpec
import org.jetbrains.kotlin.psi.KtClass


/**
 * [ModifyVisitor] for [android.os.Parcelable] code additions.
 *
 * This adds [android.os.Parcelable] as a super to interfaces.
 */
class ParcelableInterfaceVisitor : ModifyVisitor {

    override fun modify(
        builder: TypeSpec.Builder,
        clazz: KtClass,
        packageName: String
    ): TypeSpec.Builder = builder.addSuperinterface(ParcelableClassVisitor.PARCELABLE)
}