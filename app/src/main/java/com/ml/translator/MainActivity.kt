package com.ml.translator

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ml.translator.ui.MLApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MLApp(
            navigateToSettings = {
                startActivity(Intent().apply {
                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    data = Uri.fromParts("package", com.ml.translator.BuildConfig.APPLICATION_ID, null)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
        ) }
    }
}