package com.example.co_leaner.data.network

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.co_leaner.model.Courses
import retrofit2.HttpException
import java.io.IOException
import java.lang.NullPointerException

class CoursesPagingSource(
    private val coursesApiService: CoursesApiService,
    private val courseCategory: String?
) : PagingSource<Int, Courses>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Courses> {

        var uriPrev: Uri? = null
        val uriNext: Uri

        return try {
            val page = params.key ?: DEFAULT_PAGE_INDEX
            val response = coursesApiService.getAllCourses(page, params.loadSize, courseCategory)
            uriNext = Uri.parse(response.next)
            if (page > 1)
                uriPrev = Uri.parse(response.previous)
            LoadResult.Page(
                data = response.results,
                prevKey = uriPrev?.getQueryParameter("previous")?.toInt() ?: page - 1,
                nextKey = uriNext?.getQueryParameter("next")?.toInt() ?: page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (exception: NullPointerException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Courses>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)
                ?.prevKey
                ?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)
                    ?.nextKey
                    ?.minus(1)
        }
    }

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
    }

}
