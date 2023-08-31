package com.example.feature_dashboard.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core_common.fragment.BaseFragment
import com.example.feature_dashboard.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(inflater, container, false)
    }
}