package com.vimeo.networking2.enums

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
    BRAZILIAN_PORTUGUESE("pt-BR"),

    /**
     * Unknown language preference.
     */
    UNKNOWN(null)
}
