package com.betaccountant.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.betaccountant.MainActivity
import com.betaccountant.R
import com.betaccountant.dialog.StoryDialog
import com.betaccountant.enums.Level
import kotlinx.android.synthetic.main.fragment_seventh_level.*
import java.util.*

class SeventhLevel : Fragment() {

    companion object {
        private const val LEVEL_ANSWER = "паспорт"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_seventh_level, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        seventhLvlAnswerBtn.setOnClickListener {
            if (isRightAnswer()) {
                StoryDialog(
                    requireContext(),
                    "Вітаємо!\n Баланс потрапив до податкової!",
                    {
                        MediaPlayer.create(context, R.raw.win_sound).start()
                        (activity as MainActivity).navigateToLevel(Level.EIGHTH)
                    },
                    ContextCompat.getDrawable(requireContext(), R.drawable.docs_and_coins),
                    true
                ).show()
            }
        }
    }

    private fun isRightAnswer() =
        seventhLvlAnswerInput.text.toString().toLowerCase(Locale.ROOT) == LEVEL_ANSWER
}