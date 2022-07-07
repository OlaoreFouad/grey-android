package dependencies

import versions.Versions

object TestDependencies {
    const val junit = "junit:junit:${ Versions.junitTest }"
    const val mockitoCore = "org.mockito:mockito-core:${ Versions.mockitoCore }"
    const val kotlinCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2"
    const val archCoreTesting = "androidx.arch.core:core-testing:2.1.0"
}
