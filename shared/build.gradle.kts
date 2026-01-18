import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinCompose)
    `maven-publish`
}

group = "com.github.Asutosh11"
version = "1.0.12"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
        }
        publishLibraryVariants("release", "debug")
    }

    jvm {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
        }
    }

    // Use XCFramework() from the official plugin
    val xcf = XCFramework()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "BingoSDK"
            isStatic = true

            // Add this framework to XCFramework
            xcf.add(this)

            // Export main library classes
            export("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

            // Configure framework for library usage
            freeCompilerArgs += listOf(
                "-Xexport-kdoc"
            )
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)

            // Export coroutines for iOS
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

            // Compose Multiplatform
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.androidx.lifecycle.runtime)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.core.ktx)
            implementation(compose.uiTooling)
        }

        jvmMain.dependencies {
            implementation("io.ktor:ktor-client-cio:2.3.12")
            implementation(libs.slf4j.simple)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.example.bingo"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
        targetSdk = 35

        // Library configuration
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // Disable lint to avoid Kotlin 2.2.0 compatibility issues on JitPack
    lint {
        abortOnError = false
        checkReleaseBuilds = false
        checkDependencies = false
        ignoreWarnings = true
        quiet = true
    }

    // Enable library publishing
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

// Task to create ZIP file for the XCFramework
tasks.register<Zip>("zipReleaseXCFramework") {
    group = "build"
    description = "Creates a ZIP file of the Release XCFramework"

    dependsOn("assembleSharedReleaseXCFramework")

    from(layout.buildDirectory.dir("XCFrameworks/release"))
    include("BingoSDK.xcframework/**")
    archiveFileName.set("BingoSDK.xcframework.zip")
    destinationDirectory.set(layout.buildDirectory.dir("distributions"))

    doLast {
        val zipFile = layout.buildDirectory.file("distributions/BingoSDK.xcframework.zip").get().asFile
        println("✓ XCFramework ZIP created at: ${zipFile.absolutePath}")
        println("  Size: ${zipFile.length() / 1024 / 1024} MB")
    }
}

// Task to create ZIP file for Debug XCFramework
tasks.register<Zip>("zipDebugXCFramework") {
    group = "build"
    description = "Creates a ZIP file of the Debug XCFramework"

    dependsOn("assembleSharedDebugXCFramework")

    from(layout.buildDirectory.dir("XCFrameworks/debug"))
    include("BingoSDK.xcframework/**")
    archiveFileName.set("BingoSDK-debug.xcframework.zip")
    destinationDirectory.set(layout.buildDirectory.dir("distributions"))

    doLast {
        val zipFile = layout.buildDirectory.file("distributions/BingoSDK-debug.xcframework.zip").get().asFile
        println("✓ Debug XCFramework ZIP created at: ${zipFile.absolutePath}")
    }
}

// Convenience task to build and zip release XCFramework
tasks.register("buildAndZipXCFramework") {
    group = "build"
    description = "Builds and zips the Release XCFramework for distribution"
    dependsOn("zipReleaseXCFramework")

    doLast {
        println("========================================")
        println("  BingoSDK XCFramework Build Complete")
        println("========================================")
        println("Release XCFramework: build/XCFrameworks/release/BingoSDK.xcframework")
        println("ZIP file: build/distributions/BingoSDK.xcframework.zip")
        println("")
        println("Ready for distribution!")
    }
}

// Configure publishing
publishing {
    publications {
        withType<MavenPublication> {
            groupId = "com.github.Asutosh11"
            artifactId = "bingo-sdk"
            version = "1.0.12"

            pom {
                name.set("Bingo SDK")
                description.set("Kotlin Multiplatform library for address management with API integration")
                url.set("https://github.com/Asutosh11/Bingo")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("Asutosh11")
                        name.set("Asutosh Panda")
                        email.set("your.email@example.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/Asutosh11/Bingo.git")
                    developerConnection.set("scm:git:ssh://github.com/Asutosh11/Bingo.git")
                    url.set("https://github.com/Asutosh11/Bingo")
                }
            }
        }
    }
}

// iOS Framework export task
tasks.register("exportIOSFramework") {
    notCompatibleWithConfigurationCache("Utility task for local builds; prints paths and depends on link tasks")
    dependsOn("linkDebugFrameworkIosSimulatorArm64")
    dependsOn("linkReleaseFrameworkIosArm64")
    dependsOn("linkReleaseFrameworkIosX64")

    doLast {
        println("iOS frameworks built successfully!")
        println("Debug framework (Simulator): shared/build/bin/iosSimulatorArm64/debugFramework/BingoSDK.framework")
        println("Release framework (Device): shared/build/bin/iosArm64/releaseFramework/BingoSDK.framework")
        println("Release framework (Intel Mac): shared/build/bin/iosX64/releaseFramework/BingoSDK.framework")
    }
}

// Disable all lint tasks to avoid Kotlin 2.2.0 compatibility issues on JitPack
tasks.whenTaskAdded {
    if (name.contains("lint", ignoreCase = true)) {
        enabled = false
    }
}

// Task to run the SimpleMain class
tasks.register<JavaExec>("runAddressApiExample") {
    dependsOn("jvmMainClasses")
    group = "application"
    description = "Run the Address API Example"
    classpath = kotlin.targets["jvm"].compilations["main"].output.allOutputs +
            kotlin.targets["jvm"].compilations["main"].compileDependencyFiles
    mainClass.set("com.example.bingo.SimpleMainKt")
}
