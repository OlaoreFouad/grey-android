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
}
