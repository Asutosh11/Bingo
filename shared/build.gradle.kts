import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
}

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
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
