plugins {
    alias(libs.plugins.kotlinJvm)
}

dependencies{
    implementation(projects.core)
    implementation(projects.ynison)
    implementation(libs.ktor.client.java)
    implementation("io.grpc:grpc-netty:1.76.0")
}

