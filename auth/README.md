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

## Other Authentication Approaches

If you don't want to use basic authentication or code grant redirect authentication, you can authenticate with the library by generating and specifying an access token. You can generate the access token by going to the [Vimeo developer site](https://developer.vimeo.com/apps) and generating an access token for your app. Once you have the access token, you can initialize the Vimeo SDK with said token. While the default `AccountStore` used by the `Authenticator` (and most implementations you would use) does not have a valid account until you've authenticated with the API, we can use a custom implementation that hardcodes the account we're using. This allows us to pass a specific access token to the SDK and always use it.

```kotlin
// Kotlin
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
```java
// Java
public class FixedTokenAccountStore implements AccountStore {
  private final VimeoAccount vimeoAccount;

  public FixedTokenAccountStore(final String accessToken) {
    vimeoAccount = new VimeoAccount(accessToken, null, null, null, null, null);
  }

  @Override
  @Nullable  public VimeoAccount loadAccount() {
    return vimeoAccount;
  }

  @Override
  public void removeAccount() {}

  @Override
  public void storeAccount(@NotNull final VimeoAccount vimeoAccount) {}
}

final VimeoApiConfiguration vimeoApiConfiguration = new VimeoApiConfiguration.Builder(CLIENT_ID, CLIENT_SECRET, Arrays.asList(ScopeType.PUBLIC))
  .withAccountStore(new FixedTokenAccountStore(ACCESS_TOKEN))
  .build();
final Authenticator authenticator = Authenticator.create(vimeoApiConfiguration);
final VimeoApiClient vimeoApiClient = VimeoApiClient.create(vimeoApiConfiguration, authenticator);

// Any requests made will now utilize the ACCESS_TOKEN without additional log in.
```

## Errors

### API Error

If you provide an invalid client ID and secret and attempt to make a request, the SDK will notify you with an error. Error info is provided in the class [ApiError](../models/src/main/java/com/vimeo/networking2/ApiError.kt). If the reason for an error was a response from the API, you can obtain the reason from the response as follows within the `VimeoCallback`.

```kotlin
// Kotlin
onError = {
  if (it is VimeoResponse.Error.Api) {
    println(it.reason)
  }
}
```

```java
// Java
@Override
public void onError(@NotNull final VimeoResponse.Error error) {
  if (error instanceof VimeoResponse.Error.Api) {
    final Api apiError = (Api) error;
    final ApiError reason = apiError.getReason();
    println(reason);
  }
}
```

The error message and code can then be extracted from the `ApiError` object to help you determine what went wrong.

### Exception

If any exception occurs during your request, you will also be informed through the `onError` callback method. For example, an exception may occur if you don't have a network connection and you attempt to make a request.


```kotlin
// Kotlin
onError = {
  if (it is VimeoResponse.Error.Exception) {
    println(it.throwable)
  }
}
```

```java
// Java
@Override
public void onError(@NotNull final VimeoResponse.Error error) {
  if (error instanceof VimeoResponse.Error.Exception) {
    final VimeoResponse.Error.Exception exception = (Exception) error;
    println(exception.getThrowable());
  }
}
```

You can then extract information from the throwable that is returned to determine its cause.

### Invalid Token Error

TODO

### Unknown Error

An unknown error can occur if the SDK fails to parse the response from the API or the API doesn't return a response at all. The API may have responded with an HTTP status code and no exceptions were thrown, but the SDK was unable to find a response that it expected. This sort of exception should be extremely rare, and any that you experience should be reported, as they are likely caused by a bug in the SDK or in the API itself.

```kotlin
// Kotlin
onError = {
  if (error is VimeoResponse.Error.Unknown) {
    println(error.rawResponse)
  }
}
```

```java
// Java
@Override
public void onError(@NotNull VimeoResponse.Error.Unknown error) {
  if (error instanceof VimeoResponse.Error.Unknown) {
    final VimeoResponse.Error.Unknown unknown = (Unknown) error;
    println(unknown.getRawResponse());
  }
}
```

The HTTP status code and any possible data returned from the API can extracted from the `VimeoResponse.Error.Unknown` class.


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
