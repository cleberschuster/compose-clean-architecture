package com.schuster.composecleanarchitecture.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.schuster.composecleanarchitecture.R

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * A responsabilidade exclusiva deste arquivo/componentes é renderizar visualmente os estados de erro 
 * (erro de chamada à API e erro de input do campo de busca) para o usuário final de forma declarativa.
 */
@Composable
fun ErrorScreen(modifier: Modifier, uiStateError: String) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_error_72),
            contentDescription = "${stringResource(id = R.string.error_text)} $uiStateError"
        )
        Text(
            text = "${stringResource(id = R.string.error_text)} $uiStateError,",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ErrorScreenInputSearch(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_error_72),
            contentDescription = stringResource(id = R.string.search_not_empty)
        )
        Text(
            text = stringResource(id = R.string.search_not_empty),
            modifier = Modifier.padding(16.dp)
        )
    }
}