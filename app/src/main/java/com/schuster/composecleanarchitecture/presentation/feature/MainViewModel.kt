package com.schuster.composecleanarchitecture.presentation.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schuster.composecleanarchitecture.R
import com.schuster.composecleanarchitecture.domain.model.DomainResult
import com.schuster.composecleanarchitecture.domain.usecase.GetPostUseCase
import com.schuster.composecleanarchitecture.presentation.mapper.toPresentation
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * [MVI: Model]
 * O ViewModel atua como o mediador que recebe Intenções (Intents), processa-as e 
 * emite novos Estados (States) ou Efeitos (Effects).
 *
 * [SOLID: S - Single Responsibility Principle]
 * Coordena o fluxo de dados entre o domínio (Use Cases) e a apresentação.
 */
class MainViewModel(private val useCase: GetPostUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    // O uso de Channel.BUFFERED garante que eventos únicos (como Snackbars e Navegação) 
    // sejam entregues à UI mesmo que ela não esteja pronta no momento do disparo,
    // evitando a perda de eventos durante mudanças de configuração ou inicialização.
    private val _uiEffect = Channel<MainUiEffect>(Channel.BUFFERED)
    val uiEffect = _uiEffect.receiveAsFlow()

    /**
     * Ponto único de entrada para todas as interações do usuário.
     * Garante o fluxo unidirecional de dados.
     */
    fun processIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.OnValueChange -> handleValueChange(intent.searchText)
            is MainIntent.OnSearch -> getNewPost(_uiState.value.textSearch)
        }
    }

    private fun handleValueChange(searchText: String) {
        val onlyNumbers = searchText.filter { it.isDigit() }
        _uiState.update { it.copy(textSearch = onlyNumbers) }
    }

    private fun getNewPost(id: String) {
        viewModelScope.launch {
            if (id.isBlank()) {
                _uiEffect.send(MainUiEffect.ShowSnackbar(resId = R.string.search_not_empty))
                _uiState.update { it.copy(status = MainStatus.InputTextError) }
                return@launch
            }

            _uiState.update { it.copy(status = MainStatus.Loading) }

            when (val domainResult = useCase(id.toInt())) {
                is DomainResult.Success -> {
                    _uiState.update { currentState ->
                        currentState.copy(
                            status = MainStatus.Success(data = domainResult.data.toPresentation())
                        )
                    }
                }
                is DomainResult.Error -> {
                    _uiState.update { currentState ->
                        currentState.copy(
                            status = MainStatus.Error(message = domainResult.message)
                        )
                    }
                }
            }
        }
    }
}
