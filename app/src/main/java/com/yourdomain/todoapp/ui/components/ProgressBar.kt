package com.yourdomain.todoapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yourdomain.todoapp.data.model.Subtask

@Composable
fun ProgressBar(
    subtasks: List<Subtask>,
    modifier: Modifier = Modifier
) {
    val completedCount = subtasks.count { it.isCompleted }
    val totalCount = subtasks.size
    val progress = if (totalCount > 0) completedCount.toFloat() / totalCount.toFloat() else 0f

    Column(modifier = modifier) {
        Text(
            text = "Progress: $completedCount / $totalCount",
            style = MaterialTheme.typography.bodySmall
        )
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .padding(top = 4.dp)
        )
    }
}
