package com.ufc.cadapp

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ufc.cadapp.ui.theme.CadAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
@Preview
fun App(){
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var nacionalidade by remember { mutableStateOf("") }
    var nascimento by remember { mutableStateOf("") }
    var objetivo by remember { mutableStateOf("") }
    var areaAtuacao by remember { mutableStateOf("") }
    var escolaridade by remember { mutableStateOf("") }
    var curso by remember { mutableStateOf("") }
    var instituicao by remember { mutableStateOf("") }
    var conclusao by remember { mutableStateOf("") }
    var ultimaProfissao by remember { mutableStateOf("") }
    var dataEntrada by remember { mutableStateOf("") }
    var dataSaida by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp),

        modifier = Modifier.fillMaxSize()
            .padding(20.dp, 30.dp)
            .verticalScroll(scrollState)
    ){

        Text("Cadastro de Curriculo",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
        Text("Pessoal")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = nome,
            onValueChange = { nome = it},
            label = { Text("Nome") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it},
            label = { Text("Email") },
            placeholder = { Text("usuario@gmail.com") }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = telefone,
            onValueChange = { telefone = it},
            label = { Text("Telefone") }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = endereco,
            onValueChange = { endereco = it},
            label = { Text("Endereço") }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = nacionalidade,
            onValueChange = { nacionalidade = it},
            label = { Text("Nacionalidade") }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = nascimento,
            onValueChange = { nascimento = it},
            label = { Text("Nascimento") }
        )
        Text("Objetivo")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = objetivo,
            onValueChange = { objetivo = it},
            label = { Text("Objetivo") }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = areaAtuacao,
            onValueChange = { areaAtuacao = it},
            label = { Text("Area de Atuação") }
        )
        Text("Formação")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = escolaridade,
            onValueChange = { escolaridade = it},
            label = { Text("Escolaridade") }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = curso,
            onValueChange = { curso = it},
            label = { Text("Curso") }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = instituicao,
            onValueChange = { instituicao = it},
            label = { Text("Instituição") }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = conclusao,
            onValueChange = { conclusao = it},
            label = { Text("Conclusão") }
        )
        Text("Experiência profissional")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = ultimaProfissao,
            onValueChange = { ultimaProfissao = it},
            label = { Text("Última Profissão") }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = dataEntrada,
            onValueChange = { dataEntrada = it},
            label = { Text("Data de entrada") }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = dataSaida,
            onValueChange = { dataSaida = it},
            label = { Text("Data de saída") }
        )

        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            Button(
                onClick = {
                }

            ) {
                Text("Enviar")
            }
            Button(
                onClick = {
                    nome = ""
                    email = ""
                    telefone = ""
                    endereco = ""
                    nascimento = ""
                    objetivo = ""
                    areaAtuacao = ""
                    escolaridade = ""
                    curso = ""
                    instituicao = ""
                    conclusao = ""
                    ultimaProfissao = ""
                    nacionalidade = ""
                    dataSaida = ""
                    dataEntrada = ""
                }

            ) {
                Text("Limpar")
            }

        }

    }
}

