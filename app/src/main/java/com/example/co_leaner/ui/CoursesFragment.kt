package com.example.co_leaner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.co_leaner.R
import com.example.co_leaner.adapter.CoursesAdapter
import com.example.co_leaner.viewmodel.CoursesViewModel
import com.example.co_leaner.viewmodel.CoursesViewModelFactory
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CoursesFragment : Fragment() {

    private lateinit var viewModel: CoursesViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_courses, container, false)

        val rvCourses: RecyclerView = view.findViewById(R.id.rvCourses)
        progressIndicator = view?.findViewById(R.id.progressIndicator)
        progressIndicator?.visibility = GONE

        val courseCategory = arguments?.getString("COURSE_CATEGORY")
        val toolbar = (activity as AppCompatActivity).supportActionBar
        toolbar?.title = courseCategory
        toolbar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_40)
        toolbar?.setDisplayHomeAsUpEnabled(true)

        val adapter = CoursesAdapter(CoursesAdapter.OnClickListener {
            openCourseDetailFragment(it!!.id)
        }, context)

        rvCourses.layoutManager = GridLayoutManager(context, 2)
        rvCourses.adapter = adapter

        viewModel = ViewModelProviders.of(
            this,
            CoursesViewModelFactory(context)
        )[CoursesViewModel::class.java]
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCourses(courseCategory).collectLatest {
                adapter.submitData(it)
                rvCourses.adapter = adapter
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                when {
                    loadStates.source.append is LoadState.Loading -> showProgress(true)
                    loadStates.source.append is LoadState.NotLoading -> showProgress(false)
                    loadStates.source.refresh is LoadState.Loading -> showProgress(true)
                    loadStates.source.refresh is LoadState.NotLoading -> showProgress(false)
                    loadStates.source.prepend is LoadState.Loading -> showProgress(true)
                    loadStates.source.prepend is LoadState.NotLoading -> showProgress(false)
                }
            }
        }
        return view
    }

    companion object {
        var progressIndicator: LinearProgressIndicator? = null
        fun showProgress(isLoading: Boolean) {
            if (isLoading) progressIndicator?.visibility =
                VISIBLE else progressIndicator?.visibility = GONE
        }
    }


    private fun openCourseDetailFragment(courseId: Int) {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        val navController = navHostFragment?.navController
        val bundle = Bundle()
        bundle.putInt("COURSE_ID", courseId)
        navController?.navigate(R.id.courseDetailFragment, bundle)
    }
}