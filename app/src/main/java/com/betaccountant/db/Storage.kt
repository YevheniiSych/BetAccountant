package com.betaccountant.db

import com.betaccountant.db.model.Task

fun getTasksFromStorage() = listOf(
    Task(
        levelNumber = 1,
        question = "Скільки Бухгалтер має активів?",
        rightAnswer = "22",
        taskDesc = "Допоможи Бухгалтеру скласти баланс."
    ),
    Task(
        levelNumber = 2,
        question = "Скільки БетБухгалтер має пасивів?",
        rightAnswer = "22",
        taskDesc = "Допоможи Бухгалтеру скласти баланс."
    ),
    Task(
        levelNumber = 3,
        question = "Вгадай, де знаходиться директор?",
        taskDesc = " Щоб перевірити, чи є тут директор, дай відповідь на коротке запитання! Будь уважний, неправильна відповідь забирає життя!"
    ),
    Task(
        levelNumber = 4,
        question = "Підбери код до сейфу.",
        rightAnswer = "35240",
        taskDesc = "Тепер Баланс заповнений і підписаний! Його потрібно залишити в сейфі."
    ),
    Task(
        levelNumber = 5,
        question = "Куди Бухгалтеру здавати Баланс?",
        taskDesc = "Щоб дізнатися куди здавати баланс, дай відповідь на коротке запитання! Будь уважний, неправильна відповідь забирає життя!"
    ),
    Task(
        levelNumber = 6,
        question = "Цікаві факти про гроші: правда чи ні?",
        taskDesc = "Обирай три правильні відповіді:"
    ),
    Task(
        levelNumber = 7,
        question = "Скажи слово-пароль черговому на вході до податкової.",
        rightAnswer = "ЗВІТ",
        taskDesc = "Слово-пароль складається з перших букв термінів які необхідно дібрати до визначень."
    ),
    Task(
        levelNumber = 8,
        question = "В який кабінет потрібно здавати Баланс?",
        rightAnswer = "ЗВІТ"
    )
)