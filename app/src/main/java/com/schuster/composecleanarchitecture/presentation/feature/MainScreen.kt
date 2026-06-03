package com.schuster.composecleanarchitecture.presentation.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.schuster.composecleanarchitecture.presentation.components.ErrorScreen
import com.schuster.composecleanarchitecture.presentation.components.ErrorScreenInputSearch
import com.schuster.composecleanarchitecture.presentation.components.SearchTopBar
import com.schuster.composecleanarchitecture.presentation.components.ShimmerScreen
import com.schuster.composecleanarchitecture.presentation.ui.theme.PurpleGrey40
import com.schuster.composecleanarchitecture.utils.TestTags
import org.koin.androidx.compose.koinViewModel

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * A responsabilidade da UI é estritamente declarar e estruturar os componentes visuais, 
 * renderizando dados provenientes de um fluxo de dados e delegando as interações para funções
 * externas. Não há lógica de negócio nem gerenciamento de rede direto na tela.
 *
 * [SOLID: I - Interface Segregation Principle (Princípio da Segregação de Interfaces)]
 * [MainScreenContent] é um componente altamente segregado. Ele não recebe a instância do
 * ViewModel completo. Em vez disso, depende de interfaces simples e estruturas mínimas
 * de dados (o [UiState] e a lambda [onEvent]). Isso permite que o componente seja reutilizável, 
 * fácil de testar isoladamente e independente de viewModels específicos.
 *
 * [SOLID: D - Dependency Inversion Principle (Princípio da Inversão de Dependência)]
 * O [MainScreenContent] não depende diretamente de classes concretas de lógica de dados. 
 * As dependências de efeitos e intenções são passadas via injeção de parâmetros (Callbacks e Estados) 
 * a partir de componentes superiores.
 */
@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // Coleta os efeitos colaterais unidirecionais no nível superior, 
    // mantendo o conteúdo da tela stateless
    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { uiEffect ->
            when (uiEffect) {
                is MainUiEffect.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = uiEffect.asString(context)
                    )
                }
            }
        }
    }

    MainScreenContent(
        uiStateValue = uiState,
        snackbarHostState = snackbarHostState,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    uiStateValue: UiState,
    snackbarHostState: SnackbarHostState,
    onEvent: (MainScreenEvent) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    onEvent(MainScreenEvent.OnSearch)
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    SearchTopBar(
                        currentSearchText = uiStateValue.textSearch,
                        onSearchTextChanged = {
                            onEvent(MainScreenEvent.OnValueChange(it))
                        },
                        onSearchDispatched = {
                            keyboardController?.hide()
                            onEvent(MainScreenEvent.OnClickSearch)
                        },
                        onCleanTextPressed = {
                            onEvent(MainScreenEvent.OnValueChange(""))
                        },
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PurpleGrey40
                )
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        MainScreenUiState(uiStateValue, paddingValues)
    }
}

@Composable
fun MainScreenUiState(uiStateValue: UiState, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(color = PurpleGrey40),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        when (uiStateValue.status) {
            Status.SUCCESS -> {
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                        .testTag(TestTags.COMMENT_TEXT),
                    text = "COMENTARIO: ${uiStateValue.data?.comment}",
                    style = TextStyle(fontSize = 16.sp)
                )

                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    text = "EMAIL: ${uiStateValue.data?.email}",
                    style = TextStyle(fontSize = 16.sp)
                )

                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    text = "NOME: ${uiStateValue.data?.name}",
                    style = TextStyle(fontSize = 16.sp)
                )

                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    text = "ID: ${uiStateValue.data?.id}",
                    style = TextStyle(fontSize = 16.sp)
                )

                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    text = "POST ID: ${uiStateValue.data?.postId}",
                    style = TextStyle(fontSize = 16.sp)
                )
            }

            Status.ERROR -> {
                ErrorScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp),
                    uiStateError = uiStateValue.errorMessage.toString()
                )
            }

            Status.LOADING -> ShimmerScreen()

            Status.INPUT_TEXT_ERROR -> ErrorScreenInputSearch(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
            )

            Status.IDLE -> {}
        }
    }
}