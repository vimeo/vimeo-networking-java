# vimeo-networking
vimeo-networking is a Java networking library used for interacting with the Vimeo API. The example provided in this project shows the implementation in the context of an Android app.

## Contents
* [Getting Started](#getting-started)
      * [Gradle](#gradle)
      * [Submodule](#submodule)
      * [Initialization](#initialization)
      * [Authentication](#authentication)
      * [Request Basics](#request-basics)
* [Requests](#requests)
     * [Callbacks](#callbacks)
     * [Cache Strategy](#cache-strategy)
     * [Sample Requests](#sample-requests)
* [Found an Issue?](#found-an-issue)
* [Questions](#questions)
* [License](#license)

## Getting Started
For a more in depth look at the usage, refer to the [example Android app](example). The example project includes implementation of all of the below features.

### Gradle
Specify the dependency in your `build.gradle` file (make sure `jcenter()` is included as a repository)
```groovy
compile 'com.vimeo.networking:vimeo-networking:1.0.0'
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
On app launch, configure `VimeoClient` with your client key, secret, and scope strings. And once initialization is complete, authenticate if necessary. This authentication can include client credentials or authentication via code grant.
```java
Configuration.Builder configBuilder =
      new Configuration.Builder(Vimeo.VIMEO_BASE_URL_STRING, clientId, clientSecret, SCOPE,
                                testAccountStore, new AndroidGsonDeserializer())
          .setCacheDirectory(this.getCacheDir())
```
Setting the cache directory and deserializer are optional but highly recommended.
* The deserializer allows for deserialization on a background thread. Without it, object deserialization will occur on the main (UI) thread. This can be bad for performance if the API response bodies are large.
* The cache directory is to allow caching of requests. Without it, no requests will be cached and all other cache settings will be ignored.

### Authentication 
All calls to the Vimeo API must be [authenticated](https://developer.vimeo.com/api/authentication). This means that before making requests to the API you must authenticate and obtain an access token. Two authentication methods are provided: 

1. [Client credentials grant](https://developer.vimeo.com/api/authentication#unauthenticated-requests): This mechanism allows your application to access publicly accessible content on Vimeo. 

2. [OAuth authorization code grant](https://developer.vimeo.com/api/authentication#generate-redirect): This mechanism allows a Vimeo user to grant permission to your app so that it can access private, user-specific content on their behalf. 

#### Client Credentials Grant
```java
// You can't make any requests to the api without an access token. This will get you a basic
// "Client Credentials" grant which will allow you to make requests
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

4) Add an intent filter to your activity in the [`AndroidManifest`](example/src/main/AndroidManifest.xml) to listen for the deeplink.
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

*You can find an example of both stategies in [MainActivity.java of the example app](example/src/main/java/com/vimeo/android/networking/example/MainActivity.java)

## Requests
With `vimeo-networking` configured and authenticated, you’re ready to start making requests to the Vimeo API.

### Standard Request Methods
You can access the below methods through `VimeoClient.getInstance()`.

For making GET requests:
```java
public Call<Object> fetchContent(String uri, CacheControl cacheControl, ModelCallback callback,
                                 @Nullable String query, @Nullable Map<String, String> refinementMap,
                                 @Nullable String fieldFilter)
```

For making POST requests:
```java
public Call<Object> postContent(String uri, CacheControl cacheControl, HashMap<String, String> postBody,
                                VimeoCallback callback)
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
          toast("Staff Picks Failure :(");
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

## Found an Issue?
Please file any and all issues found in this library to the git [issue tracker](https://github.com/vimeo/vimeo-networking-java/issues)

## Want to Contribute?
If you'd like to contribute, please follow our guidelines found in [CONTRIBUTING.md](CONTRIBUTING.md)

## Questions?

Tweet at us here: @vimeoapi

Post on [Stackoverflow](http://stackoverflow.com/questions/tagged/vimeo-android) with the tag `vimeo-android`

Get in touch [here](https://Vimeo.com/help/contact)

## License

`vimeo-networking-java` is available under the MIT license. See the LICENSE file for more info.
