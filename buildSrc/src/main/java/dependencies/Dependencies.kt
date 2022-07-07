package dependencies

import versions.Versions

object Dependencies {

    const val ktxCore = "androidx.core:core-ktx:${ Versions.ktxCore }"
    const val appCompat = "androidx.appcompat:appcompat:${ Versions.appCompat }"
    const val googleMaterial = "com.google.android.material:material:${ Versions.googleMaterial }"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${ Versions.constraintLayout }"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${ Versions.legacySupport }"

    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${ Versions.retrofit }"
    const val moshi = "com.squareup.moshi:moshi:${ Versions.moshi }"
    const val moshiConverterFactory = "com.squareup.retrofit2:converter-moshi:${ Versions.retrofit }"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${ Versions.moshi }"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${ Versions.loggingInterceptor }"

    const val daggerHilt = "com.google.dagger:hilt-android:${ Versions.daggerHilt }"

    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${ Versions.kotlinCoroutines }"
    const val gson = "com.squareup.retrofit2:converter-gson:${ Versions.retrofit }"

    const val fragmentKtx = "androidx.fragment:fragment-ktx:${ Versions.fragmentKtx }"
    const val activityKtx = "androidx.activity:activity:${ Versions.activityKtx }"

    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${ Versions.lifecycle }"
    const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${ Versions.lifecycle }"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${ Versions.lifecycle }"

    const val circleImageView = "de.hdodenhof:circleimageview:${ Versions.circleImageView }"

    const val glide = "com.github.bumptech.glide:glide:${ Versions.glide }"
}
