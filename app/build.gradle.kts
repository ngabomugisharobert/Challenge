plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.robert.challenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.robert.challenge"
        minSdk = 24
        targetSdk = 34
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
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"

            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation( libs.lottie.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.junit.ktx)

    testImplementation(libs.junit)
    testImplementation(libs.junit.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


//    added libraries

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Lifecycle ViewModel and Hilt integration
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

//    glide
    implementation("com.github.bumptech.glide:glide:4.13.2")

    // Hilt for ViewModel (with Compose)
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation( "com.airbnb.android:lottie-compose:5.2.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
    implementation("androidx.compose.material:material-icons-extended-android:1.7.0")

    // Dependency Injection using Hilt
    implementation("com.google.dagger:hilt-android:2.46")
    kapt("com.google.dagger:hilt-android-compiler:2.46")
    implementation("androidx.compose.material:material:1.6.8")
    api ("com.google.android.material:material:1.1.0-alpha06")
    implementation("androidx.compose.material3:material3-window-size-class:1.3.0")
    implementation("androidx.navigation:navigation-compose:2.8.0")
    implementation("io.coil-kt:coil-compose:2.0.0")
    implementation("io.coil-kt:coil-gif:2.0.0")

    testImplementation("com.google.dagger:hilt-android-testing:2.46")
    kaptTest("com.google.dagger:hilt-android-compiler:2.46")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44")


//    testing
    testImplementation("io.mockk:mockk:1.13.7")
    androidTestImplementation("io.mockk:mockk-android:1.13.7")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("com.google.truth:truth:1.1.3")
    androidTestImplementation("com.google.truth:truth:1.1.3")

    // Testing dependencies
    debugImplementation(libs.androidx.monitor)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    testImplementation(libs.androidx.arch.core.testing)
    androidTestImplementation(libs.androidx.espresso.contrib)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.guava)
    androidTestImplementation(libs.accessibility.test.framework)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation(libs.junit)

    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.test.espresso:espresso-contrib:3.5.1")
    androidTestImplementation ("androidx.navigation:navigation-testing:2.7.3")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4")

}