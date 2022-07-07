package dependencies

import versions.Versions

object AnnotationProcessing {
    const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${ Versions.moshi }"
    const val daggerHiltCompiler = "com.google.dagger:hilt-android-compiler:${ Versions.daggerHilt }"
    const val lifecycleCompiler =  "androidx.lifecycle:lifecycle-compiler:${ Versions.lifecycle }"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${ Versions.glide }"
}
