package com.gimble.assignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gimble.assignment.TheViewModel
import com.gimble.assignment.adapter.RecentLinksAdapter
import com.gimble.assignment.data.remote.dashboardapi.DashboardData
import com.gimble.assignment.databinding.FragmentRecentBinding


class RecentFragment : Fragment() {


    private lateinit var binding: FragmentRecentBinding
    private val viewModel: TheViewModel by lazy {
        ViewModelProvider(this).get(TheViewModel::class.java)}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentRecentBinding.inflate(layoutInflater,container,false)

        initialSetup()
        return binding.root
    }

    private fun initialSetup() {
        viewModel.apiData.observe(viewLifecycleOwner, Observer { data ->
            setuptheList(data)
        })
        viewModel.getDataforDashboard()
    }

    private fun setuptheList(dat : DashboardData) {

        val layoutManager = LinearLayoutManager(context)
        binding.list.layoutManager = layoutManager
        binding.list.setHasFixedSize(true)
        binding.list.adapter = RecentLinksAdapter(dat.data.recent_links)


    }
}