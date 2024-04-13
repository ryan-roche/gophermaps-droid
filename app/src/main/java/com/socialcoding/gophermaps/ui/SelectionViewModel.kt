package com.socialcoding.gophermaps.ui

import androidx.lifecycle.ViewModel
import com.socialcoding.gophermaps.data.DataSource
import com.socialcoding.gophermaps.data.MapsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SelectionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MapsUiState(buildings = DataSource.endBuildings))
    val uiState: StateFlow<MapsUiState> = _uiState.asStateFlow()
    fun setFirst(building: String){
        _uiState.update { currentState ->
            currentState.copy(
                start_building = building
            )
        }
    }
    fun setDestination(building: String){
        _uiState.update {currentState ->
            currentState.copy(
                end_building = building
            )
        }
    }
    fun resetSelection(){
        _uiState.value = MapsUiState(buildings = DataSource.endBuildings)
    }
}