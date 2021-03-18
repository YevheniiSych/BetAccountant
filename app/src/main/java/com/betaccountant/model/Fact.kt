package com.betaccountant.model

data class Fact(
    val text: String,
    val isTrue: Boolean,
    val groupId: Int = 0
)