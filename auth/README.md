# Authentication

In order to get data from the Vimeo API, we need to generate an access token. There are two types of access tokens - basic and authenticated. It can be obtained by using the [Authenticator](./src/main/java/com/vimeo/networking2/Authenticator.kt) interface. It provides methods to authenticate via email, Google or Facebook. These methods will provide you with an access token via a callback.

### Basic Authentication

A basic access token can be obtained by using your client id, client secret and access scopes. A client id and secret can created in the developer website [Create an App](https://developer.vimeo.com/).

After you get your client credentials, create an instance of `ServerConfig` as  follows:

```
val serverConfig = ServerConfig(CLIENT_ID, CLIENT_SECRET)
```

In order to authenticate with your client credentials, create an instance of [Authenticator](/src/main/java/com/vimeo/networking2/Authenticator.kt) and pass your `ServerConfig` object to it.

```
val authenticator = Authenticator.create(serverConfig)
```

To authenticate, you would invoke the `Authenticor#clientCredentials` method by supplying a `VimeoCallback`. The callback has a `onSuccess` and `onError` method to provide you the result of the request.

```
authenticator.clientCredentials(object: VimeoCallback<BasicAuthToken>() {

      override fun onSuccess(authResponse: VimeoResponse.Success<BasicAuthToken>) {
               
               authResponse.data.accessToken
      }
 
      override fun onError(error: VimeoResponse.Error) { 
         
                 when(error) {
                      is VimeoResponse.Api -> { ... }
                      is VimeoResponse.Exception -> { ... }
                      is VimeoResponse.Generic -> { ... }
                 }
       }
})
```

Upon success, a [BasicAuthToken](../models/) is returned. It contains your basic access token that you could use to make requests with. The onError method will inform you of three types of possible errors that can occur - api error, exception and generic error. 

__Errors__

API Error

If you provide an invalid client id and secret and attempt to make a request, the SDK will notify you on error. Error info is provided to you in the class `ApiError`. Here is how you could get info on the error:

```
 override fun onError(error: VimeoResponse.Error) { 
         
                 when(error) {
                      is VimeoResponse.Api -> {  error ->
                              error.developerMessage
                              error.errorMessage
                              error.errorCode
                      }
                     ...
                 }
}
```

The error message and code could be extracted from the `ApiError` object.

__Exception__

If any exception occurs during your request, you will also be informed through the `onError` callback method. A exception may occur if you don't have a connection and you had attempted to make a request. 

```
 override fun onError(error: VimeoResponse.Error) { 
         
                 when(error) {
                      is VimeoResponse.Exception -> {  exception ->
                              exception.developerMessage
                              exception.printStackTrace()
                      }
                     ...
                 }
}
```

You could extract information from the throwable that is returned to you.

__Generic Error__

A generic error could occur if the SDK failed to parse the response from the API or the API didn't return a response at all. The request returned an http status code and no exceptions were thrown. 

```
 override fun onError(error: VimeoResponse.Error) { 

                 when(error) {
                      is VimeoResponse.Generic -> {  generic ->
                                 generic.httpStatusCode
                                 generic.rawResponse
                      }
                     ...
                 }
}
```

The HTTP status code and any possible data returned from the API can extracted from the `VimeoResponse.GenericError` class.

__Scopes__

What you could access with the token is determined by a list of scopes. Here are the list of [Scopes](https://developer.vimeo.com/api/authentication#scopes) that can be provided. Each scope maps to an enum [ScopeType](../api/src/main/java/com/vimeo/networking2/ScopeType.kt). Providing a list of scopes is optional. If you don't provide one, by default `ScopeType.PUBLIC` will be used.

```
val scopes = listOf(ScopeType.PRIVATE, ScopeType.CREATE, ScopeType.EDIT)
val serverConfig = ServerConfig(CLIENT_ID, CLIENT_SECRET, scopes)
```

### Advanced Authentication

__Create a Vimeo Account via Email__

If you would like to create a Vimeo account via, you will need to provide the display name, email and password, and whether you want to opt out of marketing emails. This can be done as follows:

```
authenticator.emailJoin(
        display_name = "John Doe", 
        email = "john.doe@gmail.com", 
        password = "password", 
        marketingOptIn = false, 
        callback = object : VimeoCallback<AuthenticatedAccessToken> {
              
               override fun onSuccess(response: VimeoResponse.Success<AuthenticatedAccessToken>) { ...  }

                override fun onError(error: VimeoResponse.Error) { ... }

            })
```

Upon success, you will get a [AuthenticatedAccessToken](...). This object contains the access token, but it also contains the [User](...) object of authenticated user.

__Login via email__

To login via email and password, use `Authenticator#loginEmail`.

```
authenticator.emailLogin(
          email = "john.doe@gmail.com",
          password = "password",
           authCallback = object: VimeoCallback<AuthenticatedAccessToken> {
                  
                   override fun onSuccess(response: VimeoResponse.Success<AuthenticatedAccessToken>) { ... }
                    override fun onError(error: VimeoResponse.Error) { ... }

                }
            )
```

__Facebook and Google Authentication__

There is a single method the SDK provides to create a Vimeo Account with Google or Facebook or to login via these social channels. 

```
authenticator.google(
             token = "google_token",
             email = "john.doe@gmail.com",
             marketingOptIn = false,
             authCallback = object : VimeoCallback<AuthenticatedAccessToken> {
                    override fun onSuccess(response: VimeoResponse.Success<AuthenticatedAccessToken>){ ... }

                    override fun onError(error: VimeoResponse.Error) { ... }
                })
```

After you successfully login into Google, use the returned token to authenticate as shown above. If you don't have a Vimeo Account, this will create one for you. Otherwise, it will log you in. 

__Certificate Pinning__

By default certificate pining is enabled for every request. If you would like to disable it, you may specify it when configuring the SDK. 

```
val certPinningEnabled = false
val serverConfig = ServerConfig(CLIENT_ID, CLIENT_SECRET, scopes, certPinningEnabled)
```

If you are using the SDK on KitKat, then you will have to turn on certificate pinning. In KitKat, TLS is disabled by default. 
