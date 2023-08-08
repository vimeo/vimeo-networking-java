package com.vimeo.modelgenerator

import com.vimeo.modelgenerator.tasks.GenerateModelsTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class GenerateModelsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension =
            project.extensions.create(EXTENSION_NAME, GenerateModelsExtension::class.java, project)

        project.pluginManager.withPlugin(KOTLIN_ANDROID) {
            registerTask(project, extension)
            project.afterEvaluate {
                project.tasks.findByName(PRE_BUILD)?.dependsOn(project.tasks.findByName(GENERATE_MODELS))
            }
        }

        project.pluginManager.withPlugin(KOTLIN_JVM) {
            registerTask(project, extension)

            project.afterEvaluate {
                project.tasks.findByName(KSP_KOTLIN)?.dependsOn(project.tasks.findByName(GENERATE_MODELS))
            }

            // kaptGenerateStubsKotlin is used as the set up task instead of build
            // because kapt code generation happens before build is called and
            // we need to generate the models prior to kapt so the Moshi adapters
            // can also be generated.
            val kaptTask: Task? = project.tasks.findByName(KAPT_GENERATE_STUBS)

            // When generating to a module that doesn't use kapt this task will be null
            // in that case compileKotlin is the earliest task run when the module is being
            // built so we can attach generateModels to that instead.
            if (kaptTask != null) {
                kaptTask.dependsOn(GENERATE_MODELS)
            } else {
                project.tasks.findByName(COMPILE_KOTLIN)?.dependsOn(GENERATE_MODELS)
            }
        }
    }

    private fun registerTask(
        project: Project,
        extension: GenerateModelsExtension
    ) {
        project.tasks.register(GENERATE_MODELS, GenerateModelsTask::class.java) {
            it.typeToGenerate = extension.typeGenerated!!
            it.inputModelPath = extension.inputPath!!
        }
    }

    companion object {
        private const val KAPT_GENERATE_STUBS = "kaptGenerateStubsKotlin"
        private const val COMPILE_KOTLIN = "compileKotlin"
        private const val PRE_BUILD = "preBuild"
        private const val KSP_KOTLIN = "kspKotlin"
        private const val EXTENSION_NAME = "generated"
        private const val KOTLIN_JVM = "org.jetbrains.kotlin.jvm"
        private const val KOTLIN_ANDROID = "kotlin-android"
        private const val GENERATE_MODELS = "generateModels"
    }
}
