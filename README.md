# vimeo-networking
vimeo-networking is a Java networking library used for interacting with the Vimeo API. The example provided in this project shows the implementation in the context of an Android app.

## Getting Started
For a more indepth look at the usage, refer to the [example Android app](example).

### Gradle
Specify the dependency in your `build.gradle` file (make sure `jcenter()` included as a repository)
```groovy
compile 'com.vimeo.networking:vimeo-networking:1.0.0'
```

### Submodule
We recommend using JCenter, but if you'd like to use the library as a submodule:
```
git submodule add git@github.com:vimeo/vimeo-networking-java.git
```
Then in your Gradle use:
```groovy
compile project(':vimeo-networking-java:vimeo-networking')
```

### Initialization
On app launch, configure `VimeoClient` with your client key, secret, and scope strings. And once initialization is complete, authenticate if necessary. This authentication can include client credentials or user authentication.
```java
Configuration.Builder configBuilder =
      new Configuration.Builder(Vimeo.VIMEO_BASE_URL_STRING, clientId, clientSecret, SCOPE,
                                testAccountStore, new AndroidGsonDeserializer())
          .setCacheDirectory(this.getCacheDir())
          .setCodeGrantRedirectUri(codeGrantRedirectUri);
```
We highly recommend providing a deserializer, cache directory, and code grant redirect uri.
* The deserializer allows for deserialization a background thread.
* The cache directory is to allow caching of requests.
* The grant redirect uri allows you to utilize our [Code Grant Auth](https://developer.vimeo.com/api/authentication#generate-redirect) flow. 
  * For Android apps, this will most likely be a deeplink back to your application.


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
            toast("Client Credentials Authorization Success");
        }

        @Override
        public void failure(VimeoError error) {
            toast("Client Credentials Authorization Failure");
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
String uri = VimeoClient.getInstance().getCodeGrantAuthorizationURI();
Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
startActivity(browserIntent);
```
3) The web browswer will open and the user will be presented with a webpage asking them to grant access based on the `scope` that you specified in your `ClientBuilder` above.  

4) Add an intent filter to listen for the deeplink and then call `VimeoClient#authenticateWithCodeGrant(...)` passing in the `Uri` retrieved from `Uri uri = getIntent().getData();`

### Sample Request For Staff Picks
```java
  VimeoClient.getInstance().fetchNetworkContent(STAFF_PICKS_VIDEO_URI, new ModelCallback<VideoList>(VideoList.class) {
      @Override
      public void success(VideoList videoList) {
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
