package com.example.co_leaner.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.co_leaner.room.Group
import com.example.co_leaner.room.Repo

class GroupsViewModel(context: Context) : ViewModel() {

    private val repo: Repo = Repo(context)
    val myCourses: LiveData<List<Group>>? = repo.myGroups?.asLiveData()

}