package com.ml.translator.data.model

import com.ml.translator.ui.Destinations

data class Screen(
    val destinations: Destinations,
    val previewImage: String = "",
    val title: String,
    val description: String,
    val tags: List<String> = emptyList()
)