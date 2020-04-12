package com.example.feature_pincode.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core.presentation.BaseFragment
import com.example.feature_pincode.databinding.FragmentPincodeBinding

class PincodeFragment: BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentPincodeBinding.inflate(inflater, container, false).root
    }
}
