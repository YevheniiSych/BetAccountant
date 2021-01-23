package com.betaccountant

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {

    var navController: NavController? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.fragment_container)
    }

    override fun onBackPressed() {
        if (navController?.currentDestination?.id == R.id.startFragment) {
            super.onBackPressed()
        } else {
            showExitDialog()
        }
    }

    private fun showExitDialog() {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.apply {
            setMessage(R.string.exit_dialog_message)
            setCancelable(true)
            setPositiveButton(R.string.yes) { _, _ -> finish()}
            setNegativeButton(R.string.no) { _, _ -> }
        }
        val alert = alertBuilder.create()
        alert.apply {
            setTitle(R.string.exit_dialog_title)
            show()
        }
    }
}