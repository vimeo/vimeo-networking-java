# Authentication

In order to get data from the Vimeo API, we need to generate an access token. There are two types of access tokens - basic and authenticated. It can be obtained by using the [Authenticator](./src/main/java/com/vimeo/networking2/Authenticator.kt) interface. It provides methods to authenticate via code grant, email login, Google login, or Facebook login. These methods will provide you with an access token via a callback.

## Basic Authentication

A basic access token can be obtained by using your client ID, client secret and access scopes. A client id and client secret can created by going to [Create an App](https://developer.vimeo.com/).

After you get your client credentials, create an instance of [VimeoApiConfiguration](../api-core/src/main/java/com/vimeo/networking2/config/VimeoApiConfiguration.kt) as  follows:

```kotlin
// Kotlin
val configuration = VimeoApiConfiguration.Builder(CLIENT_ID, CLIENT_SECRET, listOf(ScopeType.PUBLIC, ScopeType.PRIVATE))
    .build()
```

```java
// Java
final List<ScopeType> scopes = Arrays.asList(ScopeType.PUBLIC, ScopeType.PRIVATE);
final VimeoApiConfiguration configuration = new VimeoApiConfiguration.Builder(CLIENT_ID, CLIENT_SECRET, scopes).build();
```

In order to authenticate with your client credentials, create an instance of [Authenticator](../auth/src/main/java/com/vimeo/networking2/Authenticator.kt) and pass your `VimeoApiConfiguration` object to it.

```kotlin
// Kotlin (new instance)
val authenticator = Authenticator(vimeoApiConfiguration)

// Kotlin (singleton)
Authenticator.initialize(vimeoApiConfiguration)
```

```java
// Java (new instance)
final Authenticator authenticator = Authenticator.create(vimeoApiConfiguration);

// Java (singleton)
Authenticator.initialize(vimeoApiConfiguration);
```

To authenticate, you would invoke the [Authenticator.authenticateWithClientCredentials](../auth/src/main/java/com/vimeo/networking2/Authenticator.kt#L116) method by supplying a [VimeoCallback](../api-core/src/main/java/com/vimeo/networking2/VimeoCallback.kt). The callback has an `onSuccess` and `onError` method to provide you the result of the request.  In Kotlin, you can also use the `vimeoCallback` function for a better experience

```kotlin
// Kotlin
authenticator.authenticateWithClientCredentials(vimeoCallback(
  onSuccess = {
    println("Access Token: ${it.data.accessToken}")
  },
  onError = {
    println("Couldn't obtain access token")
  }
))
```

```java

// Java
authenticator.clientCredentials(new VimeoCallback<VimeoAccount>() {
  @Override
  public void onSuccess(@NotNull final VimeoResponse.Success<BasicAccessToken> response) {
    final VimeoAccount data = response.getData();
    println("Access Token: " + data.getAccessToken());
  }
  @Override
  public void onError(@NotNull final VimeoResponse.Error error) {
    println("Couldn't obtain access token");
  }
});
```

Upon success, a [VimeoAccount](../models/src/main/java/com/vimeo/networking2/VimeoAccount.kt) is returned. It contains your basic access token that will be used to make requests. The `onError` method will inform you of three types of possible errors that can occur - API errors, invalid token errors, exceptions, and unknown errors.  The `VimeoAccount` is stored in the `AccountStore` instance that was provided to the `VimeoApiConfiguration` and will be the account used for all subsequent requests. If there was previously a different account in the `AccountStore`, authenticating will overwrite the previously stored account.

## Scopes

The data that you can access with the token is determined by a list of scopes. Here are the list of [Scopes](https://developer.vimeo.com/api/authentication#scopes) that can be provided. Each scope maps to an enum [ScopeType](../api-core/src/main/java/com/vimeo/networking2/ScopeType.kt). Providing a list of scopes is optional, and if you don't provide one, by default `ScopeType.PUBLIC` will be used.  Beware that many properties returned by the API will be `null` if only the public scope is used.

```kotlin
// Kotlin
val scopes = listOf(ScopeType.PRIVATE, ScopeType.CREATE, ScopeType.EDIT)
val vimeoApiConfiguration = VimeoApiConfiguration.Builder(CLIENT_ID, CLIENT_SECRET, scopes).build()
```

```java
// Java
final List<ScopeType> scopes = Arrays.asList(ScopeType.PRIVATE, ScopeType.CREATE, ScopeType.EDIT);
final VimeoApiConfiguration vimeoApiConfiguration = new VimeoApiConfiguration.Builder(CLIENT_ID, CLIENT_SECRET, scopes).build();
```


## Advanced Authentication

### Create a Vimeo Account via Email

If you would like to create a Vimeo account via email, you will need to provide the display name, email and password, and whether you want to opt into marketing emails. This can be done as follows:

Kotlin

```kotlin

// Kotlin
authenticator.emailJoin(
    display_name = "John Doe",
    email = "john.doe@gmail.com",
    password = "password",
    marketingOptIn = false,
    callback = object : VimeoCallback<AuthenticatedAccessToken> {
        override fun onSuccess(response: VimeoResponse.Success<AuthenticatedAccessToken>) { }
        override fun onError(error: VimeoResponse.Error) { }
    })

```

```java

// Java
authenticator.emailJoin(
    "John Doe",
    "john.doe@gmail.com",
    "password",
    false,
    new VimeoCallback<AuthenticatedAccessToken>() {
        @Override
 public void onSuccess(@NotNull Success<AuthenticatedAccessToken> response) { }
        @Override
 public void onError(@NotNull Error error) { }
});
```

Upon success, you will get an [AuthenticatedAccessToken](../models/src/main/java/com/vimeo/networking2/AuthenticatedAccessToken.kt) which contains the access token and the authenticated [User](../models/src/main/java/com/vimeo/networking2/User.kt) object.

### Login via email

```kotlin

// Kotlin
authenticator.emailLogin(
    email = "john.doe@gmail.com",
    password = "password",
    authCallback = object: VimeoCallback<AuthenticatedAccessToken> {
        override fun onSuccess(response: VimeoResponse.Success<AuthenticatedAccessToken>) { }
        override fun onError(error: VimeoResponse.Error) { }
})
```

```java

// Java
authenticator.emailLogin(
    "john.doe@gmail.com",
    "password",
    new VimeoCallback<AuthenticatedAccessToken>() {
        @Override
 public void onSuccess(@NotNull Success<AuthenticatedAccessToken> response) { }
        @Override
 public void onError(@NotNull Error error) { }
});

```

### Facebook and Google Authentication

There is a single method the SDK provides to create a Vimeo Account with Google or Facebook or to login via these social channels.

```kotlin

// Kotlin
authenticator.google(
    token = "google_token",
    email = "john.doe@gmail.com",
    marketingOptIn = false,
    authCallback = object : VimeoCallback<AuthenticatedAccessToken> {
        override fun onSuccess(response: VimeoResponse.Success<AuthenticatedAccessToken>){ ... }
        override fun onError(error: VimeoResponse.Error) { ... }
})
```

```java

// Java
authenticator.google(
    "google_token",
    "john.doe@gmail.com",
    false,
    new VimeoCallback<AuthenticatedAccessToken>() {
        @Override
 public void onSuccess(@NotNull Success<AuthenticatedAccessToken> response) { ... }
        @Override
 public void onError(@NotNull Error error) { ... }
});
```

After you successfully login into Google, use the returned token to authenticate as shown above. If you don't have a Vimeo Account, this will create one for you. Otherwise, it will log you in.


## Errors

__API Error__

If you provide an invalid client id and secret and attempt to make a request, the SDK will notify you on error. Error info is provided to you in the class [ApiError](../models/src/main/java/com/vimeo/networking2/ApiError.kt). Here is how you could get info on the error:

```kotlin

// Kotlin
override fun onError(error: VimeoResponse.Error) {
    if (error is VimeoResponse.Error.Api) {
        error.reason.errorCode
        error.reason.errorMessage
        error.reason.invalidParameters
    }
}
```

```java

// Java
authenticator.clientCredentials(new VimeoCallback<BasicAccessToken>() {
    @Override
 public void onError(@NotNull Error error) {
        if (error instanceof Error.Api) {
            Api apiError = (Api) error;
            ApiError reason = apiError.getReason();
            reason.getErrorCode();
            reason.getErrorMessage();
            reason.getInvalidParameters();
        }
    }
});

```

The error message and code could be extracted from the `ApiError` object.

### Exception

If any exception occurs during your request, you will also be informed through the `onError` callback method. A exception may occur if you don't have a connection and you had attempted to make a request.


```kotlin

// Kotlin
override fun onError(error: VimeoResponse.Error) {
    if (error is VimeoResponse.Error.Exception) {
        error.exception.developerMessage
        error.exception.printStackTrace()
    }
}

```

```java

// Java
authenticator.clientCredentials(new VimeoCallback<BasicAccessToken>() {
    @Override
 public void onError(@NotNull Error error) {
        if (error instanceof Error.Exception) {
            Error.Exception exception = ((Exception) error)
            exception.getThrowable();
            exception.getThrowable().printStackTrace();
        }
    }
});

```

You could extract information from the throwable that is returned to you.

### Generic Error

A generic error could occur if the SDK failed to parse the response from the API or the API didn't return a response at all. The request returned an HTTP status code and no exceptions were thrown.

```kotlin

// Kotlin
override fun onError(error: VimeoResponse.Error) {
    if (error is Error.Generic) {
        error.generic.httpStatusCode
        error.generic.rawResponse
    }
}
```

```java

// Java
@Override
public void onError(@NotNull Error error) {
    if (error instanceof Error.Generic) {
        Error.Generic generic = ((Generic) error);
        generic.getHttpStatusCode();
        generic.getRawResponse();
    }
}
```

The HTTP status code and any possible data returned from the API can extracted from the `VimeoResponse.GenericError` class.


## Certificate Pinning

By default certificate pinning is enabled for every request. If you would like to disable it, you may specify it when configuring the SDK.

```kotlin
// Kotlin
val vimeoApiConfiguration = VimeoApiConfiguration.Builder(CLIENT_ID, CLIENT_SECRET, scopes)
  .withCertPinning(certPinningEnabled = false)
  .build()
```

```java
// Java
final VimeoApiConfiguration vimeoApiConfiguration = new VimeoApiConfiguration.Builder(CLIENT_ID, CLIENT_SECRET, scopes)
  .withCertPinning(false)
  .build();
```

When using the SDK on KitKat, you will have to turn off certificate pinning, since TLS is disabled by default.
