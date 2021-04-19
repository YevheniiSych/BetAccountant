package com.betaccountant.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.betaccountant.MainActivity
import com.betaccountant.R
import com.betaccountant.enums.Level
import com.betaccountant.fragment.ResultFragment.Companion.IS_FROM_START
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startBtn.setOnClickListener {
            activity?.toolbar?.timeCounterEnabled(true)
            activity?.toolbar?.livesCounterEnabled(true)
            activity?.toolbar?.resetTimeCounter()
            activity?.toolbar?.startTimeCounter()
            activity?.toolbar?.infoButton?.visibility = View.GONE
            (activity as MainActivity).navigateToLevel(Level.SEVENTH)
        }
        activity?.toolbar?.setLabel(R.string.inside_app_name)
        activity?.toolbar?.infoButton?.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean(IS_FROM_START, true)
            (activity as MainActivity).navController?.navigate(R.id.resultFragment, bundle)
        }
    }
}