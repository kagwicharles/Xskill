package com.example.co_leaner.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {

    @Query("SELECT * FROM course")
    fun getMyCourses() : Flow<List<Course>>?

    @Insert
    fun insertCourse(course: Course)
}