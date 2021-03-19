package com.betaccountant.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import com.betaccountant.R
import kotlinx.android.synthetic.main.bool_question_dialog_layout.view.*
import kotlinx.android.synthetic.main.story_dialog_layout.view.*

class BoolQuestionDialog(
    context: Context,
    private val question: String?,
    private val answer: Boolean,
    private val answerListener: (isRightAnswer: Boolean) -> Unit
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dialogView = layoutInflater.inflate(R.layout.bool_question_dialog_layout, null)
        dialogView.apply {
            questionDialogQuestionTxt.text = question
            questionDialogYesBtn.setOnClickListener(this@BoolQuestionDialog::handleClick)
            questionDialogNoBtn.setOnClickListener(this@BoolQuestionDialog::handleClick)
        }
        setContentView(dialogView)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun handleClick(view: View?) {
        when (view?.id) {
            R.id.questionDialogYesBtn -> {
                answerListener(answer)
            }
            R.id.questionDialogNoBtn -> {
                answerListener(!answer)
            }
        }
        this@BoolQuestionDialog.dismiss()
    }
}