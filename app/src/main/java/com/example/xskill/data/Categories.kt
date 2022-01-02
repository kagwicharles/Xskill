package com.example.xskill.data

import com.example.xskill.R
import com.example.xskill.model.CourseCategories

class CategoriesData {

    companion object {
        fun getCourseCategories(): ArrayList<CourseCategories> {
            val courseCategories = ArrayList<CourseCategories>().apply {
                add(CourseCategories("IT & Software", R.drawable.software))
                add(CourseCategories("Business", R.drawable.briefcase))
                add(CourseCategories("Fitness & Health", R.drawable.fitness))
                add(CourseCategories("Finance & Accounting", R.drawable.finance))
                add(CourseCategories("Development", R.drawable.development))
                add(CourseCategories("Lifestyle", R.drawable.lifestyle))
                add(CourseCategories("Marketing", R.drawable.marketing))
                add(CourseCategories("Music", R.drawable.music))
                add(CourseCategories("Office Productivity", R.drawable.productivity))
                add(CourseCategories("Personal Development", R.drawable.personal_growth))
                add(CourseCategories("Photography & Video", R.drawable.photography))
                add(CourseCategories("Teaching & Academics", R.drawable.tutor))

            }
            return courseCategories
        }
    }

}