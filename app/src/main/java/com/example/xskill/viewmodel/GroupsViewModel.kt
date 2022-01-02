package com.example.xskill.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.xskill.data.local.Group
import com.example.xskill.data.local.Repo

class GroupsViewModel(context: Context) : ViewModel() {

    private val repo: Repo = Repo(context)
    val myCourses: LiveData<List<Group>>? = repo.myGroups?.asLiveData()

}