plugins {
    alias(libs.plugins.dokka)
}



dependencies {
    dokka(projects.core)
}

dokka {
    moduleName.set("yam-api")

    pluginsConfiguration.html {
        customAssets.from("../images/logo.png")
        customStyleSheets.from("logo-styles.css")
    }
}