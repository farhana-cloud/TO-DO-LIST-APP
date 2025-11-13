package com.yourdomain.todoapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yourdomain.todoapp.data.model.Task
import com.yourdomain.todoapp.ui.components.TaskCard
import com.yourdomain.todoapp.viewmodel.TaskViewModel

@Composable
fun DashboardScreen(
    onTaskClick: (Task) -> Unit,
    onAddTaskClick: () -> Unit,
    viewModel: TaskViewModel = viewModel()
) {
    val tasks by viewModel.tasks.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTaskClick) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "My Tasks",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyColumn {
                items(tasks) { task ->
                    TaskCard(
                        task = task,
                        onClick = { onTaskClick(task) }
                    )
                }
            }
        }
    }
}
