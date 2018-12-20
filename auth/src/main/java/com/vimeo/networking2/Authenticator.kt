package com.vimeo.networking2

/**
 * Authentication with email, google, facebook or pincode.
 * This is a temporary class that will be updated as requests are added.
 *
 * @param serverConfig All the server configuration (client id and secret, custom interceptors,
 *                     read timeouts, base url etc...) that can be set for authentication and
 *                     making requests.
 */
class Authenticator(private val serverConfig: ServerConfig)
