package com.betaccountant.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.betaccountant.MainActivity
import com.betaccountant.R
import com.betaccountant.dialog.OneWrongStatementTaskDialog
import com.betaccountant.dialog.StoryDialog
import com.betaccountant.enums.Level
import com.betaccountant.enums.Locations
import com.betaccountant.model.Fact
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fifth_level.*

class FifthLevel : Fragment() {

    private lateinit var allFactsList: ArrayList<Fact>
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
        allFactsList = fillFactList()
        libraryContainer.setOnClickListener(this::handleItemClick)
        bankBalanceContainer.setOnClickListener(this::handleItemClick)
        taxContainer.setOnClickListener {
            isBalanceLocation = balanceLocation == Locations.TAX_OFFICE
            handleItemClick(it)
        }
        shopContainer.setOnClickListener(this::handleItemClick)
    }

    private fun handleItemClick(view: View?) {
        val randomGroupId = allFactsList.random().groupId
        val oneTaskFactsList = allFactsList.filter { it.groupId == randomGroupId }
        allFactsList = allFactsList.filterNot { it.groupId == randomGroupId } as ArrayList<Fact>
        OneWrongStatementTaskDialog(
            requireContext(),
            oneTaskFactsList,
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

    private fun fillFactList(): ArrayList<Fact> {
        val factList = ArrayList<Fact>()

        // Fact List 1

        factList.add(
            Fact(
                "Вперше податок на доходи запровадила Велика Британія 1799 року.",
                true,
                1
            )
        )
        factList.add(
            Fact(
                "У багатьох країнах зараз справляється податок на собак.",
                true,
                1
            )
        )
        factList.add(
            Fact(
                "В Україні підприємства можуть не сплачувати податки у перші 5 років своєї діяльності.",
                false,
                1
            )
        )

        // Fact List 2

        factList.add(
            Fact(
                "У 1689 р. Петром І у Росії був запроваджений податок на бороду.",
                true,
                2
            )
        )
        factList.add(
            Fact(
                "В Україні з 2000 р. діє податок на годинники.",
                false,
                2
            )
        )
        factList.add(
            Fact(
                "Українець може зменшити суму сплачених податків, якщо оплачує своє навчання в закладах вищої освіти.",
                true,
                2
            )
        )

        // Fact List 3

        factList.add(
            Fact(
                "На Балеарських островах в Іспанії діє податок на сонце.",
                true,
                3
            )
        )
        factList.add(
            Fact(
                "Середньостатистичний українець сплачує в якості податків більше 40% заробітної плати.",
                false,
                3
            )
        )
        factList.add(
            Fact(
                "За часів Київської Русі податки можна було сплачувати як у грошовому, так і в натуральному вигляді (хутром, зерном тощо).",
                true,
                3
            )
        )

        // Fact List 4

        factList.add(
            Fact(
                "У 1783 р. у Великій Британії було запроваджено податок на капелюхи.",
                true,
                4
            )
        )
        factList.add(
            Fact(
                "При заповненні податкової декларації в Україні людина обов’язково має вказати свій індивідуальний податковий номер.",
                true,
                4
            )
        )
        factList.add(
            Fact(
                "У Німеччині кожен громадянин може безкоштовно звернутись до податкового консультанта.",
                false,
                4
            )
        )

        return factList
    }
}