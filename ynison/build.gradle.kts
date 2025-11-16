@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxJson)
    alias(libs.plugins.dokka)
    alias(libs.plugins.grpc)
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
        androidUnitTest {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
        jvmTest {
            dependencies {
                implementation(libs.ktor.client.java)
            }
        }
        linuxX64Test {
            dependencies {
                implementation(libs.ktor.client.curl)
            }
        }
        webTest{
            dependencies {
                implementation(libs.ktor.client.js)
            }
        }
        commonMain {
            dependencies {
                implementation(projects.core)

                implementation(libs.ktor.client)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.content.neogation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.coroutines)
                implementation(libs.logger)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.datetime)
                implementation(libs.xmlutil)
                implementation(libs.md5)
                kotlin("reflect")

                implementation("org.kotlincrypto.macs:hmac-sha2:0.6.0")
            }
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.ktor.client.mock)
                implementation(libs.coroutines.test)
                implementation(libs.getenv)
                implementation(libs.io)
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


kmpGrpc {
    // declare the targets you need.
    common()
    jvm()
    android()
    js()
    wasmjs()
    native()

    // Optional: if the protobuf well known types should be included
    // https://protobuf.dev/reference/protobuf/google.protobuf/
    includeWellKnownTypes = true

    // Optional: if all generated source files should have 'internal' visibility.
    internalVisibility = true

    // Specify the folders where your proto files are located, you can list multiple.
    protoSourceFolders = project.files("proto")
}