package com.example.co_leaner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.co_leaner.R
import com.example.co_leaner.adapter.CategoriesAdapter
import com.example.co_leaner.data.CategoriesData.Companion.getCourseCategories
import com.example.co_leaner.util.Utils

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        Utils.setToolbar(
            fragment = this
        )

        val adapter = CategoriesAdapter(CategoriesAdapter.OnClickListener {
            openCoursesFragment(it.categoryName)
        }, context, getCourseCategories())

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvCourseCategories)
        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        return view
    }

    private fun openCoursesFragment(courseCategory: String) {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        val navController = navHostFragment?.navController
        val bundle = Bundle()
        bundle.putString("COURSE_CATEGORY", courseCategory)
        navController?.navigate(R.id.coursesFragment, bundle)
    }
}