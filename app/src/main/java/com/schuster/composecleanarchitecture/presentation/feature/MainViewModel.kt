package com.schuster.composecleanarchitecture.presentation.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schuster.composecleanarchitecture.R
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.domain.usecase.GetPostUseCase
import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation
import com.schuster.composecleanarchitecture.utils.handleApiError
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
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
                _uiState.update { it.copy(textSearch = event.searchText.trim()) }
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
        if (id.isBlank()) {
            viewModelScope.launch {
                _uiEffect.send(MainUiEffect.ShowSnackbar(resId = R.string.search_not_empty))
                _uiState.update { it.copy(status = Status.INPUT_TEXT_ERROR) }
            }
            return
        }

        _uiState.update { it.copy(status = Status.LOADING) }

        viewModelScope.launch {
            useCase.invoke(id.toInt())
                .onEach { domainResult ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            status = Status.SUCCESS,
                            data = domainResult.toPresentation(),
                        )
                    }
                }
                .catch { error ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            status = Status.ERROR,
                            errorMessage = handleApiError(error).toString()
                        )
                    }
                }
                .collect()
        }
    }

    private fun ObjectDomain.toPresentation() = ObjectPresentation(
        postId = postId,
        id = id,
        name = name,
        email = email,
        comment = comment
    )
}
