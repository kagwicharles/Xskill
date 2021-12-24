package com.example.co_leaner.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.co_leaner.R
import com.example.co_leaner.adapter.GroupsAdapter
import com.example.co_leaner.databinding.FragmentGroupsBinding
import com.example.co_leaner.util.Utils
import com.example.co_leaner.viewmodel.GroupsViewModel
import com.example.co_leaner.viewmodel.MyViewModelFactory

class GroupsFragment : Fragment() {

    private var _binding: FragmentGroupsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GroupsViewModel

    private var bottomSheet: BottomSheet? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGroupsBinding.inflate(inflater, container, false)
        bottomSheet = BottomSheet()

        Utils.setToolbar(
            fragment = this
        ).setNavigationOnClickListener {
            Utils.openFragment(fragment = this, destination = R.id.homeFragment)
        }
        setHasOptionsMenu(true)

        val adapter = GroupsAdapter(requireContext())
        val recyclerView = binding.rvMyGroups
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false

        viewModel = ViewModelProviders.of(
            this,
            MyViewModelFactory(context)
        )[GroupsViewModel::class.java]
        viewModel.myCourses?.observe(viewLifecycleOwner, {
            adapter.submitData(it)
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.groups_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_group) {
            bottomSheet?.show(requireActivity().supportFragmentManager, "")
        }
        return super.onOptionsItemSelected(item)
    }
}