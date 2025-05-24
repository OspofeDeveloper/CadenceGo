import com.android.build.api.dsl.LibraryExtension
import com.example.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project>{

    override fun apply(project: Project) = with(project) {
        apply(plugin = "com.android.library")
        apply(plugin = "org.jetbrains.kotlin.android")

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
            defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            // All resources have to be prefixed with the library name to avoid name collisions
            // Example: "core:module1" must be prefixed with "core_module1_
            resourcePrefix =
                path.split("""\W""".toRegex()).drop(1).distinct().joinToString(separator = "_")
                    .lowercase() + "_"
        }
    }
}