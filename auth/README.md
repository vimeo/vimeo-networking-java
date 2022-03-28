# Authentication

In order to get data from the Vimeo API, we need to generate an access token. There are two types of access tokens - basic and authenticated. It can be obtained by using the [Authenticator](./src/main/java/com/vimeo/networking2/Authenticator.kt) interface. It provides methods to authenticate via code grant, email login, Google login, or Facebook login. These methods will provide you with an access token via a callback.

## Basic Authentication

A basic access token can be obtained by using your client ID, client secret and access scopes. A client id and client secret can created by going to [Create an App](https://developer.vimeo.com/).

After you get your client credentials, create an instance of [VimeoApiConfiguration](../api-core/src/main/java/com/vimeo/networking2/config/VimeoApiConfiguration.kt) as  follows:

```kotlin
val configuration = VimeoApiConfiguration.Builder(CLIENT_ID, CLIENT_SECRET, listOf(ScopeType.PUBLIC, ScopeType.PRIVATE))
    .build()
```

In order to authenticate with your client credentials, create an instance of [Authenticator](../auth/src/main/java/com/vimeo/networking2/Authenticator.kt) and pass your `VimeoApiConfiguration` object to it.

```kotlin
// (new instance)
val authenticator = Authenticator(vimeoApiConfiguration)

// (singleton)
Authenticator.initialize(vimeoApiConfiguration)
```

To authenticate, you would invoke the [Authenticator.authenticateWithClientCredentials](../auth/src/main/java/com/vimeo/networking2/Authenticator.kt#L116) method by supplying a [VimeoCallback](../api-core/src/main/java/com/vimeo/networking2/VimeoCallback.kt). The callback has an `onSuccess` and `onError` method to provide you the result of the request.  In Kotlin, you can also use the `vimeoCallback` function for a better experience

```kotlin
authenticator.authenticateWithClientCredentials(vimeoCallback(
  onSuccess = {
    println("Access Token: ${it.data.accessToken}")
  },
  onError = {
    println("Couldn't obtain access token")
  }
))
```

Upon success, a [VimeoAccount](../models/src/main/java/com/vimeo/networking2/VimeoAccount.kt) is returned. It contains your basic access token that will be used to make requests. The `onError` method will inform you of four types of possible errors that can occur - API errors, invalid token errors, exceptions, and unknown errors.  The `VimeoAccount` is stored in the `AccountStore` instance that was provided to the `VimeoApiConfiguration` and will be the account used for all subsequent requests. If there was previously a different account in the `AccountStore`, authenticating will overwrite the previously stored account.

## Scopes

The data that you can access with the token is determined by a list of scopes. Here are the list of [Scopes](https://developer.vimeo.com/api/authentication#table-1) that can be provided. Each scope maps to an enum [ScopeType](../api-core/src/main/java/com/vimeo/networking2/ScopeType.kt). Providing a list of scopes is optional, and if you don't provide one, by default `ScopeType.PUBLIC` will be used.  Beware that many properties returned by the API will be `null` if only the public scope is used.

```kotlin
val scopes = listOf(ScopeType.PRIVATE, ScopeType.CREATE, ScopeType.EDIT)
val vimeoApiConfiguration = VimeoApiConfiguration.Builder(CLIENT_ID, CLIENT_SECRET, scopes).build()
```

## Other Authentication Approaches

If you don't want to use basic authentication or code grant redirect authentication, you can authenticate with the library by generating and specifying an access token. You can generate the access token by going to the [Vimeo developer site](https://developer.vimeo.com/apps) and generating an access token for your app. Once you have the access token, you can initialize the Vimeo SDK with said token. While the default `AccountStore` used by the `Authenticator` (and most implementations you would use) does not have a valid account until you've authenticated with the API, we can use a custom implementation that hardcodes the account we're using. This allows us to pass a specific access token to the SDK and always use it.

```kotlin
class FixedTokenAccountStore(accessToken: String) : AccountStore {
  private val account = VimeoAccount(accessToken = accessToken)

  override fun loadAccount(): VimeoAccount = account
  override fun storeAccount(vimeoAccount: VimeoAccount) = Unit
  override fun removeAccount() = Unit
}

val vimeoApiConfiguration = VimeoApiConfiguration.Builder(CLIENT_ID, CLIENT_SECRET, listOf(ScopeType.PUBLIC))
  .withAccountStore(FixedTokenAccountStore(ACCESS_TOKEN))
  .build()
val authenticator = Authenticator(vimeoApiConfiguration)
val vimeoApiClient = VimeoApiClient(vimeoApiConfiguration, authenticator)

// Any requests made will now utilize the ACCESS_TOKEN without additional log in.
```

## Errors

### API Error

If you provide an invalid client ID and secret and attempt to make a request, the SDK will notify you with an error. Error info is provided in the class [ApiError](../models/src/main/java/com/vimeo/networking2/ApiError.kt). If the reason for an error was a response from the API, you can obtain the reason from the response as follows within the `VimeoCallback`.

```kotlin
onError = {
  if (it is VimeoResponse.Error.Api) {
    println(it.reason)
  }
}
```

The error message and code can then be extracted from the `ApiError` object to help you determine what went wrong.

### Exception

If any exception occurs during your request, you will also be informed through the `onError` callback method. For example, an exception may occur if you don't have a network connection and you attempt to make a request.


```kotlin
onError = {
  if (it is VimeoResponse.Error.Exception) {
    println(it.throwable)
  }
}
```

You can then extract information from the throwable that is returned to determine its cause.

### Invalid Token Error

Invalid token errors should be rare, but can occur in a couple scenarios. The first scenario would happen if the access token you are using was revoked by the API. This could happen if the access token got flagged for spam. Another case that this would happen in is if you initialized the SDK with the wrong token or if you accidentally deleted the token on the API website before you used it.

```kotlin
onError = {
  if (it is VimeoResponse.Error.InvalidToken) {
    println(it.reason)
  }
}
```

The reason for the invalid token can then be used to determine what the origin of the problem is.

### Unknown Error

An unknown error can occur if the SDK fails to parse the response from the API or the API doesn't return a response at all. The API may have responded with an HTTP status code and no exceptions were thrown, but the SDK was unable to find a response that it expected. This sort of exception should be extremely rare, and any that you experience should be reported, as they are likely caused by a bug in the SDK or in the API itself.

```kotlin
onError = {
  if (error is VimeoResponse.Error.Unknown) {
    println(error.rawResponse)
  }
}
```

The HTTP status code and any possible data returned from the API can extracted from the `VimeoResponse.Error.Unknown` class.
