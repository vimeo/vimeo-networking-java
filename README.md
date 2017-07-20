# vimeo-networking
vimeo-networking is a Java networking library used for interacting with the Vimeo API. The 
provided in this project shows the implementation in the context of an Android app.

| Branch | Build Status |
|--------|--------------|
| master | [![Build Status](https://travis-ci.org/vimeo/vimeo-networking-java.svg?branch=master)](https://travis-ci.org/vimeo/vimeo-networking-java) |
| v2.0.0 | [![Build Status](https://travis-ci.org/vimeo/vimeo-networking-java.svg?branch=v2.0.0)](https://travis-ci.org/vimeo/vimeo-networking-java) |

| Latest Version |
|----------------|
| [![Download](https://api.bintray.com/packages/vimeo/maven/vimeo-networking/images/download.svg)](https://bintray.com/vimeo/maven/vimeo-networking/_latestVersion) |

## Contents
* [Prerequisites](#prerequisites)
     * [API Registration](#api-registration)
* [Getting Started](#getting-started)
     * [Gradle](#gradle)
     * [Submodule](#submodule)
     * [Initialization](#initialization)
     * [Authentication](#authentication)
* [Requests](#requests)
     * [Callbacks](#callbacks)
     * [Cache Strategy](#cache-strategy)
     * [Sample Requests](#sample-requests)
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
For a more in depth look at the usage, refer to the [example Android app](example-java-android). The example project includes implementation of all of the below features.

### Gradle
Specify the dependency in your `build.gradle` file (make sure `jcenter()` is included as a repository)
```groovy
compile 'com.vimeo.networking:vimeo-networking:1.1.0'
```

### Submodule
We recommend using JCenter, but if you'd like to use the library as a submodule:
```
git submodule add git@github.com:vimeo/vimeo-networking-java.git
```
Then in your `build.gradle` use:
```groovy
compile project(':vimeo-networking-java:vimeo-networking')
```

### Initialization

Access to this API is done through the `VimeoClient` class, which must be initialized prior to use, or rather, before the first call to `VimeoClient.getInstance()`. To initialize `VimeoClient`, you must call `VimeoClient.initialize(Configration)` passing it a `Configuration` object, which is highly customizable through the use of the `Configuration.Builder` class. There are a few different default `Builder` constructors that cover core functionality.
In the below sections, we cover examples of ways to customize your `VimeoClient` instance. Full implementations are in the example app.

#### Configuration Builder for Apps with Account Management
On app launch, configure `VimeoClient` with your client key, secret, and scope strings. And once initialization is complete, authenticate if necessary. This authentication can include [client credentials](#client-credentials-grant) or authentication via [code grant](#oauth-authorization-code-grant).

If you choose to pass in an `AccountStore`, the authenticated account will automatically be persisted. Once authentication is complete, you can access the persisted `VimeoAccount` object through `VimeoClient#getVimeoAccount()`. For a basic implementation of an `AccountStore`, please refer to [this file in the example app](example-java-android/src/main/java/com/vimeo/android/networking/example/vimeonetworking/TestAccountStore.java)
```java
  /**
    * @param clientId      The client id provided to you from <a href="https://developer.vimeo.com/apps/">the developer console</a>
    * @param clientSecret  The client secret provided to you from <a href="https://developer.vimeo.com/apps/">the developer console</a>
    * @param scope         Space separated list of <a href="https://developer.vimeo.com/api/authentication#scopes">scopes</a>
    *                      <p/>
    *                      Example: "private public create"
    * @param accountStore  (Optional, Recommended) An implementation that can be used to interface with Androids <a href="http://developer.android.com/reference/android/accounts/AccountManager.html">Account Manager</a>
    * @param deserializer  (Optional, Recommended) Extend GsonDeserializer to allow for deserialization on a background thread
    */
Configuration.Builder configBuilder =
      new Configuration.Builder(clientId, clientSecret, SCOPE,
                                testAccountStore, new AndroidGsonDeserializer())
          .setCacheDirectory(this.getCacheDir())
```
Setting the cache directory and deserializer are optional but highly recommended.
* The deserializer allows for deserialization on a background thread. Without it, object deserialization will occur on the main (UI) thread. This can be bad for performance if the API response bodies are large.
* The cache directory is to allow caching of requests. Without it, no requests will be cached and all other cache settings will be ignored.

#### Configuration Builder for Apps with Only One Developer Account
You can skip the need for [client credentials](#client-credentials-grant) or [code grant](#oauth-authorization-code-grant) requests if you provide an access token up front. With this access token you'll be able to make any requests that the access token's scope allows.
You will NOT be able to switch accounts if you only supply the access token. To do that, refer to the above section.

You can generate an access token in the Authentication tab once you select your app from the [list here](https://developer.vimeo.com/apps).

Once you have the access token, you can easily initialize your `VimeoClient` instance with the below builder.

```java
String accessToken = getString(R.string.access_token);
return new Configuration.Builder(accessToken);
```

After providing the access token, if you'd like to have access to the associated `User` object you'll need to make a call to `VimeoClient#fetchCurrentUser`. If you're using an account store, you can update the `VimeoAccount` with the new `User` object.

*Note: You will not be able to log out of the account associated with the access token provided to the `Configuration.Builder`. This is because we wouldn't want anyone to accidentally invalidate/delete the token which is being used to authenticate users in a production application. You will still be able to delete the token via the web [developer console](https://developer.vimeo.com/apps/).
If this seems like restricting functionality, please log an issue to the [issue tracker](https://github.com/vimeo/vimeo-networking-java/issues).

### Authentication
All calls to the Vimeo API must be [authenticated](https://developer.vimeo.com/api/authentication). This means that before making requests to the API you must authenticate and obtain an access token. Two authentication methods are provided:

1. [Client credentials grant](https://developer.vimeo.com/api/authentication#generate-unauthenticated-tokens): This mechanism allows your application to access publicly accessible content on Vimeo.

2. [OAuth authorization code grant](https://developer.vimeo.com/api/authentication#generate-authenticated-tokens): This mechanism allows a Vimeo user to grant permission to your app so that it can access private, user-specific content on their behalf.

#### Client Credentials Grant
```java
// You can't make any requests to the api without an access token. This will get you a basic
// "Client Credentials" grant which will allow you to make requests. This requires a client id and client secret.
private void authenticateWithClientCredentials() {
    VimeoClient.getInstance().authorizeWithClientCredentialsGrant(new AuthCallback() {
        @Override
        public void success() {
           String accessToken = VimeoClient.getInstance().getVimeoAccount().getAccessToken();
           toast("Client Credentials Authorization Success with Access Token: " + accessToken);
        }

        @Override
        public void failure(VimeoError error) {
           String errorMessage = error.getDeveloperMessage();
           toast("Client Credentials Authorization Failure: " + errorMessage);
        }
    });
}
```

#### OAuth Authorization Code Grant
1) Set up a redirect url scheme:
Navigate to your app target settings > Info > URL Types.  Add a new URL Type, and under url scheme enter `vimeo{CLIENT_KEY}` (ex: if your CLIENT_KEY is `1234`, enter `vimeo1234`).  This allows Vimeo to redirect back into your app after authorization.

You also need to add this redirect URL to your app on the Vimeo API site.  Under “App Callback URL”, add `vimeo{CLIENT_KEY}://auth` (for the example above, `vimeo1234://auth`).

2) Open the authorization URL in the web browser:
```java
private void onLoginClick() {
     // Go To Web For Code Grant Auth
     String uri = VimeoClient.getInstance().getCodeGrantAuthorizationURI();
     Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
     startActivity(browserIntent);
}
```
3) The web browser will open and the user will be presented with a webpage asking them to grant access based on the `scope` that you specified in your `ClientBuilder` above.

4) Add an intent filter to your activity in the [`AndroidManifest`](example-java-android/src/main/AndroidManifest.xml) to listen for the deeplink.
```xml
<intent-filter>
      <action android:name="android.intent.action.VIEW"/>

      <category android:name="android.intent.category.DEFAULT"/>
      <category android:name="android.intent.category.BROWSABLE"/>

     <!-- vimeo{CLIENT_KEY} -->
      <data android:scheme="@string/deeplink_redirect_scheme"/>
      <!-- auth -->
      <data android:host="@string/deeplink_redirect_host"/>
</intent-filter>
```

5) Then call `VimeoClient#authenticateWithCodeGrant(...)` passing in the `Uri` retrieved from `Uri uri = getIntent().getData();` which will be supplied from the intent filter.

*You can find an example of both stategies in [MainActivity.java of the example app](example-java-android/src/main/java/com/vimeo/android/networking/example/MainActivity.java)

## Requests
With `vimeo-networking` configured and authenticated, you’re ready to start making requests to the Vimeo API.

### Standard Request Methods
You can access the below methods through `VimeoClient.getInstance()`.

For making GET requests:
```java
public Call<Object> fetchContent(@NotNull String uri, CacheControl cacheControl,
                                 @NotNull ModelCallback callback, @Nullable String query,
                                 @Nullable Map<String, String> refinementMap,
                                 @Nullable String fieldFilter)
```

For making POST requests:
```java
public Call<Object> postContent(@NotNull String uri, CacheControl cacheControl,
                                HashMap<String, String> postBody, @NotNull VimeoCallback callback)
```

For making PUT requests:
```java
public Call<Object> putContent(String uri, @Nullable Map<String, String> options, ModelCallback callback,
                               boolean enqueue)
```

For making DELETE requests:
```java
public Call<Object> deleteContent(String uri, @Nullable Map<String, String> options,
                                  ModelCallback callback, boolean enqueue)
```

### Callbacks
We created a layer on top of Retrofit's `Callback<T>` called `ModelCallback<T>`. This class tells our fetch, post, etc methods what type of object you'd like in your response. To use it, you'll have to pass in a `Class` object into its constructor as well as the class name as the generic.
Here is an example where our response is of the type `Video`.
```java
new ModelCallback<Video>(Video.class) {
     ...
}
```

### Cache Strategy
We offer a host of convenience methods to make simple POST, GET, and PUT requests as simple as possible. These methods can be seen in [VimeoClient.java](vimeo-networking/src/main/java/com/vimeo/networking/VimeoClient.java#L926). An example can be seen [below](#staff-picks) with a call to `fetchNetworkContent(...)`.

You can also specify your own caching stategy with the below method:
```java
Call<Object> fetchContent(String uri, CacheControl cacheControl, ModelCallback callback)
```
The method can take `CacheControl.FORCE_NETWORK` which will always skip the cache and pull directly from the API. Or it can take `CacheControl.FORCE_CACHE` which will always pull from the locally stored cache (if you specified a cache directory).

### Sample Requests
#### Staff Picks
The below request will fetch the staff picks list.
```java
VimeoClient.getInstance().fetchNetworkContent(STAFF_PICKS_VIDEO_URI, new ModelCallback<VideoList>(VideoList.class) {
     @Override
     public void success(VideoList videoList) {
          // It's good practice to always make sure that the values the API sends us aren't null
          if (videoList != null && videoList.data != null) {
               toast("Staff Picks Success!");
          }
     }

     @Override
     public void failure(VimeoError error) {
          String errorMessage = error.getDeveloperMessage();
          toast("Staff Picks Failure :( " + errorMessage);
     }
});
```
#### Currently Authenticated User
```java
VimeoClient.getInstance().fetchCurrentUser(new ModelCallback<User>(User.class) {
     @Override
     public void success(User user) {
          // Update UI with information about the current user
     }

     @Override
     public void error(VimeoError error) {
          // Log the error and update the UI to tell the user there was an error
     }
});
```

## FAQs
### How do I get a video that I own?
You can either request a ```Video``` object on it's own or request a list of videos (```VideoList```).
#### Requesting a single video
To request a single video, you will need to know its uri. For this reason, requesting a single video is most helpful when you already have a copy of it and just need to refresh your local copy with the copy that exists on the Vimeo servers.
```java
String uri = ...;// the video uri; if you have a Video, this is video.uri
VimeoClient.getInstance().fetchNetworkContent(uri, new ModelCallback<Video>(Video.class) {
     @Override
     public void success(Video video) {
          // use the video
     }

     @Override
     public void failure(VimeoError error) {
          // voice the error
     }
});
```

#### Requesting a list of videos
In order to get a list of your own videos you will need a uri from one of two ways:

As the authenticated user, just use the /me/videos endpoint:
```java
String uri = "/me/videos";
```
In the second manner, you must have a handle to your ```User``` object; you can get a list of videos a users owns as such:
```java
User user = ...; // get a handle to the User object
String uri;
if(user.getVideosConnection() != null) {
     uri = user.getVideosConnection().uri;
}
```

Then you can use the uri to get a list of videos
```java
String uri = ...; // obtained using one of the above methods
VimeoClient.getInstance().fetchNetworkContent(uri, new ModelCallback<VideoList>(VideoList.class) {
     @Override
     public void success(VideoList videoList) {
          if (videoList != null && videoList.data != null && !videoList.data.isEmpty()) {
               Video video = videoList.data.get(0); // just an example of getting the first video
          }
     }

     @Override
     public void failure(VimeoError error) {
          // voice the error
     }
});
```

### How do I play a video?
Once you have a ```Video``` object you have two choices - embed or play natively. All consumers of this library will be able to access the embed data, however, in order to play videos natively (for example, in a VideoView or using ExoPlayer on Android) you must have access to the ```VideoFile``` links - this is restricted to users with Vimeo PRO accounts accessing their own videos.
#### Embed
All consumers of this library will have access to a video's embed html. Once you request a video you can get access to this as such:
```java
Video video = ...; // obtain a video in a manner described in the Requests section
String html = video.embed != null ? video.embed.html : null;
if(html != null) {
     // html is in the form "<iframe .... ></iframe>"
     // load the html, for instance, if using a WebView on Android, you can perform the following:
     WebView webview = ...; // obtain a handle to your webview
     webview.loadData(html, "text/html", "utf-8");
}
```
#### Native playback
If you are a Vimeo PRO member, you will get access to your own videos' links; during [initialization](#initialization) of this library, you must provide the ```video_files``` scope. With these, you can choose to stream the videos in any manner you wish. You can get access to HLS and progressive streaming video files through a video's ```files``` array.
```java
Video video = ...; // obtain a video you own as described above
ArrayList<VideoFile> videoFiles = video.files;
if(videoFiles != null && !videoFiles.isEmpty()) {
     VideoFile videoFile = videoFiles.get(0); // you could sort these files by size, fps, width/height
     String link = videoFile.getLink();
     // load link
}
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
