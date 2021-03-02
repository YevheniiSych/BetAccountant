package com.betaccountant.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betaccountant.MainActivity
import com.betaccountant.R
import com.betaccountant.dialog.OneWrongStatementTaskDialog
import com.betaccountant.dialog.StoryDialog
import com.betaccountant.enums.Level
import com.betaccountant.enums.Locations
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fifth_level.*

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
        balanceLocation = Locations.TAX_OFFICE
        val factLists = ArrayList<List<String>>()
        factLists.add(resources.getStringArray(R.array.fifth_level_first_facts_list).toList())
        factLists.add(resources.getStringArray(R.array.fifth_level_second_facts_list).toList())
        factLists.add(resources.getStringArray(R.array.fifth_level_third_facts_list).toList())
        factLists.add(resources.getStringArray(R.array.fifth_level_fourth_facts_list).toList())
        val answers = resources.getStringArray(R.array.fifth_level_answers).toList()
        factsWithAnswers = factLists.zip(answers).toMap().toMutableMap()
        libraryContainer.setOnClickListener(this::handleItemClick)
        bankBalanceContainer.setOnClickListener(this::handleItemClick)
        taxContainer.setOnClickListener {
            isBalanceLocation = balanceLocation == Locations.TAX_OFFICE
            handleItemClick(it)
        }
        shopContainer.setOnClickListener(this::handleItemClick)
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
            activity?.toolbar?.subtractOneLife()
        }
        showTaxOfficeDialog()
    }

    private fun showTaxOfficeDialog() {
        StoryDialog.getInstance(
            requireContext(),
            getString(if (isBalanceLocation) R.string.help_betaccountant_reach_tax_office else R.string.balance_not_submitted)
        ) {
            if (isBalanceLocation) {
                (activity as MainActivity).navigateToLevel(Level.SIXTH)
            }
        }.show()
    }
}