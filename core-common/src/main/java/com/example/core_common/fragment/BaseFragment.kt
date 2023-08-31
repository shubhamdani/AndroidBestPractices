package com.example.core_common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.core_common.navigation.FeatureNavigator
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    @Inject
    lateinit var featureNavigator: FeatureNavigator

    private lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = viewBinding(inflater, container)
        return binding.root
    }

    abstract fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB
}