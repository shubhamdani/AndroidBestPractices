package com.example.dashboard.presentation.activity

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.common.activity.BaseActivity
import com.example.dashboard.R
import com.example.dashboard.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity() {

    private lateinit var binding: ActivityDashboardBinding

    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_dashboard) as NavHostFragment

        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}