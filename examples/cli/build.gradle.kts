plugins {
    alias(libs.plugins.kotlinJvm)
    id("application")
}


dependencies{
    implementation(projects.core)
    implementation(libs.clikt)
    implementation(libs.ktor.client.java)
}