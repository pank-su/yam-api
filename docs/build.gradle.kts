plugins {
    id("dokka.convention")
}



dependencies {
    dokka(projects.core)
}

// TODO: сделать нормальную генерацию документации
dokka {
    moduleName.set("yam-api")



    pluginsConfiguration.html {
        
    }
}