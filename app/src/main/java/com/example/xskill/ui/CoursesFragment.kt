package com.example.xskill.ui

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xskill.R
import com.example.xskill.adapter.CoursesAdapter
import com.example.xskill.databinding.FragmentCoursesBinding
import com.example.xskill.util.Utils
import com.example.xskill.viewmodel.CoursesViewModel
import com.example.xskill.viewmodel.MyViewModelFactory
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CoursesFragment : Fragment() {

    private var _binding: FragmentCoursesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CoursesViewModel

    private var gridLayoutManager: GridLayoutManager? = null
    private var scrollPosition: Int? = -1
    private var rvCourses: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCoursesBinding.inflate(inflater, container, false)

        val transitionInflater = TransitionInflater.from(requireContext())
        exitTransition = transitionInflater.inflateTransition(R.transition.fade)

        rvCourses = binding.rvCourses
        progressIndicator = binding.progressIndicator
        progressIndicator?.visibility = GONE

        val courseCategory = arguments?.getString("COURSE_CATEGORY")
        Utils.setToolbar(this, R.drawable.ic_baseline_arrow_back_ios_40, courseCategory)
            .setNavigationOnClickListener {
                Utils.openFragment(fragment = this, destination = R.id.homeFragment)
            }

        val coursesAdapter = CoursesAdapter(CoursesAdapter.OnClickListener {
            Utils.openFragment(
                this,
                setBundle(it!!.id),
                R.id.courseDetailFragment
            )
        }, context)

        gridLayoutManager = GridLayoutManager(context, 2)

        rvCourses?.apply {
            layoutManager = gridLayoutManager
            adapter = coursesAdapter
        }

        viewModel = ViewModelProviders.of(
            this,
            MyViewModelFactory(context)
        )[CoursesViewModel::class.java]
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCourses(courseCategory).collectLatest {
                coursesAdapter.submitData(it)
                rvCourses?.adapter = coursesAdapter
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            coursesAdapter.loadStateFlow.collectLatest { loadStates ->
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
        return binding.root
    }

    companion object {
        var progressIndicator: LinearProgressIndicator? = null
        fun showProgress(isLoading: Boolean) {
            if (isLoading) progressIndicator?.visibility =
                VISIBLE else progressIndicator?.visibility = GONE
        }
    }

    private fun setBundle(courseId: Int): Bundle {
        val bundle = Bundle()
        bundle.putInt("COURSE_ID", courseId)
        return bundle
    }

    override fun onPause() {
        super.onPause()
        if (scrollPosition != -1)
            scrollPosition = gridLayoutManager?.findLastVisibleItemPosition()
    }

    override fun onResume() {
        super.onResume()
        rvCourses?.scrollToPosition(scrollPosition!!)
    }
}