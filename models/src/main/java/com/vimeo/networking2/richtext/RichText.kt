package com.vimeo.networking2.richtext

/**
 * Interface for all models representing rich text nodes.
 */
sealed interface RichText {
    /**
     * The [RichTextType] of this node.
     */
    val type: RichTextType?

    companion object {
        /**
         * The name of the JSON field representing type of the rich text node.
         */
        const val TYPE_FIELD = "type"
    }
}
