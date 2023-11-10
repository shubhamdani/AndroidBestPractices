package com.example.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.common.navigation.FeatureNavigator
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    @Inject
    lateinit var featureNavigator: FeatureNavigator

    private lateinit var _binding: VB

    val binding: VB
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = viewBinding(inflater, container)
        return _binding.root
    }

    abstract fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB
}