object Versions {
    const val kotlinVersion = "1.4.20"
    const val gradleVersion = "4.1.1"
    const val navigationVersion = "2.3.1"

    // Implementation
    const val kotlinStdVersion = "1.4.10"
    const val kotlinCoreVersion = "1.3.2"
    const val appCompactVersion = "1.2.0"
    const val googleMaterialVersion = "1.2.1"
    const val constraintLayoutVersion = "2.0.4"
    const val coroutinesCoreVersion = "1.4.1"
    const val coilVersion = "0.11.0"
    const val swipeRefreshLayoutVersion = "1.1.0"
    const val retrofitVersion = "2.9.0"
    const val daggerVersion = "2.29.1"
    const val lifecycleVersion = "2.2.0"

    // Test Implementation
    const val jUnitVersion = "4.13.1"
    const val mockkVersion = "1.10.2"
    const val archCoreVersion = "2.1.0"
    const val testTruthVersion = "1.3.1-alpha02"
    const val coroutinesTestVersion = "1.4.1"

}

object Dependencies {

    val implementations = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinStdVersion}",
        "androidx.core:core-ktx:${Versions.kotlinCoreVersion}",
        "androidx.appcompat:appcompat:${Versions.appCompactVersion}",
        "com.google.android.material:material:${Versions.googleMaterialVersion}",
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCoreVersion}",

        // Image Loader
        "io.coil-kt:coil:${Versions.coilVersion}",

        // Swipe Down refresher
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayoutVersion}",

        // Navigation
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}",
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}",

        // Retrofit
        "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}",
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}",

        // Dagger 2
        "com.google.dagger:dagger:${Versions.daggerVersion}",
        "com.google.dagger:dagger-compiler:${Versions.daggerVersion}",
        "com.google.dagger:dagger-android:${Versions.daggerVersion}",
        "com.google.dagger:dagger-android-support:${Versions.daggerVersion}",

        // LiveData
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}",
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    )

    val kapt = listOf(
        "com.google.dagger:dagger-compiler:${Versions.daggerVersion}",
        "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
    )

    val testImplementations = listOf(
        "junit:junit:${Versions.jUnitVersion}",
        "io.mockk:mockk:${Versions.mockkVersion}",
        "androidx.arch.core:core-testing:${Versions.archCoreVersion}",
        "androidx.test.ext:truth:${Versions.testTruthVersion}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTestVersion}"
    )

}