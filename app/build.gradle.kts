plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}


android {
    namespace = "com.example.bestpractices"

    defaultConfig {
        applicationId = "com.example.bestpractices"
        minSdk = CommonConfiguration.minSdk
        compileSdk = CommonConfiguration.compileSdk
        targetSdk = CommonConfiguration.targetSdk
        versionCode = CommonConfiguration.versionCode
        versionName = CommonConfiguration.versionName
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":network"))
    implementation(project(":dashboard"))
    implementation(project(":test-utils"))

    api(libs.androidx.multidex)

    api(libs.androidx.navigation.fragment.ktx)
    api(libs.androidx.navigation.ui.ktx)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation(libs.androidx.ext.junit)
    api(libs.androidx.core.testing)

    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.android.material)

    api(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)

    api(libs.okhttp)
    api(libs.okhttp.logging.interceptor)

    testImplementation(libs.assertj.core)
    testImplementation(libs.junit.test)
    testImplementation(libs.io.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.coroutines.core)
}