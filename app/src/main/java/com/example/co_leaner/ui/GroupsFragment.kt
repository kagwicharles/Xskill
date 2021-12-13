package com.example.co_leaner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.co_leaner.adapter.GroupsAdapter
import com.example.co_leaner.databinding.FragmentGroupsBinding
import com.example.co_leaner.model.Groups
import com.example.co_leaner.util.Utils

class GroupsFragment : Fragment() {

    private var _binding: FragmentGroupsBinding? = null
    private val binding get() = _binding!!
    private var list: List<Groups> = listOf(
        Groups(
            "https://img-c.udemycdn.com/course/240x135/3060786_438f_2.jpg",
            "Data Science",
            9
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGroupsBinding.inflate(inflater, container, false)

        Utils.setToolbar(
            fragment = this
        )

        val adapter = GroupsAdapter(requireContext())
        val recyclerView = binding.rvMyGroups
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter

        adapter.submitData(list)
        return binding.root
    }

}