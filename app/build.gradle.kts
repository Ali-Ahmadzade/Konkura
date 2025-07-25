plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "ir.devalix.konkura"
    compileSdk = 36

    defaultConfig {
        applicationId = "ir.devalix.konkura"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        buildFeatures {
            viewBinding = true
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Splash Screen
    //noinspection UseTomlInstead
    implementation("androidx.core:core-splashscreen:1.0.1")
    //bot nav
    implementation("np.com.susanthapa:curved_bottom_navigation:0.7.0")
    // expandable CardView
    implementation("com.github.cachapa:ExpandableLayout:2.9.2")
    //Gson
    implementation("com.google.code.gson:gson:2.13.1")
    //pdf viewer
    implementation("com.github.afreakyelf:Pdf-Viewer:v1.0.7")
    // okhttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    //download
    implementation("com.github.amitshekhariitbhu:PRDownloader:1.0.2")
    //progressBar
    implementation("com.mikhaellopez:circularprogressbar:3.1.0")
    //sweetAlertDialog
    implementation("com.github.cazaea:sweet-alert-dialog:1.0.0")

}