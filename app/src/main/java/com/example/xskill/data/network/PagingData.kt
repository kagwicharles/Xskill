package com.example.xskill.data.network

import androidx.paging.*
import com.example.xskill.model.Courses
import kotlinx.coroutines.flow.Flow

//Get data from the network
class CoursesRepository {

    private val coursesApiService = CoursesApiService.createApi()

    fun getSearchResultStream(courseCategory: String?): Flow<PagingData<Courses>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                    CoursesPagingSource(coursesApiService, courseCategory)
            }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 12
    }
}