package com.betaccountant

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.betaccountant.db.AccountantDB
import com.betaccountant.db.getTasksFromStorage
import com.betaccountant.dialog.PromoVideoDialog
import com.betaccountant.enums.Level
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        const val FRAGMENT_LEVEL = "FRAGMENT_LEVEL"
    }

    var navController: NavController? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fillDB()
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

    internal fun navigateToLevel(nextLevel: Level) {
        when (nextLevel) {
            Level.FIRST, Level.SECOND -> {
                val bundle = Bundle()
                bundle.putSerializable(FRAGMENT_LEVEL, nextLevel)
                navController?.navigate(R.id.firstLevelFragment, bundle)
            }
            Level.THIRD -> navController?.navigate(R.id.thirdLevelFragment)
            Level.FOURTH -> navController?.navigate(R.id.fourthLevelFragment)
            Level.FIFTH -> navController?.navigate(R.id.fivesLevelFragment)
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
            setPositiveButton(R.string.yes) { _, _ -> finish() }
            setNegativeButton(R.string.no) { _, _ -> }
        }
        val alert = alertBuilder.create()
        alert.apply {
            setTitle(R.string.exit_dialog_title)
            show()
        }
    }

    internal fun showPromoVideoAndNavigateToLevel(nextLevel: Level? = null) {
        val promoVideoUrl = "android.resource://" + this.packageName + "/" +
                when (nextLevel) {
                    Level.THIRD -> R.raw.promo_part_1
                    Level.FIFTH -> R.raw.promo_part_2
                    Level.SEVENTH -> R.raw.promo_part_3
                    else -> R.raw.promo_part_4
                }
        val promoDialog = PromoVideoDialog(this, promoVideoUrl)
        promoDialog.show()
        toolbar?.pauseTimeCounter()
        promoDialog.start {
            toolbar?.resumeTimeCounter()
            if (nextLevel != null) {
                navigateToLevel(nextLevel)
            }
        }
    }

    private fun fillDB() {
        GlobalScope.launch {
            val db = AccountantDB.getInstance(this@MainActivity)
            db.taskDao().insertAll(getTasksFromStorage())
        }
    }

}