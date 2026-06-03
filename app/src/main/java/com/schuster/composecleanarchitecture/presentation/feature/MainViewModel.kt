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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * Este ViewModel tem como responsabilidade exclusiva receber as interações/ações do usuário
 * (representados por [MainScreenEvent]), acionar a lógica de negócios na camada de domínio 
 * através de [GetPostUseCase], e atualizar de forma unidirecional o estado de exibição [UiState] 
 * e os efeitos colaterais instantâneos [MainUiEffect].
 * A responsabilidade de mapeamento de dados foi extraída para o arquivo separado [PostMapper.kt].
 *
 * [SOLID: O - Open/Closed Principle (Princípio do Aberto/Fechado)]
 * O ViewModel é aberto para receber novos tipos de eventos através da estrutura [onEvent], bastando
 * adicionar novas ramificações no 'when' quando novas ações do usuário forem adicionadas na interface
 * selada [MainScreenEvent], sem comprometer ou modificar as lógicas existentes de fluxo.
 *
 * [SOLID: D - Dependency Inversion Principle (Princípio da Inversão de Dependência)]
 * O ViewModel depende exclusivamente da abstração [GetPostUseCase] (interface do domínio) em vez da
 * implementação concreta [GetPostUseCaseImpl]. Além disso, a injeção da dependência é feita através
 * do construtor da classe via Koin.
 */
class MainViewModel(private val useCase: GetPostUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = Channel<MainUiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.OnValueChange -> {
                // Filtra para manter APENAS dígitos numéricos
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
                _uiState.update { it.copy(status = Status.INPUT_TEXT_ERROR) }
                return@launch
            }

            _uiState.update { it.copy(status = Status.LOADING) }

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
}