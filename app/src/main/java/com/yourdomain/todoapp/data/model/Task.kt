package com.yourdomain.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.yourdomain.todoapp.utils.Converters
import java.util.Date

@Entity(tableName = "tasks")
@TypeConverters(Converters::class)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val dueDate: Date? = null,
    val isCompleted: Boolean = false,
    val priority: Int = 0, // 0: Low, 1: Medium, 2: High
    val meetingLink: String? = null,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
