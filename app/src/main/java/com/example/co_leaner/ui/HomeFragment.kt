package com.example.co_leaner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
        ).setNavigationOnClickListener {
            Utils.openFragment(fragment = this, destination = R.id.homeFragment)
        }

        val adapter = CategoriesAdapter(CategoriesAdapter.OnClickListener {
            Utils.openFragment(this, setBundle(it.categoryName), R.id.coursesFragment)
        }, context, getCourseCategories())

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvCourseCategories)
        val layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        return view
    }

    private fun setBundle(courseCategory: String): Bundle {
        val bundle = Bundle()
        bundle.putString("COURSE_CATEGORY", courseCategory)
        return bundle
    }
}