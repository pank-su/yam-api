plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.nexus.publish)
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:2.0.0-Beta")

}