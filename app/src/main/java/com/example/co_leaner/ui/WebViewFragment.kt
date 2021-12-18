package com.example.co_leaner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.co_leaner.databinding.FragmentWebViewBinding
import com.example.co_leaner.util.Utils

class WebViewFragment : Fragment() {

    private var _binding: FragmentWebViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)
        Utils.setToolbar(this)

        binding.webView.loadUrl(arguments?.getString("COURSE_URL")!!)
        return binding.root
    }
}