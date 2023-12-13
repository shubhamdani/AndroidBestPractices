package com.example.customplugin

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class ModuleConfigurationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.applyPlugins()
        project.setAndroidConfig()
        project.setKotlinConfig()
    }

    private fun Project.applyPlugins() = this.apply {
        plugin("android-library")
        plugin("kotlin-android")
        plugin("kotlin-kapt")
        plugin("kotlin-parcelize")
    }

    private fun Project.setAndroidConfig() =
        android().apply {
            defaultConfig {
                minSdk = ProjectConfig.minSdk
                compileSdk = ProjectConfig.compileSdk
                targetSdk = ProjectConfig.targetSdk
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                multiDexEnabled = true
            }

            buildFeatures {
                viewBinding = true
            }

            buildTypes {
                release {
                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
        }


    private fun Project.setKotlinConfig() = kotlin().apply {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of("13"))
        }

    }

    private fun Project.android(): LibraryExtension =
        extensions.getByType(LibraryExtension::class.java)

    private fun Project.kotlin(): KotlinAndroidProjectExtension =
        extensions.getByType(KotlinAndroidProjectExtension::class.java)
}
