package com.yourdomain.todoapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yourdomain.todoapp.viewmodel.TaskViewModel

@Composable
fun AddTaskScreen(
    onSave: () -> Unit,
    onCancel: () -> Unit,
    viewModel: TaskViewModel = viewModel()
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Task") },
                actions = {
                    TextButton(onClick = onCancel) {
                        Text("Cancel")
                    }
                    TextButton(onClick = {
                        if (title.isNotBlank()) {
                            viewModel.addTask(
                                title = title,
                                description = if (description.isNotBlank()) description else null,
                                priority = priority
                            )
                            onSave()
                        }
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
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Priority")
            Row {
                RadioButton(
                    selected = priority == 0,
                    onClick = { priority = 0 }
                )
                Text("Low", modifier = Modifier.padding(start = 8.dp))
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(
                    selected = priority == 1,
                    onClick = { priority = 1 }
                )
                Text("Medium", modifier = Modifier.padding(start = 8.dp))
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(
                    selected = priority == 2,
                    onClick = { priority = 2 }
                )
                Text("High", modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}
