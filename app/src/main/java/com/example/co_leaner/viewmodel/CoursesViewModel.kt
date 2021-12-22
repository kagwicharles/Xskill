package com.example.co_leaner.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.co_leaner.data.CoursesRepository
import com.example.co_leaner.model.Courses
import com.example.co_leaner.room.Course
import com.example.co_leaner.room.Repo
import kotlinx.coroutines.flow.Flow
import org.jetbrains.anko.doAsync

class CoursesViewModel(context: Context) : ViewModel() {

    private val coursesRepository: CoursesRepository = CoursesRepository()
    private val repo: Repo = Repo(context)

    fun getCourses(courseCategory: String?): Flow<PagingData<Courses>> = coursesRepository
        .getSearchResultStream(courseCategory)
        .cachedIn(viewModelScope)

    val myCourses: LiveData<List<Course>>? = repo.myCourses?.asLiveData()

    fun deleteCourse(course: Course) {
        doAsync {
            repo.deleteCourse(course)
        }
    }

    fun getCourseCount() : LiveData<Int> = repo.getCourseCount()
}