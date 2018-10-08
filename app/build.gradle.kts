import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(28)
    buildToolsVersion("28.0.3")
    defaultConfig {
        applicationId = "me.gr.paging"
        minSdkVersion(19)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

val supportLibraryVersion = "28.0.0"
val lifecycleVersion = "1.1.1"
val roomVersion = "1.1.1"
val pagingVersion = "1.0.1"
dependencies {
    kapt("android.arch.persistence.room:compiler:$roomVersion")

    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation("com.android.support:appcompat-v7:$supportLibraryVersion")
    implementation("com.android.support:recyclerview-v7:$supportLibraryVersion")
    implementation("com.android.support:cardview-v7:$supportLibraryVersion")
    implementation("android.arch.lifecycle:runtime:$lifecycleVersion")
    implementation("android.arch.lifecycle:extensions:$lifecycleVersion")
    implementation("android.arch.persistence.room:runtime:$roomVersion")
    implementation("android.arch.paging:runtime:$pagingVersion")

    testImplementation("junit:junit:4.12")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
}
