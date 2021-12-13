package com.example.co_leaner.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CoursesViewModelFactory(val context: Context?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoursesViewModel::class.java)) return CoursesViewModel(
            context!!
        ) as T
        throw IllegalArgumentException("ViewModel Unknown")
    }
}