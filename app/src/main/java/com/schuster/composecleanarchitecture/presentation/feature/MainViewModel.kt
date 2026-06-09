package com.schuster.composecleanarchitecture.presentation.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schuster.composecleanarchitecture.R
import com.schuster.composecleanarchitecture.domain.usecase.GetPostUseCase
import com.schuster.composecleanarchitecture.presentation.mapper.toPresentation
import com.schuster.composecleanarchitecture.utils.handleApiError
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: GetPostUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = Channel<MainUiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.OnValueChange -> {
                val onlyNumbers = event.searchText.filter { it.isDigit() }
                _uiState.update { it.copy(textSearch = onlyNumbers) }
            }
            is MainScreenEvent.OnSearch -> {
                getNewPost(_uiState.value.textSearch)
            }
            is MainScreenEvent.OnClickSearch -> {
                getNewPost(_uiState.value.textSearch)
            }
        }
    }

    private fun getNewPost(id: String) {
        viewModelScope.launch {
            if (id.isBlank()) {
                _uiEffect.send(MainUiEffect.ShowSnackbar(resId = R.string.search_not_empty))
                _uiState.update { it.copy(status = Status.InputTextError) }
                return@launch
            }

            _uiState.update { it.copy(status = Status.Loading) }

            try {
                val domainResult = useCase(id.toInt())
                
                if (domainResult != null) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            status = Status.Success(data = domainResult.toPresentation())
                        )
                    }
                } else {
                    _uiState.update { currentState ->
                        currentState.copy(
                            status = Status.Error(message = "Post não encontrado ou ID inválido")
                        )
                    }
                }
            } catch (error: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        status = Status.Error(message = handleApiError(error).toString())
                    )
                }
            }
        }
    }
}
