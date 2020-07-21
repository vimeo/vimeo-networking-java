package com.vimeo.modelgenerator

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.vimeo.modelgenerator.extensions.*
import com.vimeo.modelgenerator.visitor.ParcelableClassVisitor
import org.gradle.api.GradleException
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.types.Variance

private const val NULLABLE = '?'
private const val STARTING_BRACKET = '<'
private const val ENDING_BRACKET = '>'
private const val COMMA = ','
private const val IN_VARIANCE = "in"
private const val OUT_VARIANCE = "out"
private const val STAR_PROJECTION = "*"
private const val LAMBDA = "->"

/**
 * Creates a list of annotations using [AnnotationSpec] for a kt prefixed object,
 * like [KtProperty] or [KtNamedFunction].
 *
 * @param annotations a list of [KtAnnotationEntry] that can be pulled from most Kt
 *  prefixed objects, like [KtParameter] or [KtClass].
 * @param packageName the current package name of the worked on file.
 */
internal fun createAnnotations(
    annotations: List<KtAnnotationEntry>,
    packageName: String
): List<AnnotationSpec> = annotations.map {
    AnnotationSpec.builder(
        ClassName(
            packageName,
            it.shortName?.asString() ?: throw GradleException("Annotation name cannot be null.")
        )
    )
        .addMembers(it.valueArguments)
        .build()
}

/**
 * Creates a list of [TypeNames][TypeName] to be used as supers for a given class or interface.
 *
 * @param supers a list of [KtSuperTypeListEntry] that correspond to the given classes super classes.
 * @param packageName the current package name of the worked on file.
 */
internal fun createSuperTypes(
    supers: List<KtSuperTypeListEntry>,
    packageName: String
): List<TypeName> = supers
    .map { superType ->
        val type = superType.typeAsUserType ?: throw GradleException("Type cannot be null.")

        // If supertype is parameterized the type arguments would contain the parameters
        // Otherwise if no type arguments just return a regular supertype.
        if (type.typeArguments.isNotEmpty()) {
            ClassName(
                packageName,
                type.referenceExpression?.text.orEmpty()
            ).parameterizedBy(type.typeArguments.map { superTypeParam ->
                ClassName(
                    packageName,
                    superTypeParam.text
                        ?: throw GradleException("$superTypeParam simpleName can't be empty")
                )
            })
        } else {
            ClassName(
                packageName,
                type.referenceExpression?.text
                    ?: throw GradleException("$superType: simpleName can't be empty")
            )
        }
    }

/**
 * Creates a list of [TypeVariableNames][TypeVariableName] that can be used for Typed classes or functions.
 *
 * @param types a list of [KtTypeParameter] that will be used to type a class or function.
 * @param packageName the current package name of the worked on file.
 * @param constraints a list of [KtTypeConstraints][KtTypeConstraint] that the given [TypeVariableName]
 * will need to conform to, defaults to an empty list.
 * @param isInline a [Boolean] to denote if this is for a inline function, if so the [TypeVariableName]
 * will also be reified, defaults to false.
 * @param isParcelable a [Boolean] that denotes if the class using this method needs to be [android.os.Parcelable], defaults to false.
 */
internal fun createTypeVariables(
    types: List<KtTypeParameter>,
    packageName: String,
    constraints: List<KtTypeConstraint> = emptyList(),
    isInline: Boolean = false,
    isParcelable: Boolean = false
): List<TypeVariableName> = types.map { typeParam ->
    TypeVariableName(
        typeParam.name ?: throw GradleException("$typeParam: name can't be null"),
        bounds = constraints.map { typeConstraint ->
            createTypeName(
                typeConstraint.boundTypeReference,
                packageName
            )
        }.toMutableList().apply {
            if (isParcelable) {
                this.add(ParcelableClassVisitor.PARCELABLE)
            }
        },
        variance = when (typeParam.variance) {
            Variance.IN_VARIANCE -> KModifier.IN
            Variance.OUT_VARIANCE -> KModifier.OUT
            else -> null
        }
    )
        .copy(reified = isInline)
}

/**
 * Creates a list of [ParameterSpec] that can be used.
 *
 * @param params a list of [KtParameters][KtParameter] that correspond to a constructor params for a class.
 * @param packageName the current package name of the worked on file.
 */
