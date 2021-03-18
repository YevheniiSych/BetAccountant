package com.betaccountant.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import com.betaccountant.R
import kotlinx.android.synthetic.main.story_dialog_layout.view.*

class StoryDialog(
    context: Context,
    private val message: String,
    private val continueClick: () -> Unit,
    private val image: Drawable? = null
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dialogView = layoutInflater.inflate(R.layout.story_dialog_layout, null)
        dialogView.apply {
            storyDialogTxt.text = message
            if (image != null) {
                storyImage.visibility = View.VISIBLE
                storyImage.setImageDrawable(image)
            } else {
                storyImage.visibility = View.GONE
            }
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