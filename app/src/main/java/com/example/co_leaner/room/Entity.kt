package com.example.co_leaner.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class Course(
    @PrimaryKey val courseId: Int?,
    @ColumnInfo(name = "_class") val _class: String?,
    @ColumnInfo(name = "courseTitle") val courseTitle: String?,
    @ColumnInfo(name = "courseImage") val courseImage: String?
)