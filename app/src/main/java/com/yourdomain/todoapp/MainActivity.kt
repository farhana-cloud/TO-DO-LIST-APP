package com.yourdomain.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yourdomain.todoapp.data.model.Task
import com.yourdomain.todoapp.ui.screens.*
import com.yourdomain.todoapp.ui.theme.ToDoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "dashboard") {
                    composable("dashboard") {
                        DashboardScreen(
                            onTaskClick = { task ->
                                navController.navigate("task_detail/${task.id}")
                            },
                            onAddTaskClick = {
                                navController.navigate("add_task")
                            }
                        )
                    }
                    composable("task_detail/{taskId}") { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getString("taskId")?.toLongOrNull()
                        taskId?.let {
                            TaskDetailScreen(
                                taskId = it,
                                onEditClick = { task ->
                                    navController.navigate("edit_task/${task.id}")
                                },
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                    composable("edit_task/{taskId}") { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getString("taskId")?.toLongOrNull()
                        // For simplicity, assume task is passed or fetched; in real app, use ViewModel
                        // Here, we need to pass the task, but for demo, we'll skip
                        // EditTaskScreen(task = task, onSave = { navController.popBackStack() }, onCancel = { navController.popBackStack() })
                    }
                    composable("add_task") {
                        AddTaskScreen(
                            onSave = { navController.popBackStack() },
                            onCancel = { navController.popBackStack() }
                        )
                    }
                    composable("meeting_link/{taskId}") { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getString("taskId")?.toLongOrNull()
                        // Similar issue, need task
                        // MeetingLinkScreen(task = task, onSave = { navController.popBackStack() }, onCancel = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}
