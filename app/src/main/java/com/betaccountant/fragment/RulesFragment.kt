package com.betaccountant.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betaccountant.MainActivity
import com.betaccountant.R
import com.betaccountant.enums.Level
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_rules.*


class RulesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_rules, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        continueRulesBtn.setOnClickListener {
            activity?.toolbar?.timeCounterEnabled(true)
            activity?.toolbar?.livesCounterEnabled(true)
            activity?.toolbar?.resetTimeCounter()
            activity?.toolbar?.startTimeCounter()
            (activity as MainActivity).navigateToLevel(Level.SIXTH)
        }
    }
}