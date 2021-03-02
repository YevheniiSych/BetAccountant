package com.betaccountant.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betaccountant.MainActivity
import com.betaccountant.R
import com.betaccountant.dialog.BoolQuestionDialog
import com.betaccountant.dialog.OneWrongStatementTaskDialog
import com.betaccountant.dialog.StoryDialog
import com.betaccountant.enums.Level
import com.betaccountant.enums.Locations
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fifth_level.*
import kotlinx.android.synthetic.main.fragment_third_level.*
import kotlinx.android.synthetic.main.fragment_third_level.bankContainer

class FifthLevel : Fragment() {

    private lateinit var factsWithAnswers: MutableMap<List<String>, String>
    private var balanceLocation: Locations? = null
    private var isBalanceLocation: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_fifth_level, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        balanceLocation = Locations.getRandomLocation(Level.FIFTH)
        val factLists = ArrayList<List<String>>()
//        factLists[0] = ArrayList(3)
        factLists.add(resources.getStringArray(R.array.fifth_level_first_facts_list).toList())
        factLists.add(resources.getStringArray(R.array.fifth_level_second_facts_list).toList())
        factLists.add(resources.getStringArray(R.array.fifth_level_third_facts_list).toList())
        factLists.add(resources.getStringArray(R.array.fifth_level_fourth_facts_list).toList())
        val answers = resources.getStringArray(R.array.fifth_level_answers).toList()
        factsWithAnswers = factLists.zip(answers).toMap().toMutableMap()
        libraryContainer.setOnClickListener {
            isBalanceLocation = balanceLocation == Locations.LIBRARY
            handleItemClick(it)
        }
        bankBalanceContainer.setOnClickListener {
            isBalanceLocation = balanceLocation == Locations.BANK_BALANCE
            handleItemClick(it)
        }
        taxContainer.setOnClickListener {
            isBalanceLocation = balanceLocation == Locations.TAX_OFFICE
            handleItemClick(it)
        }
        shopContainer.setOnClickListener {
            isBalanceLocation = balanceLocation == Locations.SHOP
            handleItemClick(it)
        }
    }

    private fun handleItemClick(view: View?) {
        val factList = factsWithAnswers.keys.random()
        val answer = factsWithAnswers[factList]
        factsWithAnswers.remove(factList)
        OneWrongStatementTaskDialog.getInstance(
            requireContext(),
            factList,
            answer!!
        ) {
            handleAnswer(it)
            view?.visibility = View.INVISIBLE
        }.show()
    }

    private fun handleAnswer(isRightAnswer: Boolean) {
        if (isRightAnswer) {
//            Toast.makeText(context, "right", Toast.LENGTH_LONG).show()
        } else {
//            activity?.toolbar?.setRemainingLivesAmount(activity?.toolbar?.getRemainingLivesAmount()?.minus(1))
            activity?.toolbar?.apply {
                setRemainingLivesAmount(getRemainingLivesAmount()?.dec())
            }
//            TODO: minus life method
        }
        showDirectorDialog()
    }

    private fun showDirectorDialog() {
        StoryDialog.getInstance(
            requireContext(),
            getString(if (isBalanceLocation) R.string.director_found else R.string.director_not_found)
        ) {
            if (isBalanceLocation) {
                (activity as MainActivity).navigateToLevel(Level.SIXTH)
            }
        }.show()
    }
}