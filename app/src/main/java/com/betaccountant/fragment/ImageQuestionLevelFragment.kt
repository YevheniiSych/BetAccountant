package com.betaccountant.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.betaccountant.MainActivity
import com.betaccountant.MainActivity.Companion.FRAGMENT_LEVEL
import com.betaccountant.R
import com.betaccountant.dialog.StoryDialog
import com.betaccountant.enums.Level
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_image_question_level.*

class ImageQuestionLevelFragment : Fragment() {

    companion object {
        private const val LEVEL_ANSWER = "22"
    }

    private var currentLevel: Level? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_question_level, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        currentLevel = (arguments?.getSerializable(FRAGMENT_LEVEL) as Level)
        when (currentLevel) {
            Level.FIRST -> setFirstFragment()
            Level.SECOND -> setSecondFragment()
            else -> Toast.makeText(context, getString(R.string.unexpected_error), Toast.LENGTH_LONG)
                .show()
        }
        answerBtn.setOnClickListener {
            if (answerInput.text.toString() == LEVEL_ANSWER && currentLevel != null) {
                val nextLevel =
                    Level.values()[currentLevel!!.value] // current value because indices starts from 0
                when (currentLevel) {
                    Level.FIRST -> {
                        (activity as MainActivity).navigateToLevel(nextLevel)
                    }
                    Level.SECOND -> {
                        StoryDialog.getInstance(
                            requireContext(),
                            getString(R.string.second_to_third_level_story)
                        ) {
                            (activity as MainActivity).navigateToLevel(nextLevel)
                        }.show()
                    }
                    else -> Toast.makeText(
                        context,
                        getString(R.string.unexpected_error),
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }
    }

    private fun setFirstFragment() {
        activity?.toolbar?.timeCounterEnabled(true)
        activity?.toolbar?.resetTimeCounter()
        activity?.toolbar?.startTimeCounter()
        questionTxt.text = getText(R.string.first_level_question)
        questionImg.setImageResource(R.drawable.first_level_task)
    }

    private fun setSecondFragment() {
        questionTxt.text = getText(R.string.second_level_question)
        questionImg.setImageResource(R.drawable.second_level_task)
    }
}