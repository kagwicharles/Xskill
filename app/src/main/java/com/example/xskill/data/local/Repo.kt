package com.example.xskill.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class Repo(val context: Context) {

    private val db: CourseDao = MyDatabase.getDb(context).courseDao()

    val myCourses: Flow<List<Course>>? = db.getMyCourses()
    val myGroups: Flow<List<Group>>? = db.getAllGroups()

    fun insertCourse(course: Course) {
        db.insertCourse(course)
    }

    suspend fun deleteCourse(course: Course): Int {
        return db.deleteCourse(course)
    }

    fun getCourseCount(): LiveData<Int> = db.getCoursesCount()

    fun insertGroup(group: Group) {
        db.insertGroup(group)
    }

    fun deleteGroup(group: Group) {
        db.deleteGroup(group)
    }
}