package com.vimeo.networking2.richtext

import com.vimeo.networking2.enums.StringValue

/**
 * Enum with types of [RichText] nodes.
 */
enum class RichTextType(override val value: String?) : StringValue {

    /**
     * Type of node that represents document.
     */
    DOC("doc"),

    /**
     * Type of node that represents paragraph.
     */
    PARAGRAPH("paragraph"),

    /**
     * Type of node that represents mention.
     */
    MENTION("mention"),

    /**
     * Type of node that represents simple text.
     */
    TEXT("text"),

    /**
     * Unknown node type.
     */
    UNKNOWN(null),
}
