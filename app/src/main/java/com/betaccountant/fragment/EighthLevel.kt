package com.betaccountant.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
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

    private var imageList: ArrayList<ImageView>? = null
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

    private fun getImageList(count: Int = 6): ArrayList<ImageView> {
        val imageList = ArrayList<ImageView>()
        for (i in 1..count) {
            imageList.add(
                createImageView(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.door
                    )
                )
            )
        }
        return imageList
    }

    private fun createImageView(drawable: Drawable?): ImageView {
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
}