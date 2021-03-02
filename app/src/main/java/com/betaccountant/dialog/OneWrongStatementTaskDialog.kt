package com.betaccountant.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.core.content.ContextCompat
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
            for (fact in facts) {
                oneWrongStatementTaskRadioGroup.addView(createRadioButtonView(fact))
            }
            answerBtn.setOnClickListener(this@OneWrongStatementTaskDialog::handleClick)
        }
        setContentView(dialogView)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun handleClick(view: View?) {
        val radioButton: RadioButton? =
            findViewById(oneWrongStatementTaskRadioGroup.checkedRadioButtonId)
        if (radioButton != null) {
            val answerFactNumber =
                oneWrongStatementTaskRadioGroup.indexOfChild(oneWrongStatementTaskRadioGroup).inc()
            answerListener(answerFactNumber == answer)
            this@OneWrongStatementTaskDialog.dismiss()
        }
    }

    private fun createRadioButtonView(factText: String): RadioButton {
        val radioButton = RadioButton(context)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.topMargin = 20
        radioButton.layoutParams = params
        radioButton.text = factText
        radioButton.background =
            ContextCompat.getDrawable(context, R.drawable.transparent_background_with_border)
        radioButton.buttonTintList = ContextCompat.getColorStateList(context, R.color.border_grey)
        radioButton.minHeight = 150
        return radioButton
    }

}