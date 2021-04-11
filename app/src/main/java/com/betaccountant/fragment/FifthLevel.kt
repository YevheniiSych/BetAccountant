package com.betaccountant.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.betaccountant.MainActivity
import com.betaccountant.R
import com.betaccountant.db.AccountantDB
import com.betaccountant.dialog.GameOverDialog
import com.betaccountant.dialog.OneWrongStatementTaskDialog
import com.betaccountant.dialog.StoryDialog
import com.betaccountant.enums.Level
import com.betaccountant.enums.Locations
import com.betaccountant.db.model.Fact
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fifth_level.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FifthLevel : Fragment() {

    private var allFactsList: ArrayList<Fact>? = null
    private var balanceLocation: Locations? = null
    private var isBalanceLocation: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            allFactsList = AccountantDB.getInstance(requireContext()).factDao().getFactsByLevel(5) as ArrayList<Fact>?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_fifth_level, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        StoryDialog(
            requireContext(),
            getString(R.string.fifth_level_start_story), {},
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.man_with_calculator
            ),
            true
        ).show()
        init()
    }

    private fun init() {
        balanceLocation = Locations.TAX_OFFICE
        libraryContainer.setOnClickListener(this::handleItemClick)
        bankBalanceContainer.setOnClickListener(this::handleItemClick)
        taxContainer.setOnClickListener {
            isBalanceLocation = balanceLocation == Locations.TAX_OFFICE
            handleItemClick(it)
        }
        shopContainer.setOnClickListener(this::handleItemClick)
    }

    private fun handleItemClick(view: View?) {
        if(allFactsList != null) {
            val randomGroupId = allFactsList!!.random().groupId
            val oneTaskFactsList = allFactsList!!.filter { it.groupId == randomGroupId }
            allFactsList = allFactsList!!.filterNot { it.groupId == randomGroupId } as ArrayList<Fact>
            OneWrongStatementTaskDialog(
                requireContext(),
                oneTaskFactsList,
            ) {
                handleAnswer(it)
                view?.visibility = View.INVISIBLE
            }.show()
        }
    }

    private fun handleAnswer(isRightAnswer: Boolean) {
        if (isRightAnswer) {
//            Toast.makeText(context, "right", Toast.LENGTH_LONG).show()
        } else {
            activity?.toolbar?.subtractOneLife()
            if(activity?.toolbar?.getRemainingLivesAmount() == 0){
                GameOverDialog(requireContext()){
                    activity?.finish()
                }.show()
            }
        }
        showBalanceDialog()
    }

    private fun showBalanceDialog() {
        StoryDialog(
            requireContext(),
            getString(if (isBalanceLocation) R.string.help_betaccountant_reach_tax_office else R.string.balance_not_submitted),
            {
                if (isBalanceLocation) {
                    (activity as MainActivity).navigateToLevel(Level.SIXTH)
                }
            }, if (isBalanceLocation) {
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.man_with_calculator
                )
            } else null
        ).show()
    }
}