package com.vimeo.modelgenerator.tasks

import com.vimeo.modelgenerator.ModelFactory
import com.vimeo.modelgenerator.ModelType
import com.vimeo.modelgenerator.visitor.ParcelableClassVisitor
import com.vimeo.modelgenerator.visitor.ParcelableInterfaceVisitor
import com.vimeo.modelgenerator.visitor.SerializableClassVisitor
import com.vimeo.modelgenerator.visitor.SerializableInterfaceVisitor
import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.messages.MessageRenderer.PLAIN_RELATIVE_PATHS
import org.jetbrains.kotlin.cli.common.messages.PrintingMessageCollector
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.com.intellij.openapi.project.Project
import org.jetbrains.kotlin.com.intellij.openapi.util.Disposer
import org.jetbrains.kotlin.com.intellij.psi.PsiManager
import org.jetbrains.kotlin.com.intellij.testFramework.LightVirtualFile
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.psi.KtFile
import java.io.File


open class GenerateModelsTask : DefaultTask() {

    @get:Input
    lateinit var inputModelPath: String

    @get:Input
    lateinit var typeToGenerate: ModelType

    // Path to regular models, could be passed in through plugin
    private val modelPath: String
        get() = "${project.rootDir}/${inputModelPath}"

    // Output directory path for where the new models will be generated
    private val output =
        project.extensions.getByType(JavaPluginExtension::class.java).sourceSets.maybeCreate("main").java.srcDirs.first().path

    // A File tree of all the Files from the original model directory
    val models: ConfigurableFileTree
        @InputDirectory
        @PathSensitive(PathSensitivity.RELATIVE)
        get() = project.fileTree(modelPath)

    @get:OutputDirectory
    val outputDir = File(output)
    
    private val kotlinProject: Project by lazy {
        KotlinCoreEnvironment.createForProduction(
            Disposer.newDisposable(),
            CompilerConfiguration().apply {
                put(CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY, PrintingMessageCollector(System.err, PLAIN_RELATIVE_PATHS, false))
            },
            EnvironmentConfigFiles.JVM_CONFIG_FILES
        ).project
    }

    @TaskAction
    fun execute() {
        // a visitor that will be used to modify classes
        val classVisitor = when (typeToGenerate) {
            ModelType.SERIALIZABLE -> SerializableClassVisitor()
            ModelType.PARCELABLE -> ParcelableClassVisitor()
        }

        // a visitor that will be used to modify interfaces
        val interfaceVisitor = when (typeToGenerate) {
            ModelType.SERIALIZABLE -> SerializableInterfaceVisitor()
            ModelType.PARCELABLE -> ParcelableInterfaceVisitor()
        }

        // A class designed to build the new models
        val modelFactory = ModelFactory(output, classVisitor, interfaceVisitor)

        models.files
            // Convert regular Java `File` to a KtFile
            .map { createKtFile(it.readText(), it.path) }
            // Generating the new models in the given directory
            .map { modelFactory.buildModel(it) }
    }

    private fun createKtFile(codeString: String, fileName: String) =
        PsiManager.getInstance(kotlinProject)
            .findFile(
                LightVirtualFile(fileName, KotlinFileType.INSTANCE, codeString)
            ) as KtFile
}
