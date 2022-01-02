package com.example.xskill.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {

    @Query("SELECT * FROM course")
    fun getMyCourses() : Flow<List<Course>>?

    @Insert
    fun insertCourse(course: Course)

    @Delete
    suspend fun deleteCourse(course: Course): Int

    @Query("SELECT COUNT(DISTINCT courseId) FROM course")
    fun getCoursesCount() : LiveData<Int>

    @Query("SELECT* FROM `group`")
    fun getAllGroups() : Flow<List<Group>>?

    @Insert
    fun insertGroup(group: Group)

    @Delete
    fun deleteGroup(group: Group)
}