package com.example.bestpractices.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import com.example.bestpractices.databinding.ActivitySplashBinding
import com.example.bestpractices.viewmodel.SplashCommand
import com.example.bestpractices.viewmodel.SplashViewModel
import com.example.core_common.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(viewModel.observableCommand) {
            observe(this@SplashActivity) { it?.let { execute(it) } }
        }
    }

    private fun execute(splashCommands: SplashCommand) {
        when (splashCommands) {
            SplashCommand.NavigateToDashboard -> {
                featureNavigator.navigateToDashboardScreen(
                    this
                )
                finishAffinity()
            }
        }
    }
}