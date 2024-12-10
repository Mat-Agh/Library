import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(
        libs.plugins.kotlinMultiplatformPlugin
    )

    alias(
        libs.plugins.androidApplicationPlugin
    )

    alias(
        libs.plugins.composeMultiplatformPlugin
    )

    alias(
        libs.plugins.composeCompilerPlugin
    )

    alias(
        libs.plugins.kotlinSerializationPlugin
    )

    alias(
        libs.plugins.kspPlugin
    )

    alias(
        libs.plugins.roomPlugin
    )
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(
                JvmTarget.JVM_21
            )
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = libs.versions.desktopBasePackageName.get()
            isStatic = true
        }
    }

    jvm(
        libs.versions.desktopJvmName.get()
    )

    room {
        schemaDirectory(
            "$projectDir/schemas"
        )
    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(
                libs.activityCompose
            )

            implementation(
                libs.koinAndroid
            )

            implementation(
                libs.koinAndroidxCompose
            )

            implementation(
                libs.ktorClientOkhttp
            )

            implementation(
                compose.preview
            )
        }

        commonMain.dependencies {
            implementation(
                libs.kotlinxSerializationJson
            )

            implementation(
                libs.koinCompose
            )

            implementation(
                libs.koinComposeViewmodel
            )

            implementation(
                libs.koinCore
            )

            implementation(
                libs.bundles.ktor
            )

            implementation(
                libs.sqlite
            )

            implementation(
                libs.roomRuntime
            )

            implementation(
                libs.lifecycleViewModel
            )

            implementation(
                libs.lifecycleRuntimeCompose
            )

            implementation(
                compose.runtime
            )

            implementation(
                compose.foundation
            )

            implementation(
                compose.animation
            )

            implementation(
                compose.material3
            )

            implementation(
                compose.ui
            )

            implementation(
                compose.components.resources
            )

            implementation(
                compose.components.uiToolingPreview
            )

            implementation(
                libs.navigation
            )

            implementation(
                libs.bundles.coil
            )
        }

        desktopMain.dependencies {
            implementation(
                compose.desktop.currentOs
            )

            implementation(
                libs.coroutinesSwing
            )

            implementation(
                libs.ktorClientOkhttp
            )
        }

        nativeMain.dependencies {
            implementation(
                libs.ktorClientDarwin
            )
        }

        dependencies {
            ksp(
                libs.roomCompiler
            )
        }
    }
}

android {
    namespace = libs.versions.projectNameSpace.get()

    compileSdk = libs.versions.androidCompileSdkVersion.get().toInt()

    defaultConfig {
        applicationId = libs.versions.projectNameSpace.get()
        minSdk = libs.versions.androidMinimumSdkVersion.get().toInt()
        targetSdk = libs.versions.androidTargetSdkVersion.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
    }

    packaging {
        resources {
            excludes += libs.versions.packagingExclusionSet.get()
        }
    }

    buildTypes {
        getByName(
            libs.versions.androidReleaseBuildName.get()
        ) {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = false
            isJniDebuggable = false
        }

        getByName(
            libs.versions.androidDebugBuildName.get()
        ) {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            isJniDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

compose.desktop {
    application {
        mainClass = libs.versions.desktopMainClass.get()

        nativeDistributions {
            targetFormats(
                TargetFormat.Dmg,
                TargetFormat.Msi,
                TargetFormat.Deb
            )

            packageName = libs.versions.projectNameSpace.get()

            packageVersion = libs.versions.versionName.get()
        }
    }
}

dependencies {
    implementation(libs.firebaseDatabaseKtx)
    debugImplementation(
        compose.uiTooling
    )
}