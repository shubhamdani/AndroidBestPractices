plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.bestpractices"

    defaultConfig {
        applicationId = "com.example.bestpractices"
        minSdk = ProjectConfig.minSdk
        compileSdk = ProjectConfig.compileSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }
    buildFeatures {
        compose = true
    }

    buildFeatures {
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
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
        sourceCompatibility = JavaVersion.VERSION_13
        targetCompatibility = JavaVersion.VERSION_13
    }
    kotlinOptions {
        jvmTarget = "13"
    }
}
kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of("13"))
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