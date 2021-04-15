package com.betaccountant.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.betaccountant.MainActivity
import com.betaccountant.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_result.*


class ResultFragment : Fragment() {

    companion object {
        private const val INSTAGRAM_LINK =
            "https://www.instagram.com/sumdu_kafedra_boo/"
        private const val FACEBOOK_LINK = "https://www.facebook.com/groups/buchuchet.uabs.sumdu"
        private const val SITE_LINK = "http://buchuchet.uabs.sumdu.edu.ua"
        private const val EMAIL_LINK = "mailto:boa@uabs.sumdu.edu.ua"
        private const val LOCATION_LINK =
            "https://www.google.com/maps/place/%D0%B2%D1%83%D0%BB%D0%B8%D1%86%D1%8F+%D0%9F%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%B0%D0%B2%D0%BB%D1%96%D0%B2%D1%81%D1%8C%D0%BA%D0%B0,+57,+%D0%A1%D1%83%D0%BC%D0%B8,+%D0%A1%D1%83%D0%BC%D1%81%D1%8C%D0%BA%D0%B0+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C,+40000/@50.9051338,34.7933402,17z/data=!3m1!4b1!4m5!3m4!1s0x4129018c0bbb6515:0x6530719c9fe77b62!8m2!3d50.9051338!4d34.7955289"
        const val IS_FROM_START = "IS_FROM_START"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_result, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isFromStart = arguments?.getBoolean(IS_FROM_START) ?: false
        if(!isFromStart) {
            (activity as MainActivity).toolbar?.setLabel(getString(R.string.app_name))
            (activity as MainActivity).toolbar?.hideLives()
            (activity as MainActivity).toolbar?.hideTimer()
            val resultTime = (activity as MainActivity).toolbar?.getFormattedTime()
            resultTxt.visibility = View.VISIBLE
            resultTimeTxt.visibility = View.VISIBLE
            resultHint.visibility = View.VISIBLE
            resultTimeTxt.text = resultTime
        } else {
            resultTxt.visibility = View.GONE
            resultTimeTxt.visibility = View.GONE
            resultHint.visibility = View.GONE
            finishGameButton.visibility = View.GONE
        }

        finishGameButton.setOnClickListener {
            activity?.finish()
        }
        instagramLink.setOnClickListener {
            openUrl(INSTAGRAM_LINK)
        }
        facebookLink.setOnClickListener {
            openUrl(FACEBOOK_LINK)
        }
        siteLink.setOnClickListener {
            openUrl(SITE_LINK)
        }
        phoneLink.setOnClickListener {
            callIntent()
        }
        emailLink.setOnClickListener {
            openUrl(EMAIL_LINK)
        }
        locationLink.setOnClickListener {
            openUrl(LOCATION_LINK)
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun callIntent() {
        val phoneNumber = "tel:" + getString(R.string.phone_number)
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse(phoneNumber)
        startActivity(intent)
    }
}
