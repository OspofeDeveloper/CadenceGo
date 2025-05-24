
import com.example.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies

class KoinConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        dependencies {
            val boom = libs.findLibrary("koin-bom").get()
            "implementation"(platform(boom))
            "implementation"(libs.findLibrary("koin-core").get())
        }

        pluginManager.withPlugin("com.android.base") {
            dependencies {
                "implementation"(libs.findLibrary("koin-android").get())
            }
        }

        pluginManager.withPlugin("org.jetbrains.kotlin.plugin.compose") {
            dependencies {
                "implementation"(libs.findLibrary("koin-androidx-compose").get())
                "implementation"(libs.findLibrary("koin-androidx-compose-navigation").get())
            }
        }

        pluginManager.withPlugin("androidx.work") {
            dependencies {
                "implementation"(libs.findLibrary("koin-workmanager").get())
            }
        }

        pluginManager.withPlugin("io.ktor.plugin") {
            dependencies {
                "implementation"(libs.findLibrary("koin-ktor").get())
                "implementation"(libs.findLibrary("koin-logger-slf4j").get())
            }
        }
    }

}