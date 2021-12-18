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
import com.example.co_leaner.adapter.MyCoursesAdapter
import com.example.co_leaner.databinding.FragmentMyCoursesBinding
import com.example.co_leaner.viewmodel.CoursesViewModel
import com.example.co_leaner.viewmodel.CoursesViewModelFactory

class MyCoursesFragment : Fragment() {

    private lateinit var viewModel: CoursesViewModel

    private var _binding: FragmentMyCoursesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMyCoursesBinding.inflate(inflater, container, false)

        val rvMyCourses: RecyclerView = binding.rvMyCourses
        val adapter = MyCoursesAdapter(
            MyCoursesAdapter.OnClickListener { openWebPage(it.courseUrl!!) },
            context
        )

        rvMyCourses.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            isNestedScrollingEnabled = false
        }

        viewModel = ViewModelProviders.of(
            this,
            CoursesViewModelFactory(context)
        )[CoursesViewModel::class.java]
        viewModel.myCourses?.observe(viewLifecycleOwner, {
            adapter.submitData(it)
        })
        return binding.root
    }

    private fun openWebPage(uri: String) {}
}