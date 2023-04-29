package com.ml.translator.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ml.translator.ui.digitalink.DigitalInkScreen
import com.ml.translator.ui.digitalink.DigitalInkViewModelImpl
import com.ml.translator.ui.digitalink.LocalDigitalInkViewModel
import com.ml.translator.ui.home.HomeScreen
import com.ml.translator.ui.home.HomeViewModelImpl
import com.ml.translator.ui.home.LocalHomeViewModel
import com.ml.translator.ui.theme.JishoTheme

@Composable
fun MLApp(
    navigateToSettings: () -> Unit
) {
    val navController = rememberNavController()
    
    JishoTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NavHost(navController = navController, startDestination = Destinations.Home.value) {
                composable(route = Destinations.Home.value) {
                    CompositionLocalProvider(
                        LocalHomeViewModel provides hiltViewModel<HomeViewModelImpl>()
                    ) {
                        HomeScreen(
                            navigateToDestination = { navController.navigate(route = it.value) },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                composable(route = Destinations.DigitalInk.value) {
                    CompositionLocalProvider(
                        LocalDigitalInkViewModel provides  hiltViewModel<DigitalInkViewModelImpl>(),
                    ) {
                        DigitalInkScreen(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}