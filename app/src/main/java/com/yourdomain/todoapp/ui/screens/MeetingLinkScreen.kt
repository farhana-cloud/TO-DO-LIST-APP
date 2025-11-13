package com.yourdomain.todoapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yourdomain.todoapp.data.model.Task
import com.yourdomain.todoapp.viewmodel.TaskViewModel

@Composable
fun MeetingLinkScreen(
    task: Task,
    onSave: () -> Unit,
    onCancel: () -> Unit,
    viewModel: TaskViewModel = viewModel()
) {
    var meetingLink by remember { mutableStateOf(task.meetingLink ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meeting Link") },
                actions = {
                    TextButton(onClick = onCancel) {
                        Text("Cancel")
                    }
                    TextButton(onClick = {
                        val updatedTask = task.copy(meetingLink = if (meetingLink.isNotBlank()) meetingLink else null)
                        viewModel.updateTask(updatedTask)
                        onSave()
                    }) {
                        Text("Save")
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
            Text(
                text = "Task: ${task.title}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = meetingLink,
                onValueChange = { meetingLink = it },
                label = { Text("Meeting Link") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("https://meet.google.com/...") }
            )
        }
    }
}
