package com.betaccountant.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.betaccountant.R
import kotlinx.android.synthetic.main.game_over_dialog.view.*
import kotlinx.android.synthetic.main.guess_term_dialog_layout.view.*
import java.util.*
import kotlin.random.Random

class GameOverDialog (context: Context,
                      private val onComplete: () -> Unit
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dialogView =
            layoutInflater.inflate(R.layout.game_over_dialog, null)
        dialogView.apply {
            completeBtn.setOnClickListener {
                onComplete()
            }
        }
        setContentView(dialogView)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}