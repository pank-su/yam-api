@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxJson)
    alias(libs.plugins.dokka)
    id("module.publication")
}




kotlin {

    compilerOptions {
        freeCompilerArgs.set(listOf("-Xcontext-parameters"))

    }
    kotlin.applyDefaultHierarchyTemplate()
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget = JvmTarget.JVM_1_8
        }

    }


    linuxX64()

    js{
        browser()
        nodejs()
    }
    wasmJs{
        browser()
        nodejs()
    }

    sourceSets {

        commonMain {
            dependencies {
                implementation(projects.core)
                implementation(libs.kotlinx.serialization.json)
            }
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.ktor.client.mock)
                implementation(libs.coroutines.test)
            }
        }
    }
}

android {
    namespace = "su.pank.yamapi"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

}


dokka {
    dokkaSourceSets.configureEach {
//        includes.from("Rotor.md")
    }
}
