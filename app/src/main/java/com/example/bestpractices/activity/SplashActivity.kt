package com.example.bestpractices.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.example.bestpractices.databinding.ActivitySplashBinding
import com.example.bestpractices.viewmodel.SplashCommands
import com.example.bestpractices.viewmodel.SplashViewModel
import com.example.core_common.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        viewModel.command.observe(this@SplashActivity) { it?.let { execute(it) } }
    }

    private fun execute(splashCommands: SplashCommands) {
        when (splashCommands) {
            SplashCommands.NavigateToDashboard -> featureNavigator.navigateToDashboardScreen(
                this
            )
        }
    }
}