package com.betaccountant.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.betaccountant.R
import kotlinx.android.synthetic.main.story_dialog_layout.view.*

class StoryDialog(
    context: Context,
    private val message: String,
    private val continueClick: () -> Unit
) : Dialog(context) {

    companion object {
        private var instance: StoryDialog? = null
        fun getInstance(
            context: Context,
            message: String,
            continueClick: () -> Unit
        ): StoryDialog {
            return if (instance == null) StoryDialog(
                context,
                message,
                continueClick
            ) else instance!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dialogView = layoutInflater.inflate(R.layout.story_dialog_layout, null)
        dialogView.apply {
            storyDialogTxt.text = message
            continueStoryBtn.setOnClickListener {
                continueClick.invoke()
                this@StoryDialog.dismiss()
            }
        }
        setContentView(dialogView)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}