package com.example.feature_dashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.core_common.fragment.BaseFragment
import com.example.feature_dashboard.composeview.DashboardComposeView
import com.example.feature_dashboard.composeview.LoadingComposeView
import com.example.feature_dashboard.composeview.RetryComposeView
import com.example.feature_dashboard.databinding.FragmentDashboardComposeBinding
import com.example.feature_dashboard.viewmodels.DashboardViewModel
import com.example.feature_dashboard.viewmodels.DashboardViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardComposeBinding>() {

    private val viewModel by viewModels<DashboardViewModel>()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDashboardComposeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchDashboardData()
        with(viewModel.viewState) {
            observe(viewLifecycleOwner) { renderView(it) }
        }
    }

    private fun renderView(viewState: DashboardViewState) {
        when (viewState) {
            is DashboardViewState.CurrentWeatherLoaded -> showWeatherContent(viewState)
            DashboardViewState.Loading -> showLoadingView()
            DashboardViewState.Error -> showRetryView()
        }
    }

    private fun showRetryView() =
        binding.composeRootView.setContent { RetryComposeView(viewModel::fetchDashboardData) }

    private fun showLoadingView() =
        binding.composeRootView.setContent { LoadingComposeView() }

    private fun showWeatherContent(currentWeatherLoaded: DashboardViewState.CurrentWeatherLoaded) =
        binding.composeRootView.setContent { DashboardComposeView(currentWeatherLoaded.currentWeather) }
}
