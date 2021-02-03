package com.betaccountant.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betaccountant.MainActivity
import com.betaccountant.R
import kotlinx.android.synthetic.main.activity_main.*

class FirstLevelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first_level, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.toolbar?.timeCounterEnabled(true)
        activity?.toolbar?.resetTimeCounter()
        activity?.toolbar?.startTimeCounter()
    }
}