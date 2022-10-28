import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * @author yaya (@yahyalmh)
 * @since 28th October 2022
 */

object Dependencies {
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Version.Androidx.CORE_KTX}"
    const val ANDROID_LIFECYCLE_RUNTIME =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Version.Androidx.LIFECYCLE}"

    const val ANDROID_ACTIVITY_COMPOSE =
        "androidx.activity:activity-compose:${Version.Compose.ACTIVITY_COMPOSE}"
    const val ANDROIDX_COMPOSE_UI = "androidx.compose.ui:ui:${Version.Compose.UI_VERSION}"
    const val ANDROIDX_COMPOSE_PREVIEW =
        "androidx.compose.ui:ui-tooling-preview:${Version.Compose.UI_VERSION}"
    const val ANDROIDX_COMPOSE_UI_TOOLING =
        "androidx.compose.ui:ui-tooling:${Version.Compose.UI_VERSION}"
    const val ANDROID_COMPOSE_MATERIAL =
        "androidx.compose.material:material:${Version.Compose.MATERIAL}"


    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Version.Hilt.HILT_ANDROID}"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${Version.Hilt.HILT_COMPILER}"

    const val JUNIT = "junit:junit:${Version.Junit.JUNIT}"
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${Version.Junit.ANDROIDX_JUNIT}"

    const val ANDROIDX_ESPRESSO_CORE =
        "androidx.test.espresso:espresso-core:${Version.ESPRESSO_CORE}"

    const val COMPOSE_UI_TEST = "androidx.compose.ui:ui-test-junit4:${Version.Compose.UI_VERSION}"
    const val COMPOSE_UI_TEST_MANIFEST =
        "androidx.compose.ui:ui-test-manifest:${Version.Compose.UI_VERSION}"
}


fun DependencyHandler.compose() {
    implementation(Dependencies.ANDROID_ACTIVITY_COMPOSE)
    implementation(Dependencies.ANDROIDX_COMPOSE_PREVIEW)
    implementation(Dependencies.ANDROID_COMPOSE_MATERIAL)
    implementation(Dependencies.ANDROIDX_COMPOSE_UI)
    implementation(Dependencies.ANDROIDX_COMPOSE_UI_TOOLING)
}

fun DependencyHandler.composeTest() {
    androidTestImplementation(Dependencies.COMPOSE_UI_TEST)
    debugImplementation(Dependencies.COMPOSE_UI_TEST_MANIFEST)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.HILT_ANDROID)
    kapt(Dependencies.HILT_COMPILER)
}

fun DependencyHandler.junit() {
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.ANDROIDX_JUNIT)
}

fun DependencyHandler.espresso() = androidTestImplementation(Dependencies.ANDROIDX_ESPRESSO_CORE)

fun DependencyHandler.lifecycle() {
    implementation(Dependencies.ANDROID_LIFECYCLE_RUNTIME)
}

private fun DependencyHandler.kapt(depName: String) = add("kapt", depName)

fun DependencyHandler.implementation(depName: String) = add("implementation", depName)

fun DependencyHandler.androidTestImplementation(depName: String) =
    add("androidTestImplementation", depName)

fun DependencyHandler.testImplementation(depName: String) = add("testImplementation", depName)

fun DependencyHandler.debugImplementation(depName: String) = add("debugImplementation", depName)

private fun DependencyHandler.compileOnly(depName: String) = add("compileOnly", depName)

private fun DependencyHandler.api(depName: String) = add("api", depName)
