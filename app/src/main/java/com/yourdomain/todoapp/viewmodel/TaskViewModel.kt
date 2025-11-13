package com.yourdomain.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourdomain.todoapp.data.model.Task
import com.yourdomain.todoapp.data.model.Subtask
import com.yourdomain.todoapp.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    private val _currentTask = MutableStateFlow<Task?>(null)
    val currentTask: StateFlow<Task?> = _currentTask.asStateFlow()

    private val _subtasks = MutableStateFlow<List<Subtask>>(emptyList())
    val subtasks: StateFlow<List<Subtask>> = _subtasks.asStateFlow()

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            repository.getAllTasks().collect { taskList ->
                _tasks.value = taskList
            }
        }
    }

    fun loadTaskById(taskId: Long) {
        viewModelScope.launch {
            repository.getTaskById(taskId).collect { task ->
                _currentTask.value = task
                loadSubtasksForTask(taskId)
            }
        }
    }

    private fun loadSubtasksForTask(taskId: Long) {
        viewModelScope.launch {
            repository.getSubtasksForTask(taskId).collect { subtaskList ->
                _subtasks.value = subtaskList
            }
        }
    }

    fun addTask(title: String, description: String? = null, dueDate: Date? = null, priority: Int = 0, meetingLink: String? = null) {
        viewModelScope.launch {
            val task = Task(
                title = title,
                description = description,
                dueDate = dueDate,
                priority = priority,
                meetingLink = meetingLink,
                createdAt = Date(),
                updatedAt = Date()
            )
            repository.insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            val updatedTask = task.copy(updatedAt = Date())
            repository.updateTask(updatedTask)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun addSubtask(taskId: Long, title: String) {
        viewModelScope.launch {
            val subtask = Subtask(taskId = taskId, title = title)
            repository.insertSubtask(subtask)
        }
    }

    fun updateSubtask(subtask: Subtask) {
        viewModelScope.launch {
            repository.updateSubtask(subtask)
        }
    }

    fun deleteSubtask(subtask: Subtask) {
        viewModelScope.launch {
            repository.deleteSubtask(subtask)
        }
    }
}
