plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("android")
}

val libs = extensions.getByType<VersionCatalogsExtension>()
kotlin {
    // Автоматическая настройка иерархии source sets
    applyDefaultHierarchyTemplate()

    jvm()
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8
        }
    }

    linuxX64()

    js {
        browser()
        nodejs() //
    }

    wasmJs {
        browser()
        nodejs()
    }
}

