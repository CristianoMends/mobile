package com.example.cruditemapp.ui.view


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cruditemapp.model.Item
import com.example.cruditemapp.viewModel.ItemViewModel

private val primaryColor = Color(0xFF0D47A1)
private val primaryLightColor = Color(0xFF42A5F5)
private val destructiveColor = Color(0xFFD32F2F)
private val backgroundColor = Color(0xFFF7F9FC)
private val cardBackgroundColor = Color.White
private val textPrimaryColor = Color(0xFF212121)
private val textSecondaryColor = Color(0xFF757575)

private val cardShape = RoundedCornerShape(12.dp)
private val buttonShape = RoundedCornerShape(8.dp)


@Composable
fun ItemScreen(
    modifier: Modifier = Modifier,
    viewModel: ItemViewModel = viewModel()
) {
    val items by viewModel.items
    var showDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<Item?>(null) }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            AddItemSection { title, description ->
                viewModel.addItem(Item(title = title, description = description))
            }


            Text(
                text = "Minhas Tarefas",
                style = MaterialTheme.typography.titleLarge,
                color = textPrimaryColor
            )


            LazyColumn(

                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items, key = { it.id }) { item ->
                    ItemCard(
                        item = item,
                        onDelete = { viewModel.deleteItem(item.id) },
                        onUpdateClick = {
                            selectedItem = item
                            showDialog = true
                        }
                    )
                }
            }
        }
    }

    if (showDialog) {
        UpdateItemDialog(
            item = selectedItem,
            onDismiss = { showDialog = false },
            onUpdate = { updatedItem ->
                viewModel.updateItem(updatedItem)
                showDialog = false
            }
        )
    }
}

@Composable
private fun AddItemSection(
    onAddItem: (String, String) -> Unit
) {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (title.text.isNotEmpty()) {
                    onAddItem(title.text, description.text)

                    title = TextFieldValue("")
                    description = TextFieldValue("")
                }
            },
            shape = buttonShape,
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Adicionar")
            Spacer(Modifier.width(8.dp))
            Text(text = "Adicionar Novo Item")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ItemCard(
    item: Item,
    onDelete: () -> Unit,
    onUpdateClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = cardShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
        onClick = onUpdateClick
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = textPrimaryColor
                )
                if (item.description.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = textSecondaryColor
                    )
                }
            }


            Row {
                IconButton(
                    onClick = onUpdateClick,
                    colors = IconButtonDefaults.iconButtonColors(contentColor = primaryLightColor)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar Item")
                }
                IconButton(
                    onClick = onDelete,
                    colors = IconButtonDefaults.iconButtonColors(contentColor = destructiveColor)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Deletar Item")
                }
            }
        }
    }
}


@Composable
fun UpdateItemDialog(
    item: Item?,
    onDismiss: () -> Unit,
    onUpdate: (Item) -> Unit
) {
    if (item == null) return

    var title by remember { mutableStateOf(TextFieldValue(item.title)) }
    var description by remember { mutableStateOf(TextFieldValue(item.description)) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Item") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descrição") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onUpdate(
                        item.copy(
                            title = title.text,
                            description = description.text
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
            ) {
                Text("Salvar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(contentColor = textSecondaryColor)
            ) {
                Text("Cancelar")
            }
        },
        shape = cardShape
    )
}