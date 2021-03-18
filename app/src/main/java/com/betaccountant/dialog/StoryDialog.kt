package com.betaccountant.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import com.betaccountant.R
import kotlinx.android.synthetic.main.story_dialog_layout.view.*
import kotlinx.android.synthetic.main.story_dialog_layout.view.continueStoryBtn
import kotlinx.android.synthetic.main.story_dialog_layout.view.storyDialogTxt
import kotlinx.android.synthetic.main.story_dialog_layout_with_background_image.view.*
import kotlinx.android.synthetic.main.story_dialog_layout_with_center_image.view.*

class StoryDialog(
    context: Context,
    private val message: String,
    private val continueClick: () -> Unit,
    private val image: Drawable? = null,
    private val isBackgroundImage: Boolean = false
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dialogView = layoutInflater.inflate(
            when {
                isBackgroundImage -> {
                    R.layout.story_dialog_layout_with_background_image
                }
                image != null -> {
                    R.layout.story_dialog_layout_with_center_image
                }
                else -> {
                    R.layout.story_dialog_layout
                }
            }, null
        )
        dialogView.apply {
            storyDialogTxt.text = message
            if (isBackgroundImage) {
                storyBackgroundImage.setImageDrawable(image)
            } else if (image != null) {
                storyCenterImage.setImageDrawable(image)
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