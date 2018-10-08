package com.vimeo.networking2

data class Credit(

    /**
     * The name of the person credited.
     */
    val name: String? = null,

    /**
     * The character that this person portrayed, or the job that this person performed.
     */
    val role: String? = null,

    /**
     * The unique identifier to access the credits resource.
     */
    val uri: String? = null,

    /**
     * The Vimeo user associated with this credit.
     */
    val user: User? = null,

    /**
     * The video associated with this credit.
     */
    val video: Video? = null

)
