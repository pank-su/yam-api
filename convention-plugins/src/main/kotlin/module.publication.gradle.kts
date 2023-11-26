plugins {
    `maven-publish`
    signing
}

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })

        // Provide artifacts information required by Maven Central
        pom {
            name.set("Yandex Music Api")
            description.set("Multiplatform library for work with yandex music")
            url.set("https://github.com/pank-su/yandex-music-api")

            licenses {
                license {
                    name.set("GNU GPL 3.0")
                    url.set("https://opensource.org/license/gpl-3-0/")
                }
            }
            developers {
                developer {
                    id.set("pank_su")
                    name.set("pank_su")
                }
            }
            scm {
                url.set("https://github.com/Kotlin/multiplatform-library-template")
            }
        }
    }
}

signing {
    if (project.hasProperty("signing.gnupg.keyName")) {
        useGpgCmd()
        sign(publishing.publications)
    }
}
