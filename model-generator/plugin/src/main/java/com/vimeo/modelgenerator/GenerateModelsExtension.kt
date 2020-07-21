package com.vimeo.modelgenerator

import org.gradle.api.Project

/**
 *  Extension object that is used to configure the [GenerateModelsPlugin].
 */
open class GenerateModelsExtension(private val project: Project) {

    /**
     * A file path to the base models that will be use to generate new models.
     */
    open var inputPath: String? = null

    /**
     * The specific type of models that will be generated.
     */
    open var typeGenerated: ModelType? = null
}

/**
 * The supported types of models that can be generated.
 */
enum class ModelType {

    /**
     * Generates models that support [java.io.Serializable].
     */
    SERIALIZABLE,

    /**
     * Generates models that support [android.os.Parcelable].
     */
    PARCELABLE
}