internal fun createConstructorParams(
    params: List<KtParameter>,
    packageName: String
): List<ParameterSpec> = params
    .map {
        ParameterSpec.builder(
            it.validateName,
            createTypeName(it.typeReference, packageName)
        )
            .addIfConditionMet(it.modifierList?.isOverridden == true) {
                addModifiers(KModifier.OVERRIDE)
            }
            .addIfConditionMet(it.defaultValue != null) {
                defaultValue("%L", it.defaultValue?.text)
            }
            .addKdoc(it.docComment?.getDefaultSection()?.getContent().orEmpty())
            .addAnnotations(
                createAnnotations(
                    it.annotationEntries,
                    packageName
                )
            )
            .addIfConditionMet(it.visibilityModifier != null) {
                addModifiers(it.visibilityModifier!!)
            }
            .build()
    }


/**
 * Creates a [TypeName] that can be used to denote types for constructor params, class properties,
 * and function params and return types.
 *
 * @param type a [KtTypeReference] for the current type.
 * @param packageName the current package name of the worked on file.
 */
internal fun createTypeName(
    type: KtTypeReference?,
    packageName: String
): TypeName {
    if (type == null) {
        throw GradleException("TypeReference cannot be null.")
    }

    // Type is a lambda
    if (type.text.contains(LAMBDA)) {
        val functionType = type.children.find { it is KtFunctionType } as KtFunctionType

        val receiverType = if (functionType.receiverTypeReference != null) {
            createTypeName(
                functionType.receiverTypeReference,
                packageName
            )
        } else {
            null
        }

        return LambdaTypeName.get(
            receiver = receiverType,
            parameters = createConstructorParams(functionType.parameters, packageName),
            returnType = createTypeName(functionType.returnTypeReference, packageName)
        )
    }

    val nonNullName = type.text.removeSuffix(NULLABLE.toString())

    val isOuterMostTypeNullable = type.text.last() == NULLABLE

    val parametersString =
        nonNullName.substringAfter(STARTING_BRACKET).substringBeforeLast(ENDING_BRACKET)

    val outerMostType = nonNullName.substringBefore(STARTING_BRACKET)

    // Regular type with no parameters
    if (!nonNullName.contains(STARTING_BRACKET) && !nonNullName.contains(ENDING_BRACKET)) {
        return ClassName(
            packageName,
            nonNullName
        ).copy(nullable = isOuterMostTypeNullable)
    }

    // Types like that take multiple parameters, like Map<String, String> or Foo<Bar, Baz>.
    if (parametersString.contains(COMMA)) {
        val params =
            parametersString.split(COMMA)
                .map { it.trim() }
                .map {
                    when {
                        it.contains(OUT_VARIANCE) -> {
                            val sanitizedName = it.removePrefix(OUT_VARIANCE).trim()
                            WildcardTypeName.producerOf(
                                ClassName(packageName, sanitizedName)
                                    .copy(nullable = sanitizedName.contains(NULLABLE))
                            )
                        }
                        it.contains(IN_VARIANCE) -> {
                            val sanitizedName = it.removePrefix(IN_VARIANCE).trim()
                            WildcardTypeName.consumerOf(
                                ClassName(packageName, sanitizedName)
                                    .copy(nullable = sanitizedName.contains(NULLABLE))
                            )
                        }
                        it == STAR_PROJECTION -> STAR
                        else -> ClassName(packageName, it)
                            .copy(nullable = it.contains(NULLABLE))

                    }
                }

        return ClassName(packageName, outerMostType)
            .parameterizedBy(params)
            .copy(nullable = isOuterMostTypeNullable)

    } else {
        val parameters =
            parametersString.substringBefore(ENDING_BRACKET).split(STARTING_BRACKET)
                .map { ClassName(packageName, it) }
                .toMutableList()

        return ClassName(packageName, outerMostType)
            .parameterizedBy(createNestedParameterizedTypes(parameters))
            .copy(nullable = isOuterMostTypeNullable)
    }
}

/**
 * A recursive method that goes through the given list until completion and
 * creates a parameterized [TypeName] from it.
 *
 *  @param classes a list of [ClassNames][ClassName] that will be parameterized, specifically in order
 *  in which they should be parameterized.  A list of (List, Foo, Bar) will output `List<Foo<Bar>>`
 */
private fun createNestedParameterizedTypes(classes: MutableList<ClassName>): TypeName =
    if (classes.size == 1) {
        classes.first()
    } else {
        val firstClass = classes.first()

        classes.remove(firstClass)

        firstClass.parameterizedBy(createNestedParameterizedTypes(classes))
    }
