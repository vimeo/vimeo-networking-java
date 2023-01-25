package com.vimeo.modelgenerator

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.vimeo.modelgenerator.extensions.addAnnotations
import com.vimeo.modelgenerator.extensions.addEnumConstants
import com.vimeo.modelgenerator.extensions.addFunctions
import com.vimeo.modelgenerator.extensions.addIfConditionMet
import com.vimeo.modelgenerator.extensions.addImports
import com.vimeo.modelgenerator.extensions.addKdocs
import com.vimeo.modelgenerator.extensions.addMembers
import com.vimeo.modelgenerator.extensions.addProperties
import com.vimeo.modelgenerator.extensions.addSuperclassConstructorParameters
import com.vimeo.modelgenerator.extensions.addTypes
import com.vimeo.modelgenerator.extensions.blockTags
import com.vimeo.modelgenerator.extensions.isInline
import com.vimeo.modelgenerator.extensions.isOverridden
import com.vimeo.modelgenerator.extensions.toParams
import com.vimeo.modelgenerator.extensions.validateName
import com.vimeo.modelgenerator.extensions.visibilityModifier
import com.vimeo.modelgenerator.visitor.ModifyVisitor
import com.vimeo.modelgenerator.visitor.ParcelableClassVisitor
import org.gradle.api.GradleException
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import org.jetbrains.kotlin.util.isOrdinaryClass
import java.io.File

/**
 * A factory that will generates new models from the given [KtFiles][KtFile].
 *
 * @param path the file path that points to where the generated files should be put.
 * @param classVisitor a [ModifyVisitor] that is used to make changes to classes.
 * @param interfaceVisitor a [ModifyVisitor] that is used to make changes to interfaces.
 */
