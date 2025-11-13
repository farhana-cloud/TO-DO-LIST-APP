package com.yourdomain.todoapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yourdomain.todoapp.data.model.Task
import com.yourdomain.todoapp.ui.components.ProgressBar
import com.yourdomain.todoapp.viewmodel.TaskViewModel

@Composable
fun TaskDetailScreen(
    taskId: Long,
    onEditClick: (Task) -> Unit,
    onBackClick: () -> Unit,
    viewModel: TaskViewModel = viewModel()
) {
    val task by viewModel.currentTask.collectAsState()
    val subtasks by viewModel.subtasks.collectAsState()

    LaunchedEffect(taskId) {
        viewModel.loadTaskById(taskId)
    }

    task?.let { currentTask ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(currentTask.title) },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Text("<")
                        }
                    },
                    actions = {
                        IconButton(onClick = { onEditClick(currentTask) }) {
                            Text("Edit")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                currentTask.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                ProgressBar(subtasks = subtasks, modifier = Modifier.padding(bottom = 16.dp))
                Text(
                    text = "Subtasks",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LazyColumn {
                    items(subtasks) { subtask ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = subtask.isCompleted,
                                onCheckedChange = { isChecked ->
                                    viewModel.updateSubtask(subtask.copy(isCompleted = isChecked))
                                }
                            )
                            Text(
                                text = subtask.title,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
