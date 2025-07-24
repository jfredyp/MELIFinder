// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.6.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
    id("com.google.gms.google-services") version "4.4.3" apply false
}
subprojects {
    plugins.withId("com.android.library") {
        apply(plugin = "org.jlleitschuh.gradle.ktlint")
        apply(plugin = "io.gitlab.arturbosch.detekt")

        tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
            jvmTarget = "17"
        }
        tasks.register("preChecks") {
            group = "verification"
            description = "Corre ktlintFormat antes de preBuild"
            dependsOn("ktlintFormat","ktlintCheck", "detekt")
        }

        tasks.matching { it.name == "preBuild" }.configureEach {
            dependsOn("preChecks")
        }
    }

    plugins.withId("com.android.application") {
        apply(plugin = "org.jlleitschuh.gradle.ktlint")
        apply(plugin = "io.gitlab.arturbosch.detekt")

        tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
            jvmTarget = "17"
        }
    }
}