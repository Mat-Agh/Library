plugins {
    alias(
        libs.plugins.androidApplicationPlugin
    ) apply false

    alias(
        libs.plugins.androidLibraryPlugin
    ) apply false

    alias(
        libs.plugins.composeMultiplatformPlugin
    ) apply false

    alias(
        libs.plugins.composeCompilerPlugin
    ) apply false

    alias(
        libs.plugins.kotlinMultiplatformPlugin
    ) apply false

    alias(
        libs.plugins.kspPlugin
    ) apply false

    alias(
        libs.plugins.roomPlugin
    ) apply false
}

tasks.withType<Test> {
    if (name == "mergeDebugAndroidTestAssets") {
        enabled = false
    }
}

tasks.withType<Test> {
    if (name == "copyRoomSchemasToAndroidTestAssetsDebugAndroidTest") {
        enabled = false
    }
}

tasks.whenTaskAdded {
    if (name.contains("copyRoomSchemasToAndroidTestAssetsDebugAndroidTest")) {
        enabled = false
    }
}

gradle.taskGraph.whenReady {
    allTasks.onEach { task ->
        if (task.name.contains("androidTest") || task.name.contains("connectedAndroidTest")) {
            task.enabled = false
        }
    }
}