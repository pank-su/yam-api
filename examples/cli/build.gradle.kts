plugins {
    alias(libs.plugins.kotlinJvm)
    id("application")
}


dependencies{
    // Libs for yam-api
    implementation(projects.core)
    implementation(libs.ktor.client.java)
    implementation(libs.ktor.client.logging)


    // Cli libs
    implementation(libs.clikt)
    implementation(libs.mordant)
    implementation(libs.mordant.coroutines)
}