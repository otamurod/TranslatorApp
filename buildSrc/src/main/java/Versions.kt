package com.ml.translator

object Dep {
    object Versions {
        object Compose {
            const val Core = "1.0.4"
            const val Foundation = "1.1.0-alpha-06"
            const val Navigation = "2.4.0-alpha10"
            const val RunTime = "1.1.0-alpha06"
            const val LifeCycle = "1.0.0-alpha07"
            const val Activity = "1.3.1"
        }

        object AndroidX {
            const val LifeCycle = "2.4.0"
        }

        const val Hilt = "2.45"
        const val HiltAndroid = "1.0.0-alpha03"

        const val Work = "2.7.0"
        const val Coil = "1.4.0"
        const val Accompanist = "0.21.4-beta"

        const val ExoPlayer = "2.15.1"

        const val Coroutines = "1.3.9"

        const val Retrofit = "2.9.0"
        const val Gson = "2.8.6"

        const val OkHttp = "4.9.1"

        const val Media3 = "1.0.0-alpha01"

        const val TensorFlow = "2.3.0"
        const val TensorFlowSupport = "0.1.0"

        const val Glide = "4.12.0"

        object MLKit {
            const val DigitalInk = "17.0.0"
            const val Translation = "16.1.2"
            const val FaceDetection = "16.1.4"
            const val TextRecognition = "16.0.0-beta3"
            const val EntityExtraction = "16.0.0-beta4"
            const val SmartReplies = "17.0.0"
            const val ImageLabeling = "17.0.7"
        }

        const val Emoji = "1.1.0-beta01"

        const val CameraX = "1.0.1"

        const val Room = "2.4.0"

        const val Arrow = "1.0.1"

        const val Klock = "2.4.12"
    }

    object Compose {
        const val Ui = "androidx.compose.ui:ui:${Versions.Compose.Core}"
        const val Runtime = "androidx.compose.runtime:runtime:${Versions.Compose.RunTime}"
        const val Material = "androidx.compose.material:material:${Versions.Compose.Core}"
//        const val Material3 = "androidx.compose.material3:material3:1.0.0-beta03"
        const val Tooling = "androidx.compose.ui:ui-tooling-preview:${Versions.Compose.Core}"
        const val Navigation = "androidx.navigation:navigation-compose:${Versions.Compose.Navigation}"
        const val Activity = "androidx.activity:activity-compose:${Versions.Compose.Activity}"

        const val ViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Compose.LifeCycle}"
        const val LiveData = "androidx.compose.runtime:runtime-livedata:${Versions.Compose.RunTime}"

