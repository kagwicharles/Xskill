package com.example.co_leaner.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.co_leaner.data.local.Course
import com.example.co_leaner.data.network.CoursesRepository
import com.example.co_leaner.model.Courses
import com.example.co_leaner.data.local.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CoursesViewModel(context: Context) : ViewModel() {

    private val coursesRepository: CoursesRepository = CoursesRepository()
    private val repo: Repo = Repo(context)

    fun getCourses(courseCategory: String?): Flow<PagingData<Courses>> = coursesRepository
        .getSearchResultStream(courseCategory)
        .cachedIn(viewModelScope)

    val myCourses: LiveData<List<Course>>? = repo.myCourses?.asLiveData()

    fun deleteCourse(course: Course) {
        viewModelScope.launch {
            repo.deleteCourse(course)
        }
    }

    fun getCourseCount() : LiveData<Int> = repo.getCourseCount()
}