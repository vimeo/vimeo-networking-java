# vimeo-networking
vimeo-networking is a JVM (Java & Kotlin) SDK used for interacting with the Vimeo API. The example app provided in this project shows the implementation in the context of an Android app.

#### WARNING: Please upgrade to version 3.x of this library by 3 March 2023 or manually disable certificate pinning on version 1.x or 2.x by this date. API requests that use certificate pinning will fail beginning on this date as the certificate that 1.x and 2.x pin to will expire and be replaced.

| Branch | Build Status |
|--------|--------------|
| develop | [![release workflow](https://github.com/vimeo/vimeo-networking-java/actions/workflows/shipit.yml/badge.svg)](https://github.com/vimeo/vimeo-networking-java/actions/workflows/shipit.yml) | 

| Latest Version |
|----------------|
| [![JitPack](https://jitpack.io/v/vimeo/vimeo-networking-java.svg)](https://jitpack.io/#vimeo/vimeo-networking-java) |

## Contents
* [Prerequisites](#prerequisites)
     * [API Registration](#api-registration)
* [Getting Started](#getting-started)
     * [Gradle](#gradle)
     * [JitPack](#jitpack)
     * [Submodule](#submodule)
     * [Initialization](#initialization)
     * [Authentication](#authentication)
* [Requests](#requests)
     * [Request Structure](#request-structure)
     * [Example Requests](#example-requests)
* [FAQs](#faqs)
     * [How do I get a video that I own?](#how-do-i-get-a-video-that-i-own)
     * [How do I play a video?](#how-do-i-play-a-video)
* [Found an Issue?](#found-an-issue)
* [Questions](#questions)
* [License](#license)

## Prerequisites
In order to get started with using this library, there are a few prerequisites you need to satisfy, namely, creating a Vimeo account and registering an app with the API.

### API Registration
In order to use this library and the Vimeo API, you will need an application registered with the Vimeo API. If you do not already have an application registered, you can do so [here](https://developer.vimeo.com/apps). If you do not have a Vimeo account, you will be prompted to sign up before registration.

After you have an application registered, you will have access to a few critical pieces of information required for [initialization](#initialization) - the client identifier and client secret. These will both be located in the Authentication tab once you select your app from the [list here](https://developer.vimeo.com/apps).

More information about this and other API questions can be found on the [API homepage](https://developer.vimeo.com/api).


## Getting Started
For a more in depth look at the usage, refer to the [example Android app](example). The example project includes implementations of client credential authentication, oauth authentication, and making simple requests.

### Gradle
Make sure you include JitPack as a maven repository in your `build.gradle` file.
```groovy
repositories {
  maven { url 'https://jitpack.io' }
}
```

Then add the following two dependencies to your `build.gradle` file.
```groovy
implementation "com.github.vimeo.vimeo-networking-java:vimeo-networking:LATEST-VERSION"
implementation "com.github.vimeo.vimeo-networking-java:models:LATEST-VERSION"
```

See the [CHANGELOG](CHANGELOG.md) for the changes to each release.

#### Artifacts
There are several available artifacts for this SDK.

The core artifacts are 
- `api-core` - Contains general utilities, such as `VimeoCallback`.
- `auth` - Contains the `Authenticator` and other authentication code.
- `request` - Contains the `VimeoApiClient` and all request code.
- `models` - Contains all the data transfer objects (DTOs) that are returned by the API.

One aggregate artifact exists as a convenience that bundles `api-core`, `auth`, and `request`
- `vimeo-networking` - Includes all necessary modules except `models`.

Two alternative `models` artifacts exist
- `models-parcelable` - Provides `models` that implement the Android `Parcelable` interface.
- `models-serializable` - Provides `models` that implement the Java `Serializable` interface.

In order to use the SDK, you must always specify both `vimeo-networking` and one of `models`, `models-parcelable`, or `models-serializable`. Note that each `models` artifact is exclusive and cannot be used with another. You can only use one at a time. If you don't need `Parcelable` or `Serializable`, it's recommended to just use the default `models`.

### JitPack
Generally, we recommend to use the latest release or alpha version that you can find in the releases tab. However, if you prefer to point to a specific commit, you can use JitPack to specify that commit:
```groovy
implementation "com.github.vimeo.vimeo-networking-java:vimeo-networking:COMMIT_HASH"
implementation "com.github.vimeo.vimeo-networking-java:models:COMMIT_HASH"
```

### Submodule
We recommend using JitPack, but if you'd like to use the library as a submodule, clone the repo:
```
git submodule add git@github.com:vimeo/vimeo-networking-java.git
```
Then in your `build.gradle` use:
```groovy
implementation project(':vimeo-networking-java:vimeo-networking')
implementation project(':vimeo-networking-java:models')
```

### Initialization

Access to the API is performed through the `VimeoApiClient`, which must be initialized prior to use. Consumers can choose to initialize a singleton instance or to create their own instance of the `VimeoApiClient`. In order to initialize the client, we need to construct an `Authenticator`, which is used to perform authentication requests and manage the SDK's authentication state. Like the `VimeoApiClient`, we can initialize a singleton instance or create a new instance of the `Authenticator`.

Initialization of the `Authenticator` is as follows:
```kotlin
// Create a new instance
val vimeoApiConfiguration = ...
val authenticator = Authenticator(vimeoApiConfiguration)
```
or
```kotlin
// Initialize the singleton
val vimeoApiConfiguration = ...
Authenticator.initialize(vimeoApiConfiguration)
```
Once we have the `Authenticator` initialized, we can proceed with initializing the `VimeoApiClient` as follows:
```kotlin
// Create a new instance
val vimeoApiConfiguration = ...
val authenticator = ...
val vimeoApiClient = VimeoApiClient(vimeoApiConfiguration, authenticator)
```
or
```kotlin
// Initialize the singleton
val vimeoApiConfiguration = ...
VimeoApiClient.initialize(vimeoApiConfiguration, Authenticator.instance())
```
The `VimeoApiConfiguration` is a customizable configuration that we use to provide the client identifier, client secret, and access scopes the SDK will use to communicate with the API. Constructing the `VimeoApiConfiguration` is done using the `VimeoApiConfiguration.Builder` that provides defaults for the other required configuration parameters.

In the below sections, we cover examples of ways to customize the `VimeoApiClient` instance. A full implementation can be found in the [example](example) app.

#### Configuration Tips for All Apps

We recommend the following for all apps when configuring the SDK with the `VimeoApiConfiguration.Builder`:
- Provide a user agent to identify your app and the device using `withUserAgent(String)`. By default the library creates its own user agent, but supplementing it with more info provides the Vimeo API with a better picture of what devices are accessing it. Not required, and we provide a default if none is provided.
- Provide a cache directory with `withCacheDirectory(File)`. By default the SDK does not set the cache directory and **responses will not be cached**, so you'll have to choose one to get the best performance.
- Provide an `AccountStore` using `withAccountStore(AccountStore)`. Unless you are accessing the API with a fixed access token, then you should probably provide a store that is disk backed. The default used by the SDK is an in-memory store that **will not remember the logged in account** after the app is restarted.

#### Configuration for Apps with Account Management

Create a `VimeoApiConfiguration.Builder` with your client identifier, client secret, and scope choices. After applying the above [configuration tips](#configuration-tips-for-all-apps), the configuration will be ready to use to initialize the `Authenticator` and `VimeoApiClient`.

And once initialization is complete, authenticate if necessary. This authentication can include [client credentials](#client-credentials-grant) or authentication via [code grant](#oauth-authorization-code-grant).

Once authentication is complete, you can access the persisted `VimeoAccount` object through `Authenticator.currentAccount`.

#### Configuration Builder for Apps with Only One Developer Account

You can skip the need for [client credentials](#client-credentials-grant) or [code grant](#oauth-authorization-code-grant) requests if you provide an access token up front. With this access token you'll be able to make any requests that the access token's scope allows.  You will NOT be able to switch accounts if you only supply the access token. To do that, refer to the above section.

You can generate an access token in the Authentication tab once you select your app from the [list here](https://developer.vimeo.com/apps).

Once you have the access token, you can easily initialize your `Authenticator` and `VimeoApiClient` with the below configuration..

```kotlin
val accessToken: String = ... // Generate your access token on the API developer website
val vimeoApiConfiguration = VimeoApiConfiguration.Builder(accessToken)
  .build()
```

After providing the access token, if you'd like to have access to the associated `User` object you'll need to make a call to `VimeoApiClient.fetchCurrentUser(...)`.

**Note**: You will not be able to log out of the account associated with the access token provided to the `VimeoApiConfiguration.Builder` or use any other authentication methods in the `Authenticator`. This is because we don't want anyone to accidentally invalidate/delete the token which is being used to authenticate users in a production application. You will still be able to delete the token via the web [developer console](https://developer.vimeo.com/apps/).

### Authentication
**Note**: This section does not apply if you configured the SDK with a pre-generated access token instead of client credentials.

All calls to the Vimeo API must be [authenticated](https://developer.vimeo.com/api/authentication). This means that before making requests to the API you must authenticate and obtain an access token. Two authentication methods are provided:

1. [Client credentials grant](https://developer.vimeo.com/api/authentication#generate-unauthenticated-tokens): This mechanism allows your application to access publicly accessible content on Vimeo.

2. [OAuth authorization code grant](https://developer.vimeo.com/api/authentication#generate-authenticated-tokens): This mechanism allows a Vimeo user to grant permission to your app so that it can access private, user-specific content on their behalf.

#### Client Credentials Grant
```kotlin
val vimeoApiConfiguration: VimeoApiConfiguration = ...
val authenticator: Authenticator = Authenticator(vimeoApiConfiguration)
val vimeoApiClient: VimeoApiClient = VimeoApiClient(vimeoApiConfiguration, authenticator)

authenticator.authenticateWithClientCredentials(
  vimeoCallback(
    onSuccess = {
      // Can do something with the VimeoAccount, or ignore.
      // It will be automatically stored in the AccountStore.
      // The VimeoApiClient is now ready to make requests.
    },
    onError = {
      // Handle the error.
    }
  )
)
```

#### OAuth Authorization Code Grant
1) Set up a redirect URL scheme. Choose a redirect URL for the Vimeo website to redirect to after the user logs in. For example, you could use something like `https://example.com/redirect`, where `example.com` is your domain. The default URL is `vimeo://auth`.

	The URL you use must be registered at https://developer.vimeo.com/apps. After that, you need to configure the SDK to use your URL by using the `withCodeGrantRedirectUrl(String)` function on the `VimeoApiConfiguration.Builder`.

2) Open the authorization URL in the web browser:
```kotlin
// Create the URI to log in with some request code you can verify.
val loginUri = createCodeGrantAuthorizationUri(requestCode = "1234567")

// Open the URI in a browser (Android shown here).
val intent = Intent(Intent.ACTION_VIEW, Uri.parse(loginUri))
startActivity(intent)
```
3) The web browser will open and the user will be presented with a webpage asking them to grant access based on the `scope` that you specified in your `VimeoApiClientConfiguration` above.

4) Add an intent filter to your activity in the `AndroidManifest` to listen for the deeplink.
```xml
<intent-filter>
  <action android:name="android.intent.action.VIEW"/>
  <category android:name="android.intent.category.DEFAULT"/>
  <category android:name="android.intent.category.BROWSABLE"/>
  <!-- vimeo -->
  <data android:scheme="vimeo"/>
  <!-- auth -->
  <data android:host="auth"/>
</intent-filter>
```

5) Then call `Authenticator.authenticateWithCodeGrant(String, VimeoCallback)` passing in the URL retrieved from `val url = intent.data.toString()` which will be supplied from the intent filter.

**Note**: You can find an example of both strategies in [MainActivity.kt of the example app](example/src/main/java/com/vimeo/example/MainActivity.kt). You can also find more information on authentication in the authentication [README](auth#readme) or in the class documentation for [Authenticator](auth/src/main/java/com/vimeo/networking2/Authenticator.kt).

## Requests
With the SDK configured and authenticated, you’re ready to start making requests to the Vimeo API.

### Request Structure

#### Format
All requests functions are available on the `VimeoApiClient` and generally have the following common parameters:
- `uri`: Used to specify which endpoint should be hit, required.
-  `fieldFilter`: An optional comma delimited list of the fields that you need from the API. See the [API documentation on this](https://developer.vimeo.com/api/common-formats#json-filter) for more information. While passing `null` is acceptable, limiting the data to what you need is strongly encouraged as it will reduce the response times.
- `queryParams`: An optional map of key-value pairs that can be added to the query and further filter the response. See the [API documentation on common parameters](https://developer.vimeo.com/api/common-formats#working-with-parameters) for more information.
- `cacheControl`: The optional control that allows you to bypass the default caching behavior if you need to force a refresh or otherwise choose some different caching behavior for a specific request. Generally, leaving this `null` or providing `CacheControl.FORCE_NETWORK` are the only options we recommend using.
- `callback`: The callback that will be invoked when the API response is received. It calls back on the callback thread provided by retrofit. For Kotlin consumers, a convenience function `vimeoCallback` can be used to construct an instance of the `VimeoCallback` class in a more idiomatic manner. For Java consumers, you'll have to rely on creating an instance of the `VimeoCallback` directly.

Requests start as soon as the function is invoked. All request functions return a `VimeoRequest` that can be used to cancel an in-flight request.

#### Validation
Most parameter validation is handled by retrofit and should be pretty lenient. For the `uri` parameter you will see in a lot request functions, the validation is slightly more strict. The `uri` cannot be empty, cannot be blank, and cannot contain a path traversal `..`. Otherwise the Vimeo API will validate your parameters and request.

### Example Requests

#### Staff Picks
The below request will fetch the staff picks list.
```kotlin
val vimeoApiClient: VimeoApiClient = ...

vimeoApiClient.fetchVideoList(
  uri = STAFF_PICKS_VIDEO_URI,
  fieldFilter = null,
  queryParams = null,
  cacheControl = null,
  callback = vimeoCallback(
    onSuccess = {
      val videoList = it.data
      println("Staff Picks Success!")
    },
    onError = {
      println("Staff Picks Failure: ${it.message}")
    }
  )
)
```
#### Currently Authenticated User
```kotlin
val vimeoApiClient: VimeoApiClient = ...

vimeoApiClient.fetchCurrentUser(
  fieldFilter = null,
  cacheControl = null,
  callback = vimeoCallback(
    onSuccess = {
      // Update the UI with information about the current user
    },
    onError = {
      // Log the error and update the UI to tell the user there was an error
    }
  )
)
```

## FAQs
### How do I get a video that I own?
You can either request a `Video` object on its own or request a list of videos (`VideoList`).
#### Requesting a single video
To request a single video, you will need to know its URI. For this reason, requesting a single video is most helpful when you already have a copy of it and just need to refresh your local copy with the copy that exists on the Vimeo servers.
```kotlin
val uri = ...  // the video uri
val vimeoApiClient = ...

vimeoApiClient.fetchVideo(
  uri = uri,
  fieldFilter = null,
  queryParams = null,
  cacheControl = null,
  callback = vimeoCallback(
    onSuccess = {
      // Use the video.
    },
    onError = {
      // Voice the error.
    }
  )
) 
```

#### Requesting a list of videos
In order to get a list of your own videos you will need a URI from one of two ways:

As the authenticated user, just use the `/me/videos` endpoint:
```kotlin
val uri = "/me/videos" 
```
In the second approach, you must have a reference to your `User` object. You can get a list of videos a users owns as follows:
```kotlin
val user = ... // Get a reference to the User object
val uri = user.metadata?.connections?.videos?.uri
```

Then you can use the URI to get a list of videos
```kotlin
val uri = ... // Obtained using one of the above methods
val vimeoApiClient = ...

vimeoApiClient.fetchVideoList(
  uri = uri,
  fieldFilters = null,
  queryParams = null,
  cacheControl = null,
  callback = vimeoCallback(
    onSuccess = {
      // Getting the first video in the list.
      val firstVideo = it.data.data?.firstOrNull()
    },
    onError = {
      // Voice the error.
    }
  )
)
```

### How do I play a video?
Once you have a `Video` object you have two choices - embed or play natively. All consumers of this library will be able to access the embed data. However, in order to play videos natively (for example, in a VideoView or using ExoPlayer on Android) you must have access to the `VideoFile` links, for which there are some restrictions (see the native playback section below).

#### Embed
All consumers of this library will have access to a video's embed HTML. Once you request a video you can get access to this as such:
```kotlin
val video = ... // Obtain a video as described in the requests section
val html: String? = video.embed?.html

// html is in the form "<iframe .... ></iframe>"
// display the html however you wish
```
**Note**: Android `WebView` is not officially supported by our embeddable player.

#### Native playback
The basic requirements for native playback are:

1. User must be logged in.
2. User must be the owner of the video.
3. User must be PRO or higher (or the app must have the "can access owner's video files" capability).
4. Token must have the `ScopeType.VIDEO_FILES` scope.
5. User must be the owner of the API app making the request.

If you satisfy all of the requirements, you can choose to stream the videos in any manner you wish. You can get access to DASH or HLS video files through a video's `Play` representation.
```kotlin
val video = ... // Obtain a video you own as described above
val dashLink: String? = video.play?.dash?.link
val hlsLink: String? = video.play?.hls?.link

// Use one of the links to play
```

## Found an Issue?

Please file it in the git [issue tracker](https://github.com/vimeo/vimeo-networking-java/issues).

## Want to Contribute?

If you'd like to contribute, please follow our guidelines found in [CONTRIBUTING.md](CONTRIBUTING.md).

## License

`vimeo-networking-java` is available under the MIT license. See the [LICENSE](LICENSE) file for more info.

## Questions?

Tweet at us here: [@VimeoEng](https://twitter.com/VimeoEng).

Post on [Stackoverflow](http://stackoverflow.com/questions/tagged/vimeo-android) with the tag `vimeo-android`.

Get in touch [here](https://vimeo.com/help/contact).

Interested in working at Vimeo? We're [hiring](https://vimeo.com/jobs)!
