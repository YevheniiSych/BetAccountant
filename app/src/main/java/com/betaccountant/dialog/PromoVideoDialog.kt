package com.betaccountant.dialog

import android.app.Dialog
import android.content.Context
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import com.betaccountant.R
import kotlinx.android.synthetic.main.video_dialog_layout.*
import kotlinx.android.synthetic.main.video_dialog_layout.view.*


class PromoVideoDialog(context: Context, private val promoVideoUrl: String?) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dialogView = layoutInflater.inflate(R.layout.video_dialog_layout, null)
        val uri = Uri.parse(promoVideoUrl)
        dialogView.videoView.setVideoURI(uri)
        setContentView(dialogView)
        setCancelable(false)
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window?.setGravity(Gravity.CENTER)
        videoView.setOnPreparedListener{ mp ->
            mp.setOnSeekCompleteListener {
                videoView.start()
            }
        }
    }

    fun start(completeListener: () -> Unit) {
        videoView.start()
        videoView.setOnCompletionListener {
            completeListener.invoke()
            this@PromoVideoDialog.dismiss()
        }
    }
}