        const val Test = "androidx.compose.ui:ui-test-junit4:${Versions.Compose.Core}"
        const val ToolingTest = "androidx.compose.ui:ui-tooling:${Versions.Compose.Core}"
        // for drop-down menu
        const val Cascade = "me.saket.cascade:cascade:2.0.0-rc01" //view
        const val CascadeCompose = "me.saket.cascade:cascade-compose:2.0.0-rc01" //jetpack compose
    }

    object AndroidX {
        const val Core = "androidx.core:core-ktx:1.6.0"
        const val AppCompat = "androidx.appcompat:appcompat:1.3.1"
        const val Material = "com.google.android.material:material:1.4.0"
        const val Lifeycycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.LifeCycle}"
        const val Work = "androidx.work:work-runtime-ktx:${Versions.Work}"
    }

    object Hilt {
        const val Core = "com.google.dagger:hilt-android:${Versions.Hilt}"
        const val Compiler = "com.google.dagger:hilt-android-compiler:${Versions.Hilt}"
        const val Compose = "androidx.hilt:hilt-navigation-compose:1.1.0-alpha01"
//        const val ViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HiltAndroid}"
        const val AndroidCompiler = "androidx.hilt:hilt-compiler:${Versions.HiltAndroid}"
    }

    object Coil {
        const val Compose = "io.coil-kt:coil-compose:${Versions.Coil}"
        const val Gif = "io.coil-kt:coil-gif:${Versions.Coil}"
        const val Video = "io.coil-kt:coil-video:${Versions.Coil}"
    }

    object Accompanist {
        const val SwipeRefresh = "com.google.accompanist:accompanist-swiperefresh:${Versions.Accompanist}"
        const val Permissions = "com.google.accompanist:accompanist-permissions:${Versions.Accompanist}"
    }

    object ExoPlayer {
        const val Core = "com.google.android.exoplayer:exoplayer:${Versions.ExoPlayer}"
        const val Ui = "com.google.android.exoplayer:exoplayer-ui:${Versions.ExoPlayer}"
    }

    object Media3 {
        const val ExoPlayer = "androidx.media3:media3-exoplayer:${Versions.Media3}"
        const val Ui = "androidx.media3:media3-ui:${Versions.Media3}"
        const val Sessions = "androidx.media3:media3-session:${Versions.Media3}"
    }

    object Retrofit {
        const val Core = "com.squareup.retrofit2:retrofit:${Versions.Retrofit}"
        const val GsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.Retrofit}"
    }

    object OkHttp {
        const val LogginInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.OkHttp}"
    }

    object TensorFlow {
        const val Support = "org.tensorflow:tensorflow-lite-support:${Versions.TensorFlowSupport}"
        const val MetaData = "org.tensorflow:tensorflow-lite-metadata:${Versions.TensorFlowSupport}"
    }

    object Glide {
        const val Core = "com.github.bumptech.glide:glide:${Versions.Glide}"
        const val Compiler = "com.github.bumptech.glide:compiler:${Versions.Glide}"
    }

    object CameraX {
        const val Core = "androidx.camera:camera-camera2:${Versions.CameraX}"
        const val Lifecycle = "androidx.camera:camera-lifecycle:${Versions.CameraX}"
        const val View = "androidx.camera:camera-view:1.0.0-alpha27"
    }

    object Room {
        const val Core = "androidx.room:room-runtime:${Versions.Room}"
        const val Ktx = "androidx.room:room-ktx:${Versions.Room}"
        const val Compiler = "androidx.room:room-compiler:${Versions.Room}"
    }

    const val Gson = "com.google.code.gson:gson:${Versions.Gson}"

    const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutines}"

    const val ArrowKt = "io.arrow-kt:arrow-core:${Versions.Arrow}"

    const val Klock = "com.soywiz.korlibs.klock:klock:${Versions.Klock}"

    object Emoji {
        const val Core = "androidx.emoji2:emoji2:${Versions.Emoji}"
        const val Views = "androidx.emoji2:emoji2-views:${Versions.Emoji}"
        const val Helper = "androidx.emoji2:emoji2-views-helper:${Versions.Emoji}"
    }

    object MLKit {
        const val Translation = "com.google.mlkit:translate:${Versions.MLKit.Translation}"
        const val FaceDetection = "com.google.mlkit:face-detection:${Versions.MLKit.FaceDetection}"
        const val DigitalInk = "com.google.mlkit:digital-ink-recognition:${Versions.MLKit.DigitalInk}"
        const val TextRecognition = "com.google.mlkit:text-recognition:${Versions.MLKit.TextRecognition}"
        const val EntityExtraction = "com.google.mlkit:entity-extraction:${Versions.MLKit.EntityExtraction}"
        const val SmartReplies = "com.google.mlkit:smart-reply:${Versions.MLKit.SmartReplies}"
        const val ImageLabeling = "com.google.mlkit:image-labeling:${Versions.MLKit.ImageLabeling}"

        const val TextRecognitionJp = "com.google.mlkit:text-recognition-japanese:${Versions.MLKit.TextRecognition}"
        const val TextRecognitionDev = "com.google.mlkit:text-recognition-devanagari:${Versions.MLKit.TextRecognition}"
    }
}

