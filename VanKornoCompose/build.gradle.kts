plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("maven-publish")
}

android {
    namespace = "com.vankorno.vankornocompose"
    compileSdk = 35
    
    defaultConfig {
        minSdk = 23
        
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
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
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    
    
    implementation(libs.androidx.constraintlayout.compose) // Constraint Layout
    
    // SavedStateHandle API for ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    // For direct access from a composable and other stuff
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    
    
    implementation(libs.kotlinx.coroutines.core)// StateFlow
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.runtime.compose)
    
    
}

val versionTag = "0.0.1"
val buildDirectory = layout.buildDirectory.get()

publishing {    /* to ensure that the library is published correctly */
    publications {
        create<MavenPublication>("release") {
            afterEvaluate {
                from(components.findByName("release"))
            }
            groupId = "com.vankorno"
            artifactId = "vankornocompose"
            version = versionTag
        }
    }
}