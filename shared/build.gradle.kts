import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    `maven-publish`
}

group = "com.example.bingo"
version = "1.0.0"

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
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
        publishLibraryVariants("release", "debug")
    }
    
    jvm {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "BingoSDK"
            isStatic = true
            
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
        }
        
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
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
        
        // Library configuration
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    
    // Enable library publishing
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

// Configure publishing
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.example.bingo"
            artifactId = "bingo-sdk"
            version = "1.0.0"
            
            pom {
                name.set("Bingo SDK")
                description.set("Kotlin Multiplatform library for address management with API integration")
                url.set("https://github.com/yourusername/bingo")
                
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                
                developers {
                    developer {
                        id.set("developer")
                        name.set("Developer Name")
                        email.set("developer@example.com")
                    }
                }
            }
        }
    }
}

// iOS Framework export task
tasks.register("exportIOSFramework") {
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

// Task to run the SimpleMain class
tasks.register<JavaExec>("runAddressApiExample") {
    dependsOn("jvmMainClasses")
    group = "application"
    description = "Run the Address API Example"
    classpath = kotlin.targets["jvm"].compilations["main"].output.allOutputs +
                kotlin.targets["jvm"].compilations["main"].compileDependencyFiles
    mainClass.set("com.example.bingo.SimpleMainKt")
}
