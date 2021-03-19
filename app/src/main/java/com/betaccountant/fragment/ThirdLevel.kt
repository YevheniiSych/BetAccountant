package com.betaccountant.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.betaccountant.MainActivity
import com.betaccountant.R
import com.betaccountant.dialog.BoolQuestionDialog
import com.betaccountant.dialog.GameOverDialog
import com.betaccountant.dialog.StoryDialog
import com.betaccountant.enums.Level
import com.betaccountant.enums.Locations
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_third_level.*

class ThirdLevel : Fragment() {

    private lateinit var questionsWithAnswers: MutableMap<String, String>
    private var directorLocation: Locations? = null
    private var isDirectorLocation: Boolean = false

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
        directorLocation = Locations.getRandomLocation(Level.THIRD)
        val questions = resources.getStringArray(R.array.third_level_questions)
        val answers = resources.getStringArray(R.array.third_level_answers)
        questionsWithAnswers = questions.zip(answers).toMap().toMutableMap()
        bathContainer.setOnClickListener {
            isDirectorLocation = directorLocation == Locations.BATH
            handleItemClick(it)
        }
        seaContainer.setOnClickListener {
            isDirectorLocation = directorLocation == Locations.SEA
            handleItemClick(it)
        }
        bankContainer.setOnClickListener {
            isDirectorLocation = directorLocation == Locations.BANK
            handleItemClick(it)
        }
        businessMeetingContainer.setOnClickListener {
            isDirectorLocation = directorLocation == Locations.BUSINESS_MEETING
            handleItemClick(it)
        }
    }

    private fun handleItemClick(view: View?) {
        val question = questionsWithAnswers.keys.random()
        val answer = getBoolAnswerByQuestion(question)
        questionsWithAnswers.remove(question)
        BoolQuestionDialog(
            requireContext(),
            question,
            answer
        ) {
            handleAnswer(it)
            view?.visibility = View.INVISIBLE
        }.show()
    }

    private fun handleAnswer(isRightAnswer: Boolean) {
        if (isRightAnswer) {
//            Toast.makeText(context, "right", Toast.LENGTH_LONG).show()
        } else {
            activity?.toolbar?.subtractOneLife()
            if(activity?.toolbar?.getRemainingLivesAmount() == 0){
                GameOverDialog(requireContext()){
                    activity?.finish()
                }.show()
            }
        }
        showDirectorDialog()
    }

    private fun showDirectorDialog() {
        StoryDialog(
            requireContext(),
            getString(if (isDirectorLocation) R.string.director_found else R.string.director_not_found),
            {
                if (isDirectorLocation) {
                    (activity as MainActivity).navigateToLevel(Level.FOURTH)
                }
            }
        ).show()
    }

    private fun getBoolAnswerByQuestion(question: String): Boolean =
        questionsWithAnswers[question] == getString(R.string.yes)
}