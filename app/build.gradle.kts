plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt") // Enabling kapt for annotation processing
}

android {
    namespace = "com.appexensetrackers"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.appexensetrackers"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom)) // Automatically resolves all Compose versions
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    implementation(libs.support.annotations)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.runtime.ktx)

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom)) // For consistency
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Update single version of Compose libraries (don't use multiple versions)
    implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.material3:material3:1.2.0") // Ensure compatible version

    // ConstraintLayout for Compose
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0")

    // Google Font support (if required)
    implementation("androidx.compose.ui:ui-text-google-fonts:1.7.6")

    // Room dependencies
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")
    testImplementation("androidx.room:room-testing:$roomVersion")

    // LiveData & ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")

    // ViewModel Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    // Navigation for Compose
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // MPAndroidChart (for charts)
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
}
