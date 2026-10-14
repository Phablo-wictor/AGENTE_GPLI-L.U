plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.agente_glpi"
    compileSdk {
        version = release(37)

        packagingOptions {
            resources.excludes.add("META-INF/DEPENDENCIES")
            resources.excludes.add("META-INF/LICENSE")
            resources.excludes.add("META-INF/LICENSE.txt")
            resources.excludes.add("META-INF/license.txt")
            resources.excludes.add("META-INF/NOTICE")
            resources.excludes.add("META-INF/NOTICE.txt")
            resources.excludes.add("META-INF/notice.txt")
        }


    }

    defaultConfig {
        applicationId = "com.example.agente_glpi"
        minSdk = 31
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation("com.google.android.gms:play-services-code-scanner:16.1.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
    // DEPEDENCIA DA API DO GOOGLE
    implementation("com.google.api-client:google-api-client-android:2.2.0")
    implementation("com.google.apis:google-api-services-sheets:v4-rev20230227-2.0.0")
    implementation("com.google.auth:google-auth-library-oauth2-http:1.16.0")
}