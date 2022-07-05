package dependencies

import versions.Versions

object AndroidTestDependencies {
    const val junit = "androidx.test.ext:junit:${ Versions.junitAndroidTest }"
    const val espresso = "androidx.test.espresso:espresso-core:${ Versions.espresso }"
}
