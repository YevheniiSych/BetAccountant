package com.betaccountant.fragment

import android.media.MediaPlayer
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
import com.betaccountant.db.AccountantDB
import com.betaccountant.dialog.StoryDialog
import com.betaccountant.enums.Level
import kotlinx.android.synthetic.main.fragment_image_question_level.*
import kotlinx.android.synthetic.main.fragment_sixth_level.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ImageQuestionLevelFragment : Fragment() {

    private var tryCount = 0
    private var currentLevel: Level? = null
    private var answer: String? = null

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
            if (answerInput.text.toString() == answer && currentLevel != null) {
                handleRightAnswer()
            } else {
                handleWrongAnswer()
            }
        }
    }

    private fun showWrongAnswerDialog(text: String) {
        StoryDialog(
            requireContext(),
            text, {}
        ).show()
    }

    private fun setFirstFragment() = GlobalScope.launch {
        val task = AccountantDB.getInstance(requireContext()).taskDao().getTaskByLevel(1)
        activity?.runOnUiThread {
            questionTxt.text = "${task?.taskDesc} ${task?.question}"
            answer = task?.rightAnswer
            questionImg.setImageResource(R.drawable.first_level_task)
        }
    }

    private fun setSecondFragment() = GlobalScope.launch {
        val task = AccountantDB.getInstance(requireContext()).taskDao().getTaskByLevel(2)
        activity?.runOnUiThread {
            questionTxt.text = "${task?.taskDesc} ${task?.question}"
            answer = task?.rightAnswer
            questionImg.setImageResource(R.drawable.second_level_task)
        }
    }

    private fun handleRightAnswer() {
        MediaPlayer.create(context, R.raw.win_sound).start()
        val nextLevel =
            Level.values()[currentLevel!!.value] // current value because indices starts from 0
        when (currentLevel) {
            Level.FIRST -> {
                (activity as MainActivity).navigateToLevel(nextLevel)
            }
            Level.SECOND -> {
                handleSecondLevelRightAnswer(nextLevel)
            }
            else -> Toast.makeText(
                context,
                getString(R.string.unexpected_error),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun handleSecondLevelRightAnswer(nextLevel: Level) {
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

    private fun handleWrongAnswer() {
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