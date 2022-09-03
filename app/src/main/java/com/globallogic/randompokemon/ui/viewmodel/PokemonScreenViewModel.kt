package com.globallogic.randompokemon.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PokemonScreenViewModel : ViewModel() {
    private val _isBottomCardExpanded = MutableLiveData(false)
    val isBottomCardExpanded: LiveData<Boolean> = _isBottomCardExpanded
    private val _isMoveListExpanded = MutableLiveData(false)
    val isMoveListExpanded: LiveData<Boolean> = _isMoveListExpanded

    fun onToggleMoveListExpandedClick() {
        _isMoveListExpanded.value?.let { _isMoveListExpanded.value = !it }
    }

    fun onBottomCardExpanded() {
        _isBottomCardExpanded.value = true
    }

    fun onBottomCardCollapsed() {
        _isBottomCardExpanded.value = false
    }
}