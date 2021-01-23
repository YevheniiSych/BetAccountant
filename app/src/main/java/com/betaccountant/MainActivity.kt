package com.betaccountant

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.betaccountant.enums.Level
import kotlinx.android.synthetic.main.activity_main.*

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

    fun navigateToLevel(nextLevel: Level){
        when(nextLevel){
            Level.FIRST -> navController?.navigate(R.id.firstLevelFragment)
            Level.SECOND -> navController?.navigate(R.id.secondLevelFragment)
            Level.THIRD -> navController?.navigate(R.id.thirdLevelFragment)
            Level.FOURTH -> navController?.navigate(R.id.fourthLevelFragment)
            Level.FIVES -> navController?.navigate(R.id.fivesLevelFragment)
            Level.SIXTH -> navController?.navigate(R.id.sixthLevelFragment)
            Level.SEVENTH -> navController?.navigate(R.id.seventhLevelFragment)
            Level.EIGHTH -> navController?.navigate(R.id.eighthLevelFragment)
        }
        toolbar?.setLabel(getString(R.string.completed_levels, nextLevel.value))
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