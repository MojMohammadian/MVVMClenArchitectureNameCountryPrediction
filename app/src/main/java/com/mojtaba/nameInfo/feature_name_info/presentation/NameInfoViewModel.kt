package com.mojtaba.nameInfo.feature_name_info.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mojtaba.nameInfo.core.Resource
import com.mojtaba.nameInfo.feature_name_info.domain.use_case.GetNameInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NameInfoViewModel @Inject constructor(
    private val getNameInfo: GetNameInfo
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(NameInfoState())
    val state: State<NameInfoState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(700L)
            getNameInfo(query).onEach { result ->
                when(result){

                    is Resource.Success ->{
                      _state.value = state.value.copy(
                            nameInfoItem = result.data,
                            isLoading = false
                        )
                    }
                    is Resource.Error ->{
                        _state.value = state.value.copy(
                            nameInfoItem = result.data,
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.ShowSnackbar(
                            result.message ?: "Unknown error"
                        ))

                    }
                    is Resource.Loading ->{
                        _state.value = state.value.copy(
                            nameInfoItem = result.data,
                            isLoading = true
                        )
                    }

                }

            }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }


}