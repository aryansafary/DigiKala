import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)

}
val apiPropertiesFile = rootProject.file("key.properties")
val apiProperties = Properties()
apiProperties.load(apiPropertiesFile.inputStream())
android {
    namespace = "com.arysapp.digikala"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.arysapp.digikala"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "X_API_KEY", apiProperties.getProperty("X_API_KEY"))
        buildConfigField("String" , "KEY" , apiProperties.getProperty("KEY"))
        buildConfigField("String" , "IV" , apiProperties.getProperty("IV"))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildFeatures {
        buildConfig =true
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
        targetCompatibility =JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
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
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //Navigation Compose Implementation
    implementation(libs.androidx.navigation.compose)
    //Retrofit implementations
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    //DataStore implementations
    implementation(libs.androidx.datastore.preferences)
    //Room implementations
    implementation(libs.androidx.room.ktx)
    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    // for Show Photo
    implementation(libs.coil.compose)
    implementation (libs.glide.compose)
    //Lottie for Animation
    implementation(libs.lottie.compose)
    //hilt Di
    implementation(libs.hilt.android)
    implementation (libs.androidx.hilt.navigation.compose)
    kapt (libs.hilt.android.compiler)
    //System UI
    implementation(libs.accompanist.systemuicontroller)
    // Swipe Refresh
    implementation(libs.accompanist.swiperefresh)
   //Pager Accompanist implementations
    implementation (libs.accompanist.pager)
    implementation (libs.accompanist.pager.indicators)
    //paging3
    implementation( "androidx.paging:paging-compose:3.3.4")

    //chart
    implementation("com.patrykandpatrick.vico:compose:1.13.0")

    //gson
    implementation ("com.google.code.gson:gson:2.10.1")

    //icon
    implementation ("androidx.compose.material:material-icons-extended:1.7.5")


}
kapt{
    correctErrorTypes = true
}