rootProject.name = "Library"

include(
    ":composeApp"
)

enableFeaturePreview(
    "TYPESAFE_PROJECT_ACCESSORS"
)

pluginManagement {
    repositories {
        google {
            mavenContent {
                @Suppress("UnstableApiUsage")
                includeGroupAndSubgroups(
                    "androidx"
                )

                @Suppress("UnstableApiUsage")
                includeGroupAndSubgroups(
                    "com.android"
                )

                @Suppress("UnstableApiUsage")
                includeGroupAndSubgroups(
                    "com.google"
                )
            }
        }

        mavenCentral()

        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups(
                    "androidx"
                )

                includeGroupAndSubgroups(
                    "com.android"
                )

                includeGroupAndSubgroups(
                    "com.google"
                )
            }
        }

        mavenCentral()
    }
}

