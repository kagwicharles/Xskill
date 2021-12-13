package com.example.co_leaner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.co_leaner.R
import com.example.co_leaner.adapter.MyCoursesAdapter
import com.example.co_leaner.util.Utils
import com.example.co_leaner.viewmodel.CoursesViewModel
import com.example.co_leaner.viewmodel.CoursesViewModelFactory

class MyCoursesFragment : Fragment() {

    private lateinit var viewModel: CoursesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_courses, container, false)
        val rvMyCourses: RecyclerView = view.findViewById(R.id.rvMyCourses)
        inflater.inflate(R.layout.fragment_groups, container, false)
        val adapter = MyCoursesAdapter(context)

        rvMyCourses.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMyCourses.setHasFixedSize(true)
        rvMyCourses.adapter = adapter
        rvMyCourses.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        viewModel = ViewModelProviders.of(
            this,
            CoursesViewModelFactory(context)
        )[CoursesViewModel::class.java]
        viewModel.myCourses?.observe(viewLifecycleOwner, {
            adapter.submitData(it)
        })
        return view
    }
}