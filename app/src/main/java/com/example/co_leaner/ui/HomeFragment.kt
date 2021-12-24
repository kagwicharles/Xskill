package com.example.co_leaner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.co_leaner.R
import com.example.co_leaner.adapter.CategoriesAdapter
import com.example.co_leaner.data.CategoriesData.Companion.getCourseCategories
import com.example.co_leaner.databinding.FragmentHomeBinding
import com.example.co_leaner.util.Utils

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        Utils.setToolbar(
            fragment = this
        ).setNavigationOnClickListener {
            Utils.openFragment(fragment = this, destination = R.id.homeFragment)
        }

        val categoriesAdapter = CategoriesAdapter(CategoriesAdapter.OnClickListener {
            Utils.openFragment(this, setBundle(it.categoryName), R.id.coursesFragment)
        }, context, getCourseCategories())

        binding.rvCourseCategories.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = categoriesAdapter
        }
        return binding.root
    }

    private fun setBundle(courseCategory: String): Bundle {
        val bundle = Bundle()
        bundle.putString("COURSE_CATEGORY", courseCategory)
        return bundle
    }
}