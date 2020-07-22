# Model generator Gradle plugin

Generating different types of models to performantly serialize data.

## Why do we need this?

The Vimeo networking library targets the JVM to allow the greatest number of consumers possible, however this creates some limitations for Android consumers. Ideally Android consumers (like our internal Android app) would be able to use the `Parcelable` type to serialize/deserialize data instead of being locked to `Serializable`.

A possible solution for this would be to maintain two sets of the same models, so everyone can use what is most performant for them, but this could be tedious and error-prone. Instead, we opted to maintain a single base set of models and use that to generate `Parcelable` and `Serializable` implementations, so consumers can get what they need.

## Features

- Fully supports copying over documentation from base set to newly generated models
- Generates the following types:
	- Data classes (With either Serializable or Parcelable support)
	- Interfaces
	- Top-level functions/properties
	- Extension functions
	- Enums
	- Annotation classes



## Setup

1. Create an empty module for the models that will be generated.
2. Within the module level `build.gradle` file apply the plugin (You can use `apply` or a `plugins` block)

	```groovy
	apply plugin: 'kotlin'
	apply plugin: 'model.generator'
	```
	or

	```groovy
	plugins {
		id 'kotlin'
		id 'model.generator'
	}
	```

	NOTE:  For `Parcelable` support you will need to apply this plugin to an android library or application module.

	```groovy
	apply plugin: 'com.android.library'
	apply plugin: 'kotlin-android'
	apply plugin: 'kotlin-android-extensions'
	apply plugin: 'model.generator'
	```
	or

	```groovy
	plugins {
		id 'com.android.library'
		id 'kotlin-android'
		id 'kotlin-android-extensions'
		id 'model.generator'
	}
	```

3. Within that same `build.gradle` file add the `generated` block with an input path that points to your base models and a `ModelType` for what kind of models you would like to be generated.

	```groovy
	generated {

	    // The file path for the models that you wish to
	    // use as a base for what will be generated.
	    inputPath = "models/src/main/java/com/vimeo/mymodels"

	    // This desiginates what kind of models you want to generate.
	    // For Parcelable support the module this is applied to
	    // needs to be some sort of android module.
	    typeGenerated = ModelType.SERIALIZABLE
	}
	```
	NOTE: Both `inputPath` and `typeGenerated` are **required**.


4.  That's it! Anytime you build this new module a task will now run to generate the requested models.

You can check out the `build.gradle` files for our [`Serializable`](https://github.vimeows.com/nicholas-doglio/model-generator/blob/master/models-serializable/build.gradle.kts) and [`Parcelable`](https://github.vimeows.com/nicholas-doglio/model-generator/blob/master/models-parcelable/build.gradle) for concrete examples of how to set the plugin up.


## How does the code get generated?

The plugin works by pulling in all the given files in the input directory (passed through `inputPath`) and then converting them to `KtFiles` so the plugin can understand the content of each file. The `KtFiles` are then parsed using the [Kotlin PSI](https://jetbrains.org/intellij/sdk/docs/basics/architectural_overview/psi.html) to pull out all the important information that will be used to generate new models. Once we have all the important information we pass that all along (in addition to the requested Parcelable or Serializable changes) to `KotlinPoet` builders that will generate the new models in the src directory of the given module.


## Input -> Output

<table>
	<tbody>
		<tr>
			<td>Basic</td>
			<td>
				<pre lang="kotlin">
/**
 * Video data
 */
@JsonClass(generateAdapter = true)
data class Video(

    /**
     * The video's canonical relative URI.
     */
    @Json(name = "uri")
    val uri: String? = null
)
</pre>
			</td>
		</tr>
		<tr>
			<td>Serializable</td>
<td>
<pre lang="kotlin">
/**
 * Video data
 */
@JsonClass(generateAdapter = true)
data class Video(
  /**
   * The video's canonical relative URI.
   */
  @Json(name = "uri")
  val uri: String? = null
) : Serializable {
  companion object {
    private const val serialVersionUID: Long = 464451044
  }
}
</pre>
			</td>
		</tr>
		<tr>
			<td>Parcelable</td>
			<td>
				<pre lang="kotlin">
/**
 * Video data
 */
@JsonClass(generateAdapter = true)
@Parcelize
data class Video(
    /**
     * The video's canonical relative URI.
     */
    @Json(name = "uri")
    val uri: String? = null
) : Parcelable
</pre>
			</td>
		</tr>
	</tbody>
</table>
