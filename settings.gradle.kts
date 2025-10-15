enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "yam-api"



pluginManagement {
    includeBuild("convention-plugins")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}


include(":core")
include("rotor")
include(":docs")

include(":examples:cli")


