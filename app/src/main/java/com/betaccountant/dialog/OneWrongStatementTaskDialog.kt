package com.betaccountant.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.betaccountant.R
import kotlinx.android.synthetic.main.one_wrong_statement_task_dialog_layout.*
import kotlinx.android.synthetic.main.one_wrong_statement_task_dialog_layout.view.*

class OneWrongStatementTaskDialog(
    context: Context,
    private val facts: List<String>,
    private val answer: Int,
    private val answerListener: (isRightAnswer: Boolean) -> Unit
) : Dialog(context) {

    companion object {
        private var instance: OneWrongStatementTaskDialog? = null
        fun getInstance(
            context: Context,
            facts: List<String>,
            answer: Int,
            answerListener: (isRightAnswer: Boolean) -> Unit
        ): OneWrongStatementTaskDialog {
            return if (instance == null) OneWrongStatementTaskDialog(
                context,
                facts,
                answer,
                answerListener
            ) else instance!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dialogView = layoutInflater.inflate(R.layout.one_wrong_statement_task_dialog_layout, null)
        dialogView.apply {
            oneWrongStatementTaskFirstFact.text = facts[0]
            oneWrongStatementTaskSecondFact.text = facts[1]
            oneWrongStatementTaskThirdFact.text = facts[2]
            answerBtn.setOnClickListener(this@OneWrongStatementTaskDialog::handleClick)
        }
        setContentView(dialogView)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun handleClick(view: View?) {
        val radioButton: RadioButton? = findViewById(oneWrongStatementTaskRadioGroup.checkedRadioButtonId)
        if(radioButton != null) {
            val answerFactNumber = oneWrongStatementTaskRadioGroup.indexOfChild(oneWrongStatementTaskRadioGroup).inc()
            answerListener(answerFactNumber == answer)
            this@OneWrongStatementTaskDialog.dismiss()
        }
    }
}