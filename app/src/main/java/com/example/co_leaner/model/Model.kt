package com.example.co_leaner.model

data class CoursesParent(
    var next: String,
    var previous: String,
    var results: ArrayList<Courses>
)

data class Courses(
    var id: Int,
    var _class: String,
    var title: String,
    var price: String,
    var image_240x135: String,
    var image_480x270: String,
    var url: String,
    var visible_instructors: ArrayList<Instructors>
)

data class Instructors(
    var _class: String,
    var title: String,
    var job_title: String,
    var image_100x100: String,
    var url: String
)

data class CourseCategories(
    var categoryName: String,
    var categoryImage: Int,
)
