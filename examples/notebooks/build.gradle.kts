plugins {
    alias(libs.plugins.kotlinJvm)
}

dependencies{
    implementation(projects.core)
    implementation(libs.ktor.client.java)
}