package com.betaccountant.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.betaccountant.MainActivity
import com.betaccountant.MainActivity.Companion.FRAGMENT_LEVEL
import com.betaccountant.R
import com.betaccountant.dialog.StoryDialog
import com.betaccountant.enums.Level
import kotlinx.android.synthetic.main.fragment_image_question_level.*

class ImageQuestionLevelFragment : Fragment() {

    companion object {
        private const val FIRST_AND_SECOND_LEVEL_ANSWER = "22"
    }

    private var tryCount = 0
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
            if (answerInput.text.toString() == FIRST_AND_SECOND_LEVEL_ANSWER && currentLevel != null) {
                val nextLevel =
                    Level.values()[currentLevel!!.value] // current value because indices starts from 0
                when (currentLevel) {
                    Level.FIRST -> {
                        (activity as MainActivity).navigateToLevel(nextLevel)
                    }
                    Level.SECOND -> {
                        StoryDialog(
                            requireContext(),
                            getString(R.string.second_to_third_level_story),
                            {
                                (activity as MainActivity).showPromoVideoAndNavigateToLevel(
                                    nextLevel
                                )
                            },
                            ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.man_with_calculator
                            ),
                            true
                        ).show()
                    }
                    else -> Toast.makeText(
                        context,
                        getString(R.string.unexpected_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                tryCount++
                when (tryCount) {
                    3 -> showWrongAnswerDialog(
                        getString(
                            when (currentLevel) {
                                Level.FIRST -> R.string.continue_numbers_line
                                Level.SECOND -> R.string.remember_actives
                                else -> R.string.remember_actives
                            }
                        )
                    )
                    5 -> showWrongAnswerDialog(
                        getString(
                            when (currentLevel) {
                                Level.FIRST -> R.string.first_level_right_answer
                                Level.SECOND -> R.string.second_level_right_answer
                                else -> R.string.second_level_right_answer
                            }
                        )
                    )
                    else -> showWrongAnswerDialog(getString(R.string.wrong_answer))
                }
            }
        }
    }

    private fun showWrongAnswerDialog(text: String) {
        StoryDialog(
            requireContext(),
            text, {}
        ).show()
    }

    private fun setFirstFragment() {
        questionTxt.text = getText(R.string.first_level_question)
        questionImg.setImageResource(R.drawable.first_level_task)
    }

    private fun setSecondFragment() {
        questionTxt.text = getText(R.string.second_level_question)
        questionImg.setImageResource(R.drawable.second_level_task)
    }
}