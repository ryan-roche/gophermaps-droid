package com.socialcoding.gophermaps.data

data class MapsUiState (
    val start_building: String = "",
    val end_building: String = "",
    val end_options: List<String> = listOf(),
    val buildings: List<String> = DataSource.examplePreview
)