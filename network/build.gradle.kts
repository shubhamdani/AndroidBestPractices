plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.library")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.network"
    compileSdk = CommonConfiguration.compileSdk
}

dependencies {
    implementation(project(":common"))
    api(libs.okhttp)
    api(libs.okhttp.logging.interceptor)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)
}
