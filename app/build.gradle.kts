plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // 🟢 Plugin del compilador de Compose (necesario con Kotlin 2.0)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

android {
    namespace = "com.example.practica1moviles20100798"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.practica1moviles20100798"
        minSdk = 33
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    // 🟢 Activamos Jetpack Compose
    buildFeatures {
        compose = true
    }

    // 🟢 Versión del compilador de Compose
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Dependencias básicas del proyecto
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // 🟢 DEPENDENCIAS DE JETPACK COMPOSE
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.compose.ui:ui:1.6.8")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.8")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.8")
    implementation("androidx.compose.material3:material3:1.2.1")

    // 🟢 ÍCONOS DE COMPOSE
    implementation("androidx.compose.material:material-icons-core:1.6.8")
    implementation("androidx.compose.material:material-icons-extended:1.6.8")

    // 🟢 Coil para cargar imágenes desde URL
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Dependencias de test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
