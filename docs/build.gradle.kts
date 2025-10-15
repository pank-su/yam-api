plugins {
    alias(libs.plugins.dokka)
}



dependencies {
    dokka(projects.core)
}

dokka {
    moduleName.set("yam-api")

    pluginsConfiguration.html {
        customAssets.from("../images/logo-icon.svg")
        customStyleSheets.from("logo-styles.css")
    }
}