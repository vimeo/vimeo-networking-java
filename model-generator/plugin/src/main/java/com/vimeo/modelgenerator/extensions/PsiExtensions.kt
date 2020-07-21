package com.vimeo.modelgenerator.extensions

import com.squareup.kotlinpoet.KModifier
import org.gradle.api.GradleException
import org.jetbrains.kotlin.com.intellij.psi.PsiNamedElement
import org.jetbrains.kotlin.kdoc.parser.KDocKnownTag
import org.jetbrains.kotlin.kdoc.psi.api.KDoc
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtModifierList
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.ValueArgument
import org.jetbrains.kotlin.psi.psiUtil.visibilityModifierType
import java.util.*


/**
 * Validates the name of the [PsiNamedElement] exists, otherwise throws an [GradleException].
 */
internal val PsiNamedElement.validateName: String
    get() = this.name ?: throw GradleException("Name empty for: $this")


/**
 * Returns true if the given [KtModifierList] is overridden from a parent class, otherwise returns false.
 */
internal val KtModifierList.isOverridden: Boolean
    get() = text?.contains("override") == true

/**
 * Returns true if the given [KtModifierList] is from an inline function, otherwise returns false.
 */
internal val KtModifierList.isInline: Boolean
    get() = text?.contains("inline") == true


/**
 * Returns a list of strings that can be used as params for Annotations and enums.
 */
internal val List<ValueArgument>.toParams: List<String>
    get() = map {

        /**
         * If the parameter of the Annotation is named (Json(name = "foo")
         * then the argument name is name, if the Annotation is not using named parameters this will be null.
         */
        val argName = it.getArgumentName()?.asName?.asString()

        /**
         * The value passed to the Annotation, so in Json(name = "foo") it's foo
         * or in @file:JvmName("FooUtils") it would be FooUtils.
         */
        val argExpression = it.getArgumentExpression()?.text.orEmpty()

        if (argName == null) {
            argExpression
        } else {
            "$argName = $argExpression"
        }
    }

/**
 * Parses the given [KDoc] for any KDoc block tags and returns a list of each tag.
 */
internal val KDoc.blockTags: List<String>
    get() = KDocKnownTag.values().flatMap { tag ->
        getDefaultSection().findTagsByName(tag.name.toLowerCase(Locale.getDefault())).map {
            "@${tag.name.toLowerCase(Locale.getDefault())} ${it.getSubjectName().orEmpty()} ${it.getContent()}\n"
        }
    }

/**
 * Returns the visibility modifier (public, private, internal) for the given type.
 */
internal val KtModifierListOwner.visibilityModifier: KModifier?
    get() = when (visibilityModifierType()) {
        KtTokens.PUBLIC_KEYWORD -> KModifier.PUBLIC
        KtTokens.PRIVATE_KEYWORD -> KModifier.PRIVATE
        KtTokens.INTERNAL_KEYWORD -> KModifier.INTERNAL
        KtTokens.PROTECTED_KEYWORD -> KModifier.PROTECTED
        // If the modifier is omitted (defaulting to public) this will be null
        else -> null
    }
