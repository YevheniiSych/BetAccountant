package com.betaccountant.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.betaccountant.MainActivity.Companion.FRAGMENT_LEVEL
import com.betaccountant.R
import com.betaccountant.enums.Level
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first_level.*

class FirstLevelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first_level, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        when(arguments?.getSerializable(FRAGMENT_LEVEL)){
            Level.FIRST -> setFirstFragment()
            Level.SECOND -> setSecondFragment()
        }
    }

    private fun setFirstFragment(){
        activity?.toolbar?.timeCounterEnabled(true)
        activity?.toolbar?.resetTimeCounter()
        activity?.toolbar?.startTimeCounter()
        questionTxt.text = getText(R.string.first_level_question)
    }

    private fun setSecondFragment(){
        questionTxt.text = getText(R.string.second_level_question)
    }
}