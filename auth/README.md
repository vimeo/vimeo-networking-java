# Authentication

In order to get data from the Vimeo API, we need to generate an access token. There are two types of access tokens - basic and authenticated. It can be obtained by using the [Authenticator](./src/main/java/com/vimeo/networking2/Authenticator.kt) interface. It provides methods to authenticate via email, Google or Facebook. These methods will provide you with an access token via a callback.

## Basic Authentication

A basic access token can be obtained by using your client id, client secret and access scopes. A client id and client secret can created by going to [Create an App](https://developer.vimeo.com/).

After you get your client credentials, create an instance of [ServerConfig](../api/src/main/java/com/vimeo/networking2/config/ServerConfig.kt) as  follows:

```kotlin

// Kotlin
val serverConfig = ServerConfig(CLIENT_ID, CLIENT_SECRET)

```

```java

// Java
ServerConfig serverConfig = new ServerConfig(CLIENT_ID, CLIENT_SECRET);

```

In order to authenticate with your client credentials, create an instance of [Authenticator](./src/main/java/com/vimeo/networking2/Authenticator.kt) and pass your `ServerConfig` object to it.

```kotlin

// Kotlin
val authenticator = Authenticator.create(serverConfig)

```

```java

// Java
Authenticator authenticator = Authenticator.Factory.create(serverConfig);

```

To authenticate, you would invoke the [Authenticator.clientCredentials](./src/main/java/com/vimeo/networking2/Authenticator.kt#L47) method by supplying a [VimeoCallback](../api/src/main/java/com/vimeo/networking2/VimeoCallback.kt). The callback has a `onSuccess` and `onError` method to provide you the result of the request.

```kotlin

// Kotlin
authenticator.clientCredentials(object: VimeoCallback<BasicAuthToken>() {

      override fun onSuccess(authResponse: VimeoResponse.Success<BasicAuthToken>) {
               
               authResponse.data.accessToken
      }
 
      override fun onError(error: VimeoResponse.Error) { 
         
                 when(error) {
                      is VimeoResponse.Api -> {  }
                      is VimeoResponse.Exception -> {  }
                      is VimeoResponse.Generic -> {  }
                 }
       }
})

```

```java

// Java
authenticator.clientCredentials(new VimeoCallback<BasicAccessToken>() {

    @Override
    public void onError(@NotNull Error error) {
        
        if (error instanceof Error.Api) {
                            
        } else if (error instanceof Error.Generic) {
                            
        } else if (error instanceof Error.Exception) {
                            
        }
        
     }

    @Override
    public void onSuccess(@NotNull Success<BasicAccessToken> response) {
        BasicAccessToken data = response.getData();
        String accessToken = data.getAccessToken();
    }
});

```

Upon success, a [BasicAuthToken](../models/src/main/java/com/vimeo/networking2/BasicAccessToken.kt) is returned. It contains your basic access token that you could use to make requests with. The `onError` method will inform you of three types of possible errors that can occur - api error, exception and generic error. 

## Scopes

The data you could access with the token is determined by a list of scopes. Here are the list of [Scopes](https://developer.vimeo.com/api/authentication#scopes) that can be provided. Each scope maps to an enum [ScopeType](../api/src/main/java/com/vimeo/networking2/ScopeType.kt). Providing a list of scopes is optional. If you don't provide one, by default `ScopeType.PUBLIC` will be used.

```kotlin

// Kotlin
val scopes = listOf(ScopeType.PRIVATE, ScopeType.CREATE, ScopeType.EDIT)
val serverConfig = ServerConfig(CLIENT_ID, CLIENT_SECRET, scopes)

```


## Advanced Authentication

### Create a Vimeo Account via Email

If you would like to create a Vimeo account via email, you will need to provide the display name, email and password, and whether you want to opt out of marketing emails. This can be done as follows:

Kotlin

```kotlin

// Kotlin
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

Upon success, you will get a [AuthenticatedAccessToken](../models/src/main/java/com/vimeo/networking2/AuthenticatedAccessToken.kt) which contains the access token and the authenticated [User](../models/src/main/java/com/vimeo/networking2/User.kt) object.

### Login via email

```kotlin

// Kotlin
authenticator.emailLogin(
        email = "john.doe@gmail.com",
        password = "password",
        authCallback = object: VimeoCallback<AuthenticatedAccessToken> {
                  
             override fun onSuccess(response: VimeoResponse.Success<AuthenticatedAccessToken>) { ... }
             
             override fun onError(error: VimeoResponse.Error) { ... }

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
            public void onSuccess(@NotNull Success<AuthenticatedAccessToken> response) {  }

           @Override
           public void onError(@NotNull Error error) { }
       });

```

After you successfully login into Google, use the returned token to authenticate as shown above. If you don't have a Vimeo Account, this will create one for you. Otherwise, it will log you in. 


## Errors

__API Error__

If you provide an invalid client id and secret and attempt to make a request, the SDK will notify you on error. Error info is provided to you in the class `ApiError`. Here is how you could get info on the error:

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

A generic error could occur if the SDK failed to parse the response from the API or the API didn't return a response at all. The request returned an http status code and no exceptions were thrown. 

```kotlin

// Kotlin
 override fun onError(error: VimeoResponse.Error) { 

       if(error is Error.Generic) {
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

By default certificate pining is enabled for every request. If you would like to disable it, you may specify it when configuring the SDK. 

```kotlin

// Kotlin
val certPinningEnabled = false
val serverConfig = ServerConfig(CLIENT_ID, CLIENT_SECRET, scopes, certPinningEnabled)

```

If you are using the SDK on KitKat, then you will have to turn on certificate pinning. In KitKat, TLS is disabled by default. 
