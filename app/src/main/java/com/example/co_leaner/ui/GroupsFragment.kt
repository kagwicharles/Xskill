package com.example.co_leaner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGroupsBinding.inflate(inflater, container, false)

        Utils.setToolbar(
            fragment = this
        ).setNavigationOnClickListener {
            Utils.openFragment(fragment = this, destination = R.id.homeFragment)
        }

        val adapter = GroupsAdapter(requireContext())
        val recyclerView = binding.rvMyGroups
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter

        viewModel = ViewModelProviders.of(
            this,
            MyViewModelFactory(context)
        )[GroupsViewModel::class.java]
        viewModel.myCourses?.observe(viewLifecycleOwner, {
            adapter.submitData(it)
        })

        return binding.root
    }

}