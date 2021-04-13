package com.betaccountant.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.betaccountant.MainActivity
import com.betaccountant.R
import com.betaccountant.dialog.GuessTermDialog
import com.betaccountant.dialog.StoryDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_eighth_level.*
import kotlin.random.Random


class EighthLevel : Fragment() {

    private var imageList: ArrayList<LinearLayout>? = null
    private var captionsList = listOf(
        "начальник відділу:\nПекло О.О.",
        "головний бухгалтер:\nЗагребло Д.А.",
        "секретар:\nПомагайло Н.І.",
        "фахівець 1-ї категорії:\nХапайло О.Й.",
        "консультант:\nВдячний О.Т.",
        "головний економіст:\nЩедрий А.Д."
    )
    private var doorCount = 6

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_eighth_level, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val termList = resources.getStringArray(R.array.terms).toMutableList()
        imageList = getImageList()
        imageList?.forEach { imageView ->
            imageGrid.addView(imageView)
            imageView.setOnClickListener {
                it.visibility = View.INVISIBLE
                if (isRightDoor()) {
                    (activity as MainActivity).toolbar?.pauseTimeCounter()
                    StoryDialog(requireContext(), getString(R.string.mission_complete),{
                        (activity as MainActivity).showPromoVideoAndNavigateToLevel()
                        (activity as MainActivity).navController?.navigate(R.id.resultFragment)
                    }).show()
                    return@setOnClickListener
                } else {
                    imageList?.remove(it)
                    val randomTerm = termList.random()
                    termList.remove(randomTerm)
                    GuessTermDialog(requireContext(), randomTerm) {}.show()
                }
                doorCount--
            }
        }
    }

    private fun isRightDoor() = Random.nextInt(1, doorCount) == 1

    private fun getImageList(): ArrayList<LinearLayout> {
        val imageList = ArrayList<LinearLayout>()
        for (i in captionsList.indices) {
            imageList.add(
                createImageWithBottomCaption(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.door
                    ),
                    captionsList[i]
                )
            )
        }
        return imageList
    }

    private fun createImageWithBottomCaption(image: Drawable?, text: String): LinearLayout {
        return LinearLayout(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
                orientation = LinearLayout.VERTICAL
                addView(createImage(image))
                addView(createCaption(text))
            }
        }
    }

    private fun createImage(drawable: Drawable?): ImageView {
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels
        val imageWidth = screenWidth * 0.25
        val imageHeight = imageWidth * 2.5
        val horizontalMargin = imageWidth * 0.1
        return ImageView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                imageWidth.toInt(),
                imageHeight.toInt()
            ).apply {
                setMargins(horizontalMargin.toInt(), 0, horizontalMargin.toInt(), 0)
            }
            setImageDrawable(drawable)
        }
    }

    private fun createCaption(text: String): TextView {
        return TextView(context).apply {
            val displayMetrics = DisplayMetrics()
            activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
            val screenWidth = displayMetrics.widthPixels
            layoutParams = LinearLayout.LayoutParams(
                (screenWidth * 0.25).toInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
                textSize = 16f
                setText(text)
            }
        }
    }
}