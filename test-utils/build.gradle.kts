import com.example.customplugin.ModuleConfigurationPlugin

plugins {
    `android-library`
    `kotlin-android`
}

apply<ModuleConfigurationPlugin>()

android {
    namespace = "com.example.test_utils"
    compileSdk = ProjectConfig.compileSdk
}

dependencies {
    api("androidx.core:core-ktx:1.7.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    api(libs.androidx.core.testing)
    api(libs.kotlinx.coroutines.test)
    api(libs.kotlinx.coroutines.core)
}