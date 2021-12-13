package com.example.co_leaner.room

import android.content.Context
import kotlinx.coroutines.flow.Flow

class MyCoursesRepo(val context: Context) {

    private val db: CourseDao = MyDatabase.getDb(context).courseDao()

    val myCourses: Flow<List<Course>>? =  db.getMyCourses()

    fun insertCourse(course: Course) {
        db.insertCourse(course)
    }
}