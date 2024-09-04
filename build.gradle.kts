// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.google.gms.google.services) apply false

    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin) apply false
}
buildscript {
    dependencies {
        // Navigation Safe Args plugin classpath
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.3")
        classpath ("com.google.gms:google-services:4.3.15")
        classpath ("com.google.gms:google-services:4.4.2")
        classpath ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
    }
}
