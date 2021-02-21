package com.betaccountant.enums

import kotlin.random.Random

enum class Locations(val value: Int) {
    BATH(1),
    SEA(2),
    BANK(3),
    BUSINESS_MEETING(4);

    companion object {
        fun getRandomLocation(level: Level): Locations? {
            return when (level) {
                Level.THIRD -> {
                    val value = Random.nextInt(BATH.value, BUSINESS_MEETING.value)
                    getByValue(value)
                }
//                Level.FIVES -> {
//                }
                else -> {
                    val value = Random.nextInt(values().first().value, values().size)
                    getByValue(value)
                }
            }
        }

        fun getByValue(value: Int): Locations? = values().firstOrNull { it.value == value }
    }
}