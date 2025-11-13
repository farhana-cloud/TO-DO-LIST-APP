package com.yourdomain.todoapp.repository

import com.yourdomain.todoapp.data.local.TaskDao
import com.yourdomain.todoapp.data.model.Task
import com.yourdomain.todoapp.data.model.Subtask
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {

    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    fun getTaskById(taskId: Long): Flow<Task> = taskDao.getTaskById(taskId)

    suspend fun insertTask(task: Task): Long = taskDao.insertTask(task)

    suspend fun updateTask(task: Task) = taskDao.updateTask(task)

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)

    fun getSubtasksForTask(taskId: Long): Flow<List<Subtask>> = taskDao.getSubtasksForTask(taskId)

    suspend fun insertSubtask(subtask: Subtask): Long = taskDao.insertSubtask(subtask)

    suspend fun updateSubtask(subtask: Subtask) = taskDao.updateSubtask(subtask)

    suspend fun deleteSubtask(subtask: Subtask) = taskDao.deleteSubtask(subtask)
}
