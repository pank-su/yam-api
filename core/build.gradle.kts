@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinxJson)
    alias(libs.plugins.dokka)
    id("module.publication")
    id("kmp.all")
}


kotlin {
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



dokka {
    dokkaSourceSets.configureEach {
        includes.from("Core.md")
    }
}