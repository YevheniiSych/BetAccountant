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
import com.betaccountant.model.Fact
import kotlinx.android.synthetic.main.one_wrong_statement_task_dialog_layout.*
import kotlinx.android.synthetic.main.one_wrong_statement_task_dialog_layout.view.*

class OneWrongStatementTaskDialog(
    context: Context,
    private val facts: List<Fact>,
    private val answerListener: (isRightAnswer: Boolean) -> Unit
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dialogView = layoutInflater.inflate(R.layout.one_wrong_statement_task_dialog_layout, null)
        dialogView.apply {
            facts.forEach { oneWrongStatementTaskRadioGroup.addView(createRadioButtonView(it)) }
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
            val answerFactNumber = oneWrongStatementTaskRadioGroup.indexOfChild(radioButton)
            answerListener(!facts[answerFactNumber].isTrue)
            this@OneWrongStatementTaskDialog.dismiss()
        }
    }

    private fun createRadioButtonView(fact: Fact): RadioButton {
        val radioButton = RadioButton(context)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.topMargin = 20
        return radioButton.apply {
            layoutParams = params
            text = fact.text
            background =
                ContextCompat.getDrawable(context, R.drawable.transparent_background_with_border)
            buttonTintList = ContextCompat.getColorStateList(context, R.color.border_grey)
            minHeight = 150
        }
    }

}