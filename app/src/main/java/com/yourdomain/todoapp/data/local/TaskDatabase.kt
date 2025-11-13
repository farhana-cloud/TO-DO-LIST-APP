package com.yourdomain.todoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yourdomain.todoapp.data.model.Task
import com.yourdomain.todoapp.data.model.Subtask

@Database(
    entities = [Task::class, Subtask::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        const val DATABASE_NAME = "task_database"
    }
}
