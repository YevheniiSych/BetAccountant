package com.betaccountant.db

import com.betaccountant.db.model.Fact
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

fun getFactsList(): ArrayList<Fact> {
    val factList = ArrayList<Fact>()

    // Fact List 1

    factList.add(
        Fact(
            levelNumber = 5,
            text = "Вперше податок на доходи запровадила Велика Британія 1799 року.",
            isTrue = true,
            groupId = 1
        )
    )
    factList.add(
        Fact(
            levelNumber = 5,
            text = "У багатьох країнах зараз справляється податок на собак.",
            isTrue = true,
            groupId = 1
        )
    )
    factList.add(
        Fact(
            levelNumber = 5,
            text = "В Україні підприємства можуть не сплачувати податки у перші 5 років своєї діяльності.",
            isTrue = false,
            groupId = 1
        )
    )

    // Fact List 2

    factList.add(
        Fact(
            levelNumber = 5,
            text = "У 1689 р. Петром І у Росії був запроваджений податок на бороду.",
            isTrue = true,
            groupId = 2
        )
    )
    factList.add(
        Fact(
            levelNumber = 5,
            text = "В Україні з 2000 р. діє податок на годинники.",
            isTrue = false,
            groupId = 2
        )
    )
    val add = factList.add(
        Fact(
            levelNumber = 5,
            text = "Українець може зменшити суму сплачених податків, якщо оплачує своє навчання в закладах вищої освіти.",
            isTrue = true,
            groupId = 2
        )
    )

    // Fact List 3

    factList.add(
        Fact(
            levelNumber = 5,
            text = "На Балеарських островах в Іспанії діє податок на сонце.",
            isTrue = true,
            groupId = 3
        )
    )
    factList.add(
        Fact(
            levelNumber = 5,
            text = "Середньостатистичний українець сплачує в якості податків більше 40% заробітної плати.",
            isTrue = false,
            groupId = 3
        )
    )
    factList.add(
        Fact(
            levelNumber = 5,
            text = "За часів Київської Русі податки можна було сплачувати як у грошовому, так і в натуральному вигляді (хутром, зерном тощо).",
            isTrue = true,
            groupId = 3
        )
    )

    // Fact List 4

    factList.add(
        Fact(
            levelNumber = 5,
            text = "У 1783 р. у Великій Британії було запроваджено податок на капелюхи.",
            isTrue = true,
            groupId = 4
        )
    )
    factList.add(
        Fact(
            levelNumber = 5,
            text = "При заповненні податкової декларації в Україні людина обов’язково має вказати свій індивідуальний податковий номер.",
            isTrue = true,
            groupId = 4
        )
    )
    factList.add(
        Fact(
            levelNumber = 5,
            text = "У Німеччині кожен громадянин може безкоштовно звернутись до податкового консультанта.",
            isTrue = false,
            groupId = 4
        )
    )

    factList.add(
        Fact(
            levelNumber = 6,
            text = "Сьогоднішній топ-100 багатіїв планети заробили достатньо для того, щоб покінчити з глобальною бідністю 4 рази",
            isTrue = true
        )
    )

    factList.add(
        Fact(
            levelNumber = 6,
            text = "У 1923 році гіперінфляція в Німеччині була настільки великою, що люди спалювали гроші для того, щоб зігрітися. Тому що це було дешевше, ніж купити дрова",
            isTrue = true
        )
    )

    factList.add(
        Fact(
            levelNumber = 6,
            text = "Барак Обама став першим президентом, якого почали зображати на доларових купюрах за життя",
            isTrue = false
        )
    )

    factList.add(
        Fact(
            levelNumber = 6,
            text = "До середини XX століття в деяких частинах Африки в якості грошей продовжували використовувати домашню худобу",
            isTrue = true
        )
    )

    factList.add(
        Fact(
            levelNumber = 6,
            text = "Зміни клімату призвели до того, що деякі племена Африки почали використовувати сніг у якості грошей",
            isTrue = false
        )
    )

    return factList
}