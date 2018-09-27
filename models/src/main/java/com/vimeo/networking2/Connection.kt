package com.vimeo.networking2

data class Connection(

    /**
     * An array of HTTP methods permitted on this URI.
     */
    val options: List<String>?,

    /**
     * The API URI that resolves to the connection data.
     */
    val uri: String?,

    /**
     * The total number of albums on this connection.
     */
    val total: Int?

)
