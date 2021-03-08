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


class PromoVideoDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dialogView = layoutInflater.inflate(R.layout.video_dialog_layout, null)
        val promoVideoUrl = "android.resource://" + context.packageName + "/" + R.raw.promo
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

    fun start(startTime: Int = 0) {
        videoView.seekTo(startTime)
        videoView.start()
    }

    fun getCurrentVideoPosition() = videoView.currentPosition

    fun pause() {
        videoView.pause()
    }
}