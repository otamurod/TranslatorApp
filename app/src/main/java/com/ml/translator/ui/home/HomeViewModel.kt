package com.ml.translator.ui.home

import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ml.translator.base.SingleFlowViewModel
import com.ml.translator.data.ScreensDataProvider
import com.ml.translator.data.model.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val screensDataProvider: ScreensDataProvider,
) : ViewModel(), HomeViewModel {

    private val _screens = screensDataProvider.fetchScreen()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    override val state: StateFlow<HomeViewModel.State>
        get() = _screens
            .map { HomeViewModel.State(screens = it) }
            .stateIn(viewModelScope, SharingStarted.Eagerly, HomeViewModel.State())

    override fun onEvent(event: HomeViewModel.Event) {}

}

interface HomeViewModel: SingleFlowViewModel<HomeViewModel.Event, HomeViewModel.State> {

    sealed class Event

    data class State(
        val screens: List<Screen> = emptyList(),
    )

}

val LocalHomeViewModel = compositionLocalOf<HomeViewModel> {
    error("HomeViewModel not provided")
}