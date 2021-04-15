package com.betaccountant.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.betaccountant.MainActivity
import com.betaccountant.R
import com.betaccountant.db.AccountantDB
import com.betaccountant.db.model.Fact
import com.betaccountant.dialog.StoryDialog
import com.betaccountant.enums.Level
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.android.synthetic.main.fragment_sixth_level.*


class SixthLevel : Fragment() {

    private var factsList: List<Fact>? = null
    private var checkBoxFactsList: ArrayList<CheckBox>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_sixth_level, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sixLVLAnswerBtn.setOnClickListener {
            handleAnswerClick()
        }
        GlobalScope.launch {
            factsList = AccountantDB.getInstance(requireContext()).factDao().getFactsByLevel(6) as ArrayList<Fact>?
            activity?.runOnUiThread {
                checkBoxFactsList = ArrayList()
                factsList?.forEach { checkBoxFactsList?.add(createCheckBox(it)) }
                checkBoxFactsList?.forEach { factsLayout.addView(it) }
            }
        }
    }

    private fun handleAnswerClick() {
        if (getCheckedItemsCount() != 3) {
            Toast.makeText(context, getString(R.string.choose_only_three_facts), Toast.LENGTH_LONG)
                .show()
        } else {
            StoryDialog(
                requireContext(), getString(R.string.go_to_tax), {
                    MediaPlayer.create(context, R.raw.win_sound).start()
                    (activity as MainActivity).showPromoVideoAndNavigateToLevel(Level.SEVENTH)
                },
                ContextCompat.getDrawable(
                    requireContext(),
                    when (getRightAnswersCount()) {
                        2 -> R.drawable.bike
                        3 -> R.drawable.car
                        else -> R.drawable.tralleybus
                    }
                )
            ).show()

        }
    }

    private fun getRightAnswersCount(): Int {
        var rightAnswersCount = 0
        checkBoxFactsList?.forEach { checkBox ->
            if (checkBox.isChecked) {
                factsList?.forEach { fact ->
                    if (fact.text == checkBox.text && fact.isTrue) {
                        rightAnswersCount++
                    }
                }
            }
        }
        return rightAnswersCount
    }

    private fun getCheckedItemsCount() = checkBoxFactsList?.filter { it.isChecked }?.size ?: 0

    private fun createCheckBox(fact: Fact) = CheckBox(context)
        .apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = 20
                setPadding(5, 5, 5, 5)
            }
            text = fact.text
            background =
                ContextCompat.getDrawable(
                    context,
                    R.drawable.transparent_background_with_border
                )
            buttonTintList = ContextCompat.getColorStateList(context, R.color.border_grey)
        }
}