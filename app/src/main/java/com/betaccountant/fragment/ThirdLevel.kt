package com.betaccountant.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.betaccountant.R
import kotlinx.android.synthetic.main.fragment_third_level.*

class ThirdLevel : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_third_level, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        bathContainer.setOnClickListener { }
        seaContainer.setOnClickListener { }
        bankContainer.setOnClickListener { }
        businessMeetingContainer.setOnClickListener { }
    }
}