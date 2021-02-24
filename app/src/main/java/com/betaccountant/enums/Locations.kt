package com.betaccountant.enums

import kotlin.random.Random

enum class Locations(val value: Int) {
    BATH(1),
    SEA(2),
    BANK(3),
    BUSINESS_MEETING(4),
    LIBRARY(5),
    BANK_BALANCE(6),
    TAX_OFFICE(7),
    SHOP(8);

    companion object {
        fun getRandomLocation(level: Level): Locations? {
            return when (level) {
                Level.THIRD -> {
                    val value = Random.nextInt(BATH.value, BUSINESS_MEETING.value)
                    getByValue(value)
                }
                Level.FIFTH -> {
                    val value = Random.nextInt(LIBRARY.value, SHOP.value)
                    getByValue(value)
                }
                else -> {
                    val value = Random.nextInt(values().first().value, values().size)
                    getByValue(value)
                }
            }
        }

        private fun getByValue(value: Int): Locations? = values().firstOrNull { it.value == value }
    }
}