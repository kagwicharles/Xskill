package com.example.xskill.data.network

import com.example.xskill.model.Courses
import com.example.xskill.model.CoursesParent
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

//Retrofit service to get data from udemy affiliate api
interface CoursesApiService {

    @Headers(
        "Accept: application/json, text/plain, */*",
        "Authorization: Basic cHR5RGZmTjhZTHRwSzJ3cFUxa2plVzF2cm5tRXBncGhzOG54UE9oZTppQ3VIdDdlRlJLMTQzNUZUWTJMZzAxREtVTGJSVGhadVlkY0ZVZW5TNWI3UzJ1dnJVbGt5bFhLaVRpdno0N256UTFkekR3S1NqdFFTd0Q3Y0hVV2YyQm9Sc0tkMWtialBjQkxGSWpuQjFLOHY4WDNXSVBucWg3OWxDblJXQ0w0eA==",
        "Content_Type: application/json;charset=utf-8"
    )
    @GET("courses")
    suspend fun getAllCourses(
        @Query("page") page: Int,
        @Query("page_size") page_size: Int,
        @Query("category") courseCategory: String?
    ): CoursesParent

    @Headers(
        "Accept: application/json, text/plain, */*",
        "Authorization: Basic cHR5RGZmTjhZTHRwSzJ3cFUxa2plVzF2cm5tRXBncGhzOG54UE9oZTppQ3VIdDdlRlJLMTQzNUZUWTJMZzAxREtVTGJSVGhadVlkY0ZVZW5TNWI3UzJ1dnJVbGt5bFhLaVRpdno0N256UTFkekR3S1NqdFFTd0Q3Y0hVV2YyQm9Sc0tkMWtialBjQkxGSWpuQjFLOHY4WDNXSVBucWg3OWxDblJXQ0w0eA==",
        "Content_Type: application/json;charset=utf-8"
    )
    @GET("courses/{pk}")
    fun getAllCourseDetails(
        @Path("pk") courseId: Int?
    ): Call<Courses>

    companion object {
        private var BASE_URL = "https://www.udemy.com/api-2.0/"
        fun createApi(): CoursesApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(CoursesApiService::class.java)
        }
    }

}
