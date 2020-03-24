package com.example.feature_mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.feature_mainscreen.databinding.FragmentMainBinding

class SessionMockFragment : Fragment() {

    companion object {
        fun newInstance() = SessionMockFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMainBinding.inflate(inflater, container, false).root
    }
}
