package com.vimeo.modelgenerator.extensions

import com.squareup.kotlinpoet.*
import org.jetbrains.kotlin.psi.ValueArgument

/**
 * Adds a list of Types to the given [FileSpec], good for when building a single
 * file that contains multiple classes or objects.
 *
 * @param types a list of [TypeSpec].
 */
internal fun FileSpec.Builder.addTypes(types: List<TypeSpec>): FileSpec.Builder = apply {
    types.forEach { addType(it) }
}

/**
 * Adds a list of functions to the given [FileSpec], good for when building a single
 * file that contains multiple classes or objects.
 *
 * @param functions a list of [FunSpec].
 */
internal fun FileSpec.Builder.addFunctions(functions: List<FunSpec>): FileSpec.Builder = apply {
    functions.forEach { addFunction(it) }
}

/**
 * Adds a list of properties to the given [FileSpec], good for when building a single
 * file that contains multiple classes or objects.
 *
 * @param properties a list of [FunSpec].
 */
internal fun FileSpec.Builder.addProperties(properties: List<PropertySpec>): FileSpec.Builder =
    apply {
        properties.forEach { addProperty(it) }
    }

/**
 * Add a list of imports to the given [FileSpec].
 *
 * @param imports a list of Imports.
 */
internal fun FileSpec.Builder.addImports(imports: List<Pair<String, String>>): FileSpec.Builder =
    apply {
        imports.forEach { addImport(it.second, it.first) }
    }

/**
 * Adds a list of [AnnotationSpec] to the given [FileSpec].
 *
 * @param annotations a list of [AnnotationSpec].
 */
internal fun FileSpec.Builder.addAnnotations(annotations: List<AnnotationSpec>): FileSpec.Builder =
    apply {
        annotations.forEach { addAnnotation(it) }
    }

/**
 * Conditionally add a block of code to a [Taggable.Builder].
 *
 * This can be applied to [FileSpec.Builder], [PropertySpec.Builder], [ParameterSpec.Builder] or any class that has
 * [Taggable.Builder] as a super.
 *
 * @param condition a condition if met will add the [addition] to the given [Taggable.Builder].
 * @param addition a block of code that can be applied to the [Taggable.Builder] if the [condition] is met.
 */
internal fun <T : Taggable.Builder<T>> T.addIfConditionMet(
    condition: Boolean,
    addition: T.() -> Unit
): T = apply {
    if (condition) addition()
}

/**
 * Parses [valArgs] to create parameters for AnnotationSpec.Builders [AnnotationSpec.Builder].
 *
 * @param valArgs a list of [ValueArgument].
 */
internal fun AnnotationSpec.Builder.addMembers(valArgs: List<ValueArgument>): AnnotationSpec.Builder =
    apply { valArgs.toParams.forEach { addMember(it) } }

/**
 * Adds a list of enums to a given [TypeSpec].
 *
 * @param enums takes a list of [Pairs][Pair] with the first type being the enum name, and the second being the class body.
 */
internal fun TypeSpec.Builder.addEnumConstants(enums: List<Pair<String, TypeSpec>>): TypeSpec.Builder =
    apply {
        enums.forEach {
            addEnumConstant(it.first, it.second)
        }
    }

/**
 * Adds all the given params to a superclass constructor.
 *
 * @param params a list of pairs representing params for a superclass constructor.
 */
internal fun TypeSpec.Builder.addSuperclassConstructorParameters(params: List<Pair<String, Any>>): TypeSpec.Builder =
    apply {
        params.forEach {
            addSuperclassConstructorParameter(it.first, it.second)
        }
    }

/**
 * Adds a list of KDocs to the [TypeSpec.Builder].
 *
 * @param docs a list of KDocs
 */
internal fun TypeSpec.Builder.addKdocs(docs: List<String>): TypeSpec.Builder =
    apply { docs.forEach { addKdoc(it) } }

/**
 * Adds a list of KDocs to the [FunSpec.Builder].
 *
 * @param docs a list of KDocs
 */
internal fun FunSpec.Builder.addKdocs(docs: List<String>): FunSpec.Builder =
    apply { docs.forEach { addKdoc(it) } }

/**
 * Adds a list of KDocs to the [PropertySpec.Builder].
 *
 * @param docs a list of KDocs
 */
internal fun PropertySpec.Builder.addKdocs(docs: List<String>): PropertySpec.Builder =
    apply { docs.forEach { addKdoc(it) } }
