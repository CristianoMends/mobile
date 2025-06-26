package com.edu.taskliteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.edu.taskliteapp.ui.DesafioTaskListScreen
import com.edu.taskliteapp.ui.theme.TaskLiteAppTheme
import com.edu.taskliteapp.viewModel.DesafioTaskViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskLiteAppTheme {
                val viewModel: DesafioTaskViewModel = viewModel()
                DesafioTaskListScreen(viewModel = viewModel)
            }
        }
    }
}