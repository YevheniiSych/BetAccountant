package com.betaccountant.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Chronometer
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

    init {
        val view = View.inflate(context, R.layout.layout_toolbar_view, this)
        label = view.toolbarLabel
        timeCounter = view.toolbarTimeCounter
        readParams(context)
    }

    fun startTimeCounter(){
        timeCounter?.start()
    }

    fun pauseTimeCounter(){
        timeCounter?.stop()
    }

    fun resetTimeCounter(){

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

    private fun readParams(context: Context) {
        val attributes = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ToolbarView,
            0, 0
        )
        try {
            setLabel(attributes.getString(R.styleable.ToolbarView_label))
            timeCounterEnabled(attributes.getBoolean(R.styleable.ToolbarView_time_counter_enabled, false))
        } finally {
            attributes.recycle()
        }
    }

}