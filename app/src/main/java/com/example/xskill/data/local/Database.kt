package com.example.xskill.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(entities = [Course::class, Group::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao

    companion object {
        fun getDb(context: Context): MyDatabase {
            return Room.databaseBuilder(
                context,
                MyDatabase::class.java,
                "MyCoursesDb"
            ).build()
        }
    }
}