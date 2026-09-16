plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.wahidiyah.miladiyyah"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.wahidiyah.miladiyyah"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    /*
     * ========================================================
     * RELEASE SIGNING
     * ========================================================
     *
     * Tidak ada password yang ditulis langsung di source code.
     *
     * Gradle membaca:
     *
     * KEYSTORE_FILE
     * KEYSTORE_PASSWORD
     * KEY_ALIAS
     * KEY_PASSWORD
     *
     * Jika secrets belum tersedia:
     * - debug tetap dapat dibuild
     * - project tetap dapat dikonfigurasi
     * - release signing tidak diaktifkan
     *
     * Jika semua environment variable tersedia:
     * - release signing otomatis diaktifkan
     * ========================================================
     */

    val keystoreFile = System.getenv("KEYSTORE_FILE")
    val keystorePassword = System.getenv("KEYSTORE_PASSWORD")
    val keyAliasValue = System.getenv("KEY_ALIAS")
    val keyPassword = System.getenv("KEY_PASSWORD")

    val hasReleaseSigning =
        !keystoreFile.isNullOrBlank() &&
        !keystorePassword.isNullOrBlank() &&
        !keyAliasValue.isNullOrBlank() &&
        !keyPassword.isNullOrBlank()

    signingConfigs {
        if (hasReleaseSigning) {
            create("release") {
                storeFile = file(keystoreFile!!)
                storePassword = keystorePassword
                keyAlias = keyAliasValue
                this.keyPassword = keyPassword
            }
        }
    }

    buildTypes {
        release {
            if (hasReleaseSigning) {
                signingConfig = signingConfigs.getByName("release")
            }

            /*
             * Release tetap menggunakan konfigurasi aplikasi
             * yang sudah ada. Minify belum diaktifkan.
             */

            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.5")
    implementation("androidx.compose.material:material-icons-extended")

    val composeBom = platform("androidx.compose:compose-bom:2024.12.01")

    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.activity:activity-compose:1.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.8.5")

    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation("androidx.work:work-runtime-ktx:2.10.0")

    testImplementation("junit:junit:4.13.2")

    debugImplementation("androidx.compose.ui:ui-tooling")
}
