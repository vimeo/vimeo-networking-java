package com.vimeo.networking2.params

import com.vimeo.networking2.enums.StringValue

/**
 * Language preference of the Slack channel being notified.
 */
enum class SlackLanguagePreference(override val value: String?) : StringValue {

    /**
     * German.
     */
    GERMAN("de-DE"),

    /**
     * English.
     */
    ENGLISH("en"),

    /**
     * Spanish.
     */
    SPANISH("es"),

    /**
     * French.
     */
    FRENCH("fr-FR"),

    /**
     * Japanese.
     */
    JAPANESE("ja-JP"),

    /**
     * Korean.
     */
    KOREAN("ko-KR"),

    /**
     * Brazilian Portuguese.
     */
    BRAZILIAN_PORTUGUESE("pt-BR")
}
