
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension

class KoinConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        val libs = extensions.findByType(VersionCatalogsExtension::class.java)?.named("libs")
            ?: error("Version catalog 'libs' not found")

        dependencies.add("implementation", libs.findLibrary("koin-core").get())

        pluginManager.withPlugin("com.android.base") {
            dependencies.add("implementation", libs.findLibrary("koin-android").get())
        }

        pluginManager.withPlugin("org.jetbrains.kotlin.plugin.compose") {
            dependencies.add("implementation", libs.findLibrary("koin-androidx-compose").get())
            dependencies.add("implementation", libs.findLibrary("koin-androidx-compose-navigation").get())
        }

        pluginManager.withPlugin("androidx.work") {
            dependencies.add("implementation", libs.findLibrary("koin-workmanager").get())
        }

        pluginManager.withPlugin("io.ktor.plugin") {
            dependencies.add("implementation", libs.findLibrary("koin-ktor").get())
            dependencies.add("implementation", libs.findLibrary("koin-logger-slf4j").get())
        }
    }

}