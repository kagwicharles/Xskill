package com.example.co_leaner.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "course")
data class Course(
    @PrimaryKey val courseId: Int?,
    @ColumnInfo(name = "_class") val _class: String?,
    @ColumnInfo(name = "courseTitle") val courseTitle: String?,
    @ColumnInfo(name = "courseImage") val courseImage: String?,
    @ColumnInfo(name = "courseUrl") val courseUrl: String?
)

@Entity(tableName = "group")
data class Group(
    @PrimaryKey val groupId: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "participants") val participants: Int?,
    @ColumnInfo(name = "image") val image: String?
)
