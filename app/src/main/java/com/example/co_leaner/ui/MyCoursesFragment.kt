package com.example.co_leaner.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.co_leaner.R
import com.example.co_leaner.adapter.MyCoursesAdapter
import com.example.co_leaner.adapter.SwipeHelperCallback
import com.example.co_leaner.databinding.FragmentMyCoursesBinding
import com.example.co_leaner.util.Utils
import com.example.co_leaner.viewmodel.CoursesViewModel
import com.example.co_leaner.viewmodel.MyViewModelFactory

class MyCoursesFragment : Fragment() {

    private lateinit var viewModel: CoursesViewModel

    private var _binding: FragmentMyCoursesBinding? = null
    private val binding get() = _binding!!
    private var coursesAdapter: MyCoursesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMyCoursesBinding.inflate(inflater, container, false)
        Utils.setToolbar(this).setNavigationOnClickListener {
            Utils.openFragment(fragment = this, destination = R.id.homeFragment)
        }
        setHasOptionsMenu(true)

        val rvMyCourses: RecyclerView = binding.rvMyCourses
        coursesAdapter = MyCoursesAdapter(
            MyCoursesAdapter.OnClickListener { openWebPage(it.courseUrl!!) },
            context
        )

        rvMyCourses.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            this.adapter = coursesAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            isNestedScrollingEnabled = false
        }

        viewModel = ViewModelProviders.of(
            this,
            MyViewModelFactory(context)
        )[CoursesViewModel::class.java]
        viewModel.myCourses?.observe(viewLifecycleOwner, {
            coursesAdapter?.submitData(it)
        })

        viewModel.getCourseCount().observe(viewLifecycleOwner, {
            binding.txtCourseCount.text = it.toString()
        })
        ItemTouchHelper(
            SwipeHelperCallback(
                coursesAdapter!!,
                viewModel,
                context,
                this
            )
        ).attachToRecyclerView(rvMyCourses)
        return binding.root
    }

    private fun openWebPage(uri: String) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(activity?.getString(R.string.udemy_base_url, uri))
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.courses_menu, menu)
        val search = menu.findItem(R.id.app_bar_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    coursesAdapter?.filter?.filter(newText)
                    return true
                }
            }
        )
    }
}