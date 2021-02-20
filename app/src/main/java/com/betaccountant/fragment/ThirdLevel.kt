package com.betaccountant.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.betaccountant.R
import com.betaccountant.dialog.BoolQuestionDialog
import kotlinx.android.synthetic.main.fragment_third_level.*

class ThirdLevel : Fragment() {

    lateinit var questionsWithAnswers: MutableMap<String, String>

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
        val questions = resources.getStringArray(R.array.third_level_questions)
        val answers = resources.getStringArray(R.array.third_level_answers)
        questionsWithAnswers = questions.zip(answers).toMap().toMutableMap()
        bathContainer.setOnClickListener(this::handleItemClick)
        seaContainer.setOnClickListener(this::handleItemClick)
        bankContainer.setOnClickListener(this::handleItemClick)
        businessMeetingContainer.setOnClickListener(this::handleItemClick)
    }

    private fun handleItemClick(view: View?) {
        val question = questionsWithAnswers.keys.random()
        val answer = getBoolAnswerByQuestion(question)
        questionsWithAnswers.remove(question)
        BoolQuestionDialog.getInstance(
            requireContext(),
            question,
            answer
        ) {
            view?.visibility = View.INVISIBLE
        }.show()
    }

    private fun getBoolAnswerByQuestion(question: String): Boolean = questionsWithAnswers[question] == getString(R.string.yes)

}