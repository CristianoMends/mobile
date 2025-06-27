package com.edu.numbergeneratorapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberScreen(viewModel: NumberViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Gerador de Número") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (uiState) {
                is UiState.Idle -> {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.generateRandomNumber() }) {
                        Text("Gerar Número Aleatório")
                    }
                }

                is UiState.Loading -> {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Gerando número...")
                }

                is UiState.Success -> {
                    val number = (uiState as UiState.Success).number
                    Text("Número Gerado: $number", style = MaterialTheme.typography.headlineLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.resetState() }) {
                        Text("Gerar Novamente")
                    }
                }

                is UiState.Error -> {
                    val errorMessage = (uiState as UiState.Error).message
                    Text("Erro: $errorMessage", color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.resetState() }) {
                        Text("Tentar Novamente")
                    }
                }
            }
        }
    }
}