package dependencies

import versions.Versions

object Build {

    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${ Versions.navigation }"
    const val daggerHilt = "com.google.dagger:hilt-android-gradle-plugin:${ Versions.daggerHilt }"

}
