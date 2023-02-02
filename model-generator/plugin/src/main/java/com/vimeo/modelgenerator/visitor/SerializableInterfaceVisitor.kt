package com.vimeo.modelgenerator.visitor

import com.squareup.kotlinpoet.TypeSpec
import org.jetbrains.kotlin.psi.KtClass
import java.io.Serializable

/**
 * No-op visitor that doesn't modify Interfaces when [com.vimeo.modelgenerator.ModelType.SERIALIZABLE]
 * is selected.
 */
class SerializableInterfaceVisitor: ModifyVisitor {

    override fun modify(
        builder: TypeSpec.Builder,
        clazz: KtClass,
        packageName: String
    ): TypeSpec.Builder = builder.addSuperinterface(Serializable::class)
}
