package com.example.bestpractices.navigator

import android.content.Context
import android.content.Intent
import com.example.core_common.navigation.FeatureNavigator
import com.example.feature_dashboard.activity.DashboardActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppFeatureNavigator @Inject constructor() : FeatureNavigator {

    override fun navigateToDashboardScreen(context: Context) {
        val intent = Intent(context, DashboardActivity::class.java)
        context.startActivity(intent)
    }
}