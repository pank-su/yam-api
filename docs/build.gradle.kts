plugins {
    id("dokka.convention")
}



dependencies {
    dokka(projects.core)
}

dokka {
    moduleName.set("yam-api")



    pluginsConfiguration.html {
        
    }
}