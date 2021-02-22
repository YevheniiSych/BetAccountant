package com.betaccountant.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.util.AttributeSet
import android.view.View
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.betaccountant.R
import kotlinx.android.synthetic.main.layout_toolbar_view.view.*

class ToolbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var attrs: AttributeSet? = attrs
    private var label: TextView? = null
    private var timeCounter: Chronometer? = null
    private var livesCounter: LinearLayout? = null
    private var maxLivesAmount: Int? = 0
    private var remainingLivesAmount: Int? = 0
    private var remainingLifeImg: Drawable? = null
    private var usedLifeImg: Drawable? = null

    init {
        val view = View.inflate(context, R.layout.layout_toolbar_view, this)
        label = view.toolbarLabel
        timeCounter = view.toolbarTimeCounter
        livesCounter = view.toolbarLivesCounter
        readParams(context)
    }

    fun startTimeCounter() {
        timeCounter?.start()
    }

    fun pauseTimeCounter(){
        timeCounter?.stop()
    }

    fun resetTimeCounter(){
        timeCounter?.base = SystemClock.elapsedRealtime()
    }

    fun timeCounterEnabled(enabled: Boolean) {
        timeCounter?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    fun setLabel(labelTxt: String?) {
        label?.text = labelTxt
    }

    fun setLabel(stringId: Int) {
        label?.text = resources.getString(stringId)
    }

    fun getLabel(): String {
        return label?.text.toString()
    }

    fun livesCounterEnabled(enabled: Boolean) {
        livesCounter?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    fun getMaxLivesAmount(): Int? {
        return maxLivesAmount
    }

    fun getRemainingLivesAmount(): Int? {
        return remainingLivesAmount
    }

    fun setRemainingLivesAmount(remainingLivesAmount: Int) {
        toolbarLivesCounter.removeAllViewsInLayout()
        for (i in 1..maxLivesAmount!!) {
            if (i <= remainingLivesAmount) {
                toolbarLivesCounter.addView(createLifeView(remainingLifeImg))
            } else {
                toolbarLivesCounter.addView(createLifeView(usedLifeImg))
            }
        }
    }

    private fun readParams(context: Context) {
        val attributes = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ToolbarView,
            0, 0
        )
        try {
            setLabel(attributes.getString(R.styleable.ToolbarView_label))
            timeCounterEnabled(
                attributes.getBoolean(
                    R.styleable.ToolbarView_time_counter_enabled,
                    false
                )
            )
            livesCounterEnabled(
                attributes.getBoolean(
                    R.styleable.ToolbarView_lives_counter_enabled,
                    false
                )
            )
            maxLivesAmount = attributes.getInteger(R.styleable.ToolbarView_max_lives_amount, 0)
            remainingLivesAmount =
                attributes.getInteger(R.styleable.ToolbarView_remaining_lives_amount, 0)
            remainingLifeImg = attributes.getDrawable(R.styleable.ToolbarView_remaining_life_img)
            usedLifeImg = attributes.getDrawable(R.styleable.ToolbarView_used_life_img)
            setRemainingLivesAmount(remainingLivesAmount!!)
        } finally {
            attributes.recycle()
        }
    }

    private fun createLifeView(lifeImage: Drawable?): ImageView {
        val lifeView = ImageView(context)
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1F)
        params.marginStart = 10
        lifeView.layoutParams = params
        lifeView.setImageDrawable(lifeImage)
        return lifeView
    }

}