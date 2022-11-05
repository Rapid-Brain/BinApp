import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * @author yaya (@yahyalmh)
 * @since 28th October 2022
 */

object Dependencies {
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Version.Androidx.CORE_KTX}"
    const val ANDROID_LIFECYCLE_RUNTIME =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Version.Androidx.LIFECYCLE}"

    const val COMPOSE_BOM = "androidx.compose:compose-bom:${Version.Compose.BOM}"
    const val ANDROID_ACTIVITY_COMPOSE =
        "androidx.activity:activity-compose:${Version.Compose.ACTIVITY_COMPOSE}"
    const val ANDROIDX_COMPOSE_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val ANDROIDX_COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling"
    const val ANDROIDX_COMPOSE_LIFECYCLE =
        "androidx.lifecycle:lifecycle-runtime-compose:${Version.Androidx.LIFECYCLE}"
    const val COMPOSE_UI = "androidx.compose.ui:ui"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material"
    const val COMPOSE_MATERIAL3 = "androidx.compose.material3:material3"
    const val COMPOSE_MATERIAL3_WINDOW_SIZE =
        "androidx.compose.material3:material3-window-size-class"
    const val COMPOSE_NAVIGATION =
        "androidx.navigation:navigation-compose:${Version.Compose.NAVIGATION}"
    const val COMPOSE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-compose"
    const val COMPOSE_HILT_NAVIGATION =
        "androidx.hilt:hilt-navigation-compose:${Version.Compose.HILT_NAVIGATION}"
    const val COMPOSE_UI_TEST = "androidx.compose.ui:ui-test-junit4"
    const val COMPOSE_UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest"


    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Version.Hilt.HILT_ANDROID}"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${Version.Hilt.HILT_COMPILER}"

    const val JUNIT = "junit:junit:${Version.Junit.JUNIT}"
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${Version.Junit.ANDROIDX_JUNIT}"

    const val ANDROIDX_ESPRESSO_CORE =
        "androidx.test.espresso:espresso-core:${Version.ESPRESSO_CORE}"


    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT}"
    const val RETROFIT_GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Version.RETROFIT}"
    const val GSON = "com.google.code.gson:gson:${Version.GSON}"
    const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${Version.OKHTTP_LOGGING}"

    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES}"
    const val COROUTINES_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.COROUTINES}"
}

fun DependencyHandler.androidxCore() = implementation(Dependencies.ANDROIDX_CORE_KTX)

fun DependencyHandler.compose() {
    implementation(platform(Dependencies.COMPOSE_BOM))
    implementation(Dependencies.COMPOSE_HILT_NAVIGATION)
    implementation(Dependencies.ANDROID_ACTIVITY_COMPOSE)
    implementation(Dependencies.ANDROIDX_COMPOSE_PREVIEW)
    implementation(Dependencies.ANDROIDX_COMPOSE_UI_TOOLING)
    implementation(Dependencies.ANDROIDX_COMPOSE_LIFECYCLE)
    implementation(Dependencies.COMPOSE_UI)
}

fun DependencyHandler.composeMaterial() {
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_MATERIAL3)
    implementation(Dependencies.COMPOSE_MATERIAL3_WINDOW_SIZE)
}

fun DependencyHandler.composeTest() {
    androidTestImplementation(platform(Dependencies.COMPOSE_BOM))
    androidTestImplementation(Dependencies.COMPOSE_UI_TEST)
    debugImplementation(Dependencies.COMPOSE_UI_TEST_MANIFEST)
}

fun DependencyHandler.composeNavigation() {
    implementation(Dependencies.COMPOSE_NAVIGATION)
}

fun DependencyHandler.composeViewmodel() {
    implementation(Dependencies.COMPOSE_VIEWMODEL)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.COROUTINES_TEST)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_GSON_CONVERTER)
    implementation(Dependencies.GSON)
    implementation(Dependencies.OKHTTP_LOGGING)
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

fun DependencyHandler.lifecycle() = implementation(Dependencies.ANDROID_LIFECYCLE_RUNTIME)

private fun DependencyHandler.kapt(depName: String) = add("kapt", depName)

fun DependencyHandler.implementation(depName: String) = add("implementation", depName)
fun DependencyHandler.implementation(dependency: Dependency) = add("implementation", dependency)

fun DependencyHandler.androidTestImplementation(depName: String) =
    add("androidTestImplementation", depName)

fun DependencyHandler.androidTestImplementation(dependency: Dependency) =
    add("androidTestImplementation", dependency)

fun DependencyHandler.testImplementation(depName: String) = add("testImplementation", depName)

fun DependencyHandler.debugImplementation(depName: String) = add("debugImplementation", depName)

private fun DependencyHandler.compileOnly(depName: String) = add("compileOnly", depName)

private fun DependencyHandler.api(depName: String) = add("api", depName)
