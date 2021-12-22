package com.example.co_leaner.room

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class MyCoursesRepo(val context: Context) {

    private val db: CourseDao = MyDatabase.getDb(context).courseDao()

    val myCourses: Flow<List<Course>>? = db.getMyCourses()
    val myGroups: Flow<List<Group>>? = db.getAllGroups()

    fun insertCourse(course: Course) {
        db.insertCourse(course)
    }

    fun deleteCourse(course: Course) {
        db.deleteCourse(course)
    }

    fun getCourseCount(): LiveData<Int> = db.getCoursesCount()

    fun insertGroup(group: Group) {
        db.insertGroup(group)
    }

    fun deleteGroup(group: Group) {
        db.deleteGroup(group)
    }
}