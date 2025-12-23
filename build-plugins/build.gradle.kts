plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.nexus.publish)
    implementation(libs.kmp.gradle.plugin)
    implementation(libs.android.gradle.plugin)

}