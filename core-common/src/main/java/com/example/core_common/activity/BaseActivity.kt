package com.example.core_common.activity

import androidx.appcompat.app.AppCompatActivity
import com.example.core_common.navigation.FeatureNavigator
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var featureNavigator: FeatureNavigator
}