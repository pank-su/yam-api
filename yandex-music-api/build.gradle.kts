plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxJson)
    id("module.publication")
}

kotlin {
    kotlin.applyDefaultHierarchyTemplate()
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()
    //linuxX64()

    sourceSets {
        androidMain {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
//        iosMain {
//            dependencies {
//                implementation(libs.ktor.client.darwin)
//            }
//        }
        jvmMain {
            dependencies {
                implementation(libs.ktor.client.java)
            }
        }
//        linuxMain {
//            dependencies {
//                implementation(libs.ktor.client.curl)
//            }
//        }
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

                implementation("org.kotlincrypto.macs:hmac-sha2:0.5.1")
            }
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.coroutines.test)
                implementation(libs.getenv)
                implementation(libs.io)
            }
        }
    }
}

android {
    namespace = "su.pank.yandex.music.api"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

    }

}

