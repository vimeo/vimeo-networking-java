package com.vimeo.networking2

data class Credit(

    /**
     * The name of the person credited.
     */
    val name: String?,

    /**
     * The character that this person portrayed, or the job that this person performed.
     */
    val role: String?,

    /**
     * The unique identifier to access the credits resource.
     */
    val uri: String?,

    /**
     * The Vimeo user associated with this credit.
     */
    val user: User?,

    /**
     * The video associated with this credit.
     */
    val video: Video?

)
