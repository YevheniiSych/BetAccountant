package com.betaccountant.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.betaccountant.MainActivity
import com.betaccountant.R
import com.betaccountant.dialog.StoryDialog
import com.betaccountant.enums.Level
import kotlinx.android.synthetic.main.fragment_fourth_level.*

class FourthLevel : Fragment() {
    companion object{
        private const val ANSWER_CODE = "35240"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_fourth_level, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        finishGameButton.setOnClickListener {
            if (inputCode.text.toString() == ANSWER_CODE) {
                (activity as MainActivity).showPromoVideoAndNavigateToLevel(Level.FIFTH)
            } else {
                showWrongAnswerDialog()
            }
        }
    }

    private fun showWrongAnswerDialog() {
        StoryDialog(
            requireContext(),
            getString(R.string.third_level_wrong_answer), {}
        ).show()
    }
}