package com.vimeo.networking2.richtext

/**
 * The fallback [RichText] node.
 *
 * Appears on unknown type.
 */
class UnknownRichTextNode : RichText {
    override val type: RichTextType get() = RichTextType.UNKNOWN
}
