import com.example.customplugin.ModuleConfigurationPlugin

plugins {
    `android-library`
    `kotlin-android`
    `kotlin-kapt`
}

apply<ModuleConfigurationPlugin>()

android {
    namespace = "com.example.network"
    compileSdk = ProjectConfig.compileSdk
}

dependencies {
    implementation(project(":common"))
    api(libs.okhttp)
    api(libs.okhttp.logging.interceptor)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    commonTesting()
}

fun Project.commonTesting() {
    dependencies {
        androidTestImplementation(libs.hilt.android.testing)
        kaptAndroidTest(libs.hilt.android.compiler)
        testImplementation(libs.io.mockk)
    }
}