package com.edu.taskliteapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edu.taskliteapp.model.TaskFilter
import com.edu.taskliteapp.viewModel.DesafioTaskViewModel

@Composable
fun DesafioTaskListScreen(
    viewModel: DesafioTaskViewModel, modifier:
    Modifier = Modifier
) {
    val taskList by viewModel.filteredTasks.collectAsState()
    var newTaskTitle by remember { mutableStateOf("") }
    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("TaskLite (Desafio)", fontSize = 24.sp)
        Spacer(Modifier.height(16.dp))
        Row {
            Button(onClick = {
                viewModel.onFilterSelected(TaskFilter.ALL)
            }) {
                Text("Todas")
            }
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                viewModel.onFilterSelected(TaskFilter.PENDING)
            }) {
                Text("Pendentes")
            }
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                viewModel.onFilterSelected(TaskFilter.COMPLETED)
            }) {
                Text("Concluídas")
            }
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            items(taskList) { task ->
// Substitua TaskItem(...) por:
                TaskItemDesafio(
                    task = task,
                    onCheckedChange = { viewModel.onTaskChecked(task.id) },
                    onDelete = { viewModel.onDeleteTask(task.id) }
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        Row {
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                label = { Text("Nova tarefa") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                viewModel.onAddTask(newTaskTitle)
                newTaskTitle = ""
            }) {
                Text("Adicionar")
            }
        }
    }
}