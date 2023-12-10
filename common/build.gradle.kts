plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.library")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.common"
    compileSdk = CommonConfiguration.compileSdk

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    api(libs.androidx.constraintlayout)
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.lifecycle.livedata.ktx)
    api(libs.androidx.lifecycle.common.java8)
    api(libs.androidx.lifecycle.viewmodel.savedstate)
    api(libs.retrofit.jackson)

    api(libs.androidx.fragment)
    api(libs.androidx.navigation.ui.ktx)
    api(libs.androidx.navigation.fragment.ktx)

    api(libs.dagger)
    api(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)
    api(libs.androidx.core.ktx)
    api(libs.androidx.activity.ktx)

    implementation(libs.android.material)
    implementation(libs.androidx.lifecycle.process)

    testImplementation(libs.androidx.lifecycle.runtime.testing)
}