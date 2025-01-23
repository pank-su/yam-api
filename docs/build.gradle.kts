plugins {

    id("dokka.convention")
}



dependencies {
    dokka(projects.core)
}

// TODO: сделать нормальную генерацию документации
dokka {
    moduleName.set("yandex-music-api")



    pluginsConfiguration.html {
        
    }
}