import com.example.customplugin.ModuleConfigurationPlugin

plugins {
    `android-library`
    `kotlin-android`
    `kotlin-kapt`
    id("com.google.dagger.hilt.android")
}

apply<ModuleConfigurationPlugin>()

@Suppress("UnstableApiUsage")
android {
    namespace = "com.example.dashboard"

    compileSdk = ProjectConfig.compileSdk
    buildFeatures {
        compose = true
    }

    buildFeatures {
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":network"))
    implementation(project(":test-utils"))

    api(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)
    api("androidx.compose.ui:ui")
    api("androidx.compose.foundation:foundation")
    api("androidx.activity:activity-compose")
    api("androidx.compose.material:material")
    api("androidx.compose.ui:ui-tooling-preview")

    api(libs.androidx.appcompat)
    api(libs.android.material)
    api(libs.androidx.constraintlayout)
    api(libs.androidx.core.testing)
    testImplementation(libs.io.mockk)
}