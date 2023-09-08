package com.example.feature_dashboard.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.core_common.fragment.BaseFragment
import com.example.feature_dashboard.presentation.composeview.DashboardComposeView
import com.example.feature_dashboard.presentation.composeview.LoadingComposeView
import com.example.feature_dashboard.presentation.composeview.RetryComposeView
import com.example.feature_dashboard.databinding.FragmentDashboardComposeBinding
import com.example.feature_dashboard.viewmodels.DashboardIntent
import com.example.feature_dashboard.viewmodels.DashboardViewModel
import com.example.feature_dashboard.viewmodels.DashboardViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardComposeBinding>() {

    private val viewModel by viewModels<DashboardViewModel>()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDashboardComposeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendIntent(DashboardIntent.FetchDashboardData)
        with(viewModel.observableViewState) {
            observe(viewLifecycleOwner) { renderView(it) }
        }
    }

    private fun sendIntent(intent: DashboardIntent.FetchDashboardData) {
        lifecycleScope.launch {
            viewModel.intent.send(intent)
        }
    }

    private fun renderView(viewState: DashboardViewState) {
        when (viewState) {
            is DashboardViewState.DashBoardData -> showWeatherContent(viewState)
            is DashboardViewState.Loading -> showLoadingView()
            is DashboardViewState.Error -> showRetryView()
        }
    }

    private fun showRetryView() =
        binding.composeRootView.setContent { RetryComposeView { sendIntent(DashboardIntent.FetchDashboardData) } }

    private fun showLoadingView() =
        binding.composeRootView.setContent { LoadingComposeView() }

    private fun showWeatherContent(currentWeatherLoaded: DashboardViewState.DashBoardData) =
        binding.composeRootView.setContent { DashboardComposeView(currentWeatherLoaded.currentWeather) }
}
