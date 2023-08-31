package com.example.feature_dashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.core_common.fragment.BaseFragment
import com.example.feature_dashboard.databinding.FragmentDashboardBinding
import com.example.feature_dashboard.viewmodels.DashboardViewModel
import com.example.feature_dashboard.viewmodels.DashboardViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    private val viewModel by viewModels<DashboardViewModel>()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                DashboardViewState.Weather -> Toast.makeText(context, "Loaded", Toast.LENGTH_SHORT)
                    .show()
                DashboardViewState.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT)
                    .show()
                DashboardViewState.Error -> Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}