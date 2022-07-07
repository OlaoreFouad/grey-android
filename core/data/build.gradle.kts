import dependencies.Modules
import dependencies.Dependencies
import dependencies.AnnotationProcessing

apply(from = "../../config/android_library_build.gradle")

plugins {
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    "implementation"(project(Modules.domain))

    // retrofit
    "implementation"(Dependencies.retrofit)

    // moshi
    "implementation"(Dependencies.gson)
    "implementation"(Dependencies.loggingInterceptor)

    // dagger hilt
    "implementation"(Dependencies.daggerHilt)
    "kapt"(AnnotationProcessing.daggerHiltCompiler)

    "implementation"(Dependencies.kotlinCoroutines)
}
