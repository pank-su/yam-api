plugins {
    alias(libs.plugins.kotlinMultiplatform)

    id("dokka.convention")
}



dependencies {
    dokka(projects.apiCore)
}

// TODO: сделать нормальную генерацию документации
dokka {
    moduleName.set("yandex-music-api")



    pluginsConfiguration.html {
        
    }
}