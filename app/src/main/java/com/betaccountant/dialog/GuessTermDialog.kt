package com.betaccountant.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.betaccountant.R
import kotlinx.android.synthetic.main.guess_term_dialog_layout.view.*
import java.util.*
import kotlin.random.Random

class GuessTermDialog(
    context: Context,
    private val text: String,
    private val term: String,
    private val answerClick: () -> Unit
) : Dialog(context) {

    private var wrongAnswerCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dialogView =
            layoutInflater.inflate(R.layout.guess_term_dialog_layout, null)
        dialogView.apply {
            guessTermDialogText.text = text
            guessTermDialogTxt.text = mixSymbols(term)
            guessTermAnswerBtn.setOnClickListener {
                val answer = answerTermInput.text.toString().toLowerCase(Locale.ROOT)
                val isRightAnswer = answer == term.toLowerCase(Locale.ROOT)
                if(isRightAnswer) {
                    answerClick()
                    this@GuessTermDialog.dismiss()
                } else {
                    wrongAnswerCount++
                    if(wrongAnswerCount <= 1){
                        showWrongAnswerDialog()
                    } else {
                        showRightAnswer()
                    }
                }
            }
        }
        setContentView(dialogView)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun mixSymbols(string: String): String {
        val mixedStr = StringBuilder(string)
        repeat(20) {
            val i1 = Random.nextInt(0, mixedStr.lastIndex)
            val i2 = Random.nextInt(0, mixedStr.lastIndex)
            val s1 = mixedStr[i1]
            val s2 = mixedStr[i2]
            mixedStr.setCharAt(i1, s2)
            mixedStr.setCharAt(i2, s1)
        }
        return mixedStr.toString()
    }

    private fun showWrongAnswerDialog() {
        StoryDialog(
            context,
            context.getString(R.string.wrong_answer), {}
        ).show()
    }

    private fun showRightAnswer() {
        StoryDialog(
            context,
            term, {}
        ).show()
    }
}