internal class ModelFactory(
    private val path: String,
    private val classVisitor: ModifyVisitor,
    private val interfaceVisitor: ModifyVisitor
) {

    /**
     * Takes the given [KtFile] and uses it to generate a new model.
     *
     * @param ktFile the [KtFile] of the file that will be parsed and used to generate a new model.
     */
    fun buildModel(ktFile: KtFile) {
        val elements =
            ktFile.children.filter { it is KtClass || it is KtNamedFunction || it is KtProperty }

        val imports = ktFile.importDirectives.map { it.importPath.toString() }
            // Class name is key full package is entry, ie: <Date, java.util>, <Serializable, java.io>
            .map { Pair(it.substringAfterLast("."), it.substringBeforeLast(".")) }

        val name = ktFile.name.substringAfterLast("/").substringBefore(".kt")

        val fileAnnotations = ktFile.annotationEntries.map {
            AnnotationSpec.builder(
                ClassName(
                    "",
                    it.shortName?.asString().orEmpty()
                )
            )
                .addMembers(it.valueArguments)
                .build()
        }

        val packageName = ktFile.packageFqName.asString()

        FileSpec.builder(
            packageName = packageName,
            fileName = name
        )
            .addImports(imports)
            .addTypes(
                elements.filterIsInstance<KtClass>().map { buildType(it, packageName) })
            .addFunctions(
                elements.filterIsInstance<KtNamedFunction>().map { buildFunction(it, packageName) }
            )
            .addProperties(
                elements.filterIsInstance<KtProperty>().map { buildProperty(it, packageName) }
            )
            .addIfConditionMet(fileAnnotations.isNotEmpty()) {
                addAnnotations(fileAnnotations)
            }
            .build()
            .writeTo(File(path))
    }

    private fun buildType(
        clazz: KtClass,
        packageName: String
    ): TypeSpec = when {
        clazz.isInterface() -> buildInterface(clazz, packageName)
        clazz.isEnum() -> buildEnum(clazz, packageName)
        clazz.isAnnotation() -> buildAnnotation(clazz, packageName)
        clazz.isData() || clazz.isOrdinaryClass -> buildClass(clazz, packageName)
        else -> throw GradleException("$clazz: This class is not supported.")
    }

    private fun buildFunction(
        function: KtNamedFunction,
        packageName: String
    ): FunSpec = FunSpec.builder(function.validateName)
        .addIfConditionMet(function.modifierList?.isInline == true) {
            addModifiers(KModifier.INLINE)
        }
        .addIfConditionMet(function.isExtensionDeclaration()) {
            receiver(
                createTypeName(
                    function.receiverTypeReference,
                    packageName
                )
            )
            returns(
                createTypeName(
                    function.typeReference,
                    packageName
                )
            )
        }
        .addIfConditionMet(function.typeParameters.isNotEmpty()) {
            addTypeVariables(
                createTypeVariables(
                    function.typeParameters,
                    packageName,
                    function.typeConstraints,
                    isInline = function.modifierList?.isInline == true
                )
            )
        }
        .addIfConditionMet(function.typeReference != null) {
            val content = function.bodyExpression
            if (content == null) {
                addModifiers(KModifier.ABSTRACT)
                returns(createTypeName(function.typeReference, packageName))
            } else {
                addStatement("return ${content.text.orEmpty()}")
            }
        }
        .addIfConditionMet(function.typeReference == null) {
            val content = function.bodyBlockExpression?.statements
            if (!content.isNullOrEmpty()) {
                val statements = content.map { it.text }

                statements.forEach {
                    addStatement(it)
                }
            } else {
                // Function has no content and is declared in an interface
                addModifiers(KModifier.ABSTRACT)
            }
        }
        .addParameters(function.valueParameters.map { param ->
            ParameterSpec.builder(
                param.validateName,
                createTypeName(
                    param.typeReference,
                    packageName
                )
            )
                .build()
        })
        .addKdoc(function.docComment?.getDefaultSection()?.getContent().orEmpty())
        .addKdocs(function.docComment?.blockTags.orEmpty())
        .addAnnotations(
            createAnnotations(
                function.annotationEntries,
                packageName
            )
        )
        .addIfConditionMet(function.visibilityModifier != null) {
            addModifiers(function.visibilityModifier!!)
        }
        .addIfConditionMet(function.modifierList?.isOverridden == true) {
            addModifiers(KModifier.OVERRIDE)
        }
        .build()

    private fun buildProperty(
        property: KtProperty,
        packageName: String
    ): PropertySpec = PropertySpec.builder(
        property.validateName,
        createTypeName(
            property.typeReference,
            packageName
        )
    )
        .addIfConditionMet(property.modifierList?.isOverridden == true) {
            addModifiers(KModifier.OVERRIDE)
        }
        .addIfConditionMet(property.initializer?.text != null) {
            initializer(property.initializer?.text!!)
        }
        .addIfConditionMet(property.isExtensionDeclaration()) {
            receiver(
                createTypeName(
                    property.receiverTypeReference,
                    packageName
                )
            )
        }
        .addAnnotations(
            createAnnotations(
                property.annotationEntries, packageName
            )
        )
        .addIfConditionMet(property.getter != null) {
            getter(
                FunSpec.getterBuilder()
                    .addIfConditionMet
                        (
                        property.getter?.modifierList?.isInline == true ||
                                property.modifierList?.isInline == true
                    ) {
                        addModifiers(KModifier.INLINE)
                    }
                    .addStatement("return ${property.getter?.bodyExpression?.text.orEmpty()}")
                    .build()
            )
        }
        .addKdoc(property.docComment?.getDefaultSection()?.getContent().orEmpty())
        .addKdocs(property.docComment?.blockTags.orEmpty())
        .addIfConditionMet(property.visibilityModifier != null) {
            addModifiers(property.visibilityModifier!!)
        }
        .build()

    private fun buildAnnotation(
        clazz: KtClass,
        packageName: String
    ): TypeSpec {
        val properties = clazz.primaryConstructorParameters
            .map {
                PropertySpec.builder(
                    it.validateName,
                    createTypeName(
                        it.typeReference,
                        packageName
                    )
                )
                    .initializer(it.validateName)
                    .addKdoc(it.docComment?.getDefaultSection()?.getContent().orEmpty())
                    .addIfConditionMet(it.modifierList?.isOverridden == true) {
                        addModifiers(KModifier.OVERRIDE)
                    }
                    .build()
            }

        return TypeSpec.annotationBuilder(clazz.validateName)
            .addKdoc(clazz.docComment?.getDefaultSection()?.getContent().orEmpty())
            .addKdocs(clazz.docComment?.blockTags.orEmpty())
            .addAnnotations(
                createAnnotations(
                    clazz.annotationEntries,
                    packageName
                )
            )
            .primaryConstructor(
                FunSpec.constructorBuilder().addParameters(
                    createConstructorParams(
                        clazz.primaryConstructorParameters,
                        packageName
                    )
                ).build()
            )
            .addProperties(properties)
            .addIfConditionMet(clazz.visibilityModifier != null) {
                addModifiers(clazz.visibilityModifier!!)
            }
            .build()
    }


    private fun buildEnum(
        clazz: KtClass,
        packageName: String
    ): TypeSpec {
        val properties = clazz.primaryConstructorParameters
            .map {
                PropertySpec.builder(
                    it.validateName,
                    createTypeName(
                        it.typeReference,
                        packageName
                    )
                )
                    .initializer(it.validateName)
                    .addKdoc(it.docComment?.getDefaultSection()?.getContent().orEmpty())
                    .addIfConditionMet(it.modifierList?.isOverridden == true) {
                        addModifiers(KModifier.OVERRIDE)
                    }
                    .build()
            }

        val enums = clazz.body?.enumEntries?.map { entry ->
            val superTypeCall =
                entry.initializerList?.children?.find { it is KtSuperTypeCallEntry } as KtSuperTypeCallEntry?

            val args = superTypeCall?.valueArguments?.toParams?.map { Pair("%L", it) }.orEmpty()

            Pair(
                entry.validateName, TypeSpec.anonymousClassBuilder()
                    .addSuperclassConstructorParameters(args)
                    .addKdoc(entry.docComment?.getDefaultSection()?.getContent().orEmpty())
                    .addAnnotations(
                        createAnnotations(
                            entry.annotationEntries,
                            packageName
                        )
                    )
                    .build()
            )
        }.orEmpty()

        return TypeSpec.enumBuilder(clazz.validateName)
            .addAnnotations(
                createAnnotations(
                    clazz.annotationEntries,
                    packageName
                )
            )
            .primaryConstructor(
                FunSpec.constructorBuilder().addParameters(
                    createConstructorParams(
                        clazz.primaryConstructorParameters,
                        packageName
                    )
                ).build()
            )
            .addEnumConstants(enums)
            .addProperties(properties)
            .addKdoc(clazz.docComment?.getDefaultSection()?.getContent().orEmpty())
            .addKdocs(clazz.docComment?.blockTags.orEmpty())
            .addIfConditionMet(clazz.visibilityModifier != null) {
                addModifiers(clazz.visibilityModifier!!)
            }
            .addSuperinterfaces(
                createSuperTypes(
                    clazz.superTypeListEntries,
                    packageName
                )
            )
            .addIfConditionMet(classVisitor is ParcelableClassVisitor) {
                classVisitor.modify(this, clazz, packageName)
            }
            .build()
    }

    private fun buildInterface(
        clazz: KtClass,
        packageName: String
    ): TypeSpec = TypeSpec.interfaceBuilder(clazz.validateName)
        .addIfConditionMet(clazz.typeParameters.isNotEmpty()) {
            addTypeVariables(
                createTypeVariables(
                    clazz.typeParameters,
                    packageName = packageName
                )
            )
        }
        .addKdoc(clazz.docComment?.getDefaultSection()?.getContent().orEmpty())
        .addKdocs(clazz.docComment?.blockTags.orEmpty())
        .addIfConditionMet(clazz.superTypeListEntries.isNotEmpty()) {
            addSuperinterfaces(
                createSuperTypes(
                    clazz.superTypeListEntries,
                    packageName
                )
            )
        }
        .addIfConditionMet(clazz.visibilityModifier != null) {
            addModifiers(clazz.visibilityModifier!!)
        }
        .addProperties(clazz.getProperties().map { buildProperty(it, packageName) })
        .addAnnotations(
            createAnnotations(
                clazz.annotationEntries,
                packageName
            )
        )
        .addIfConditionMet(clazz.body?.functions?.isNotEmpty() == true) {
            addFunctions(clazz.body?.functions!!.map { buildFunction(it, packageName) })
        }
        .run { interfaceVisitor.modify(this, clazz, packageName) }
        .build()

    private fun buildClass(
        clazz: KtClass,
        packageName: String
    ): TypeSpec {
        val secondaryConstructors = clazz.secondaryConstructors.map { constructor ->
            FunSpec.constructorBuilder()
                .addKdoc(constructor.docComment?.getDefaultSection()?.getContent().orEmpty())
                .addKdocs(constructor.docComment?.blockTags.orEmpty())
                .callThisConstructor(constructor.getDelegationCall().valueArguments.toParams.map {
                    CodeBlock.Builder().add(it).build()
                })
                .addParameters(constructor.valueParameters.map { param ->
                    ParameterSpec.builder(
                        param.validateName,
                        createTypeName(
                            param.typeReference,
                            packageName
                        )
                    )
                        .build()
                })
                .build()
        }

        val properties = clazz.primaryConstructorParameters
            .map {
                PropertySpec.builder(
                    it.validateName,
                    createTypeName(
                        it.typeReference,
                        packageName
                    )
                )
                    .initializer(it.validateName)
                    .addKdoc(it.docComment?.getDefaultSection()?.getContent().orEmpty())
                    .addIfConditionMet(it.modifierList?.isOverridden == true) {
                        addModifiers(KModifier.OVERRIDE)
                    }
                    .build()
            }


        val builder = TypeSpec.classBuilder(clazz.validateName)
            .addIfConditionMet(clazz.isData()) {
                addModifiers(KModifier.DATA)
            }
            .addAnnotations(
                createAnnotations(
                    clazz.annotationEntries,
                    packageName
                )
            )
            .addKdoc(clazz.docComment?.getDefaultSection()?.getContent().orEmpty())
            .addKdocs(clazz.docComment?.blockTags.orEmpty())
            .primaryConstructor(
                FunSpec.constructorBuilder().addParameters(
                    createConstructorParams(
                        clazz.primaryConstructorParameters,
                        packageName
                    )
                ).build()
            )
            .addFunctions(secondaryConstructors)
            .addIfConditionMet(clazz.superTypeListEntries.isNotEmpty()) {
                addSuperinterfaces(
                    createSuperTypes(
                        clazz.superTypeListEntries,
                        packageName
                    )
                )
            }
            .addIfConditionMet(clazz.visibilityModifier != null) {
                addModifiers(clazz.visibilityModifier!!)
            }
            .addProperties(properties)
            .addIfConditionMet(clazz.body?.functions?.isNotEmpty() == true) {
                addFunctions(clazz.body?.functions!!.map { buildFunction(it, packageName) })
            }
            .addProperties(clazz.getProperties().map { buildProperty(it, packageName) })


        return classVisitor.modify(builder, clazz, packageName).build()
    }
}
