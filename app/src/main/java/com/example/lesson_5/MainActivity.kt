package com.example.lesson_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lesson_5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf<Bike>(Wheel(), FrontSprocket(), BackSprocket(), Brake())

        val stringList = listOf(
            "корова",
            "город",
            "огород",
            "машина",
            "змея",
            "озеро",
            "гора",
            "слово",
            "окно",
            "забор",
            "дом",
            "дверь",
            "школа",
            "ручка",
            "нога",
            "дерево",
            "слон",
            "песок",
            "змей",
            "марс"
        )

        Log.d("ololo", "onCreate: ${resultFromString("-100*100")}")
        Log.d("ololo", "onCreate: ${createName("сергей", stringList)}")

        val listOfChoices = listOf<String>("Rock", "Scissors", "Paper")

        binding.btnChoice.setOnClickListener {
            var myChoice = 3
            val compChoice = getCompChoice(listOfChoices)

            when {
                binding.rbtnRock.isChecked -> myChoice = 0
                binding.rbtnPaper.isChecked -> myChoice = 2
                binding.rbtnScissors.isChecked -> myChoice = 1
            }

            binding.tvResult.text = getResult(myChoice, compChoice, listOfChoices)
        }

    }

    private fun getResult(myChoice: Int, compChoice: Int, choices: List<String>): String {
        var result = ""
        if (myChoice != 3) {
            if (myChoice == 0 && compChoice == 1)
                result = "You won"
            else if (myChoice == 1 && compChoice == 0)
                result = "Computer won"
            else if (myChoice == 1 && compChoice == 2)
                result = "You won"
            else if (myChoice == 2 && compChoice == 1)
                result = "Computer won"
            else if (myChoice == 2 && compChoice == 0)
                result = "You won"
            else if (myChoice == 0 && compChoice == 2)
                result = "Computer won"
            else if (myChoice == compChoice)
                result = "Draw"
        }else
            return "you didn't make a choice"
        return "Your choice: ${choices[myChoice]}. Computer choice: ${choices[compChoice]}. $result"
    }

    private fun createName(name: String, stringList: List<String>): String {
        var stringRow = ""
        stringList.forEach { string ->
            stringRow += string.lowercase()
        }

        var searchedName = ""
        while (searchedName != name.lowercase()) {
            for (char in name) {
                for (char1 in stringRow) {
                    if (char1 == char) {
                        searchedName += char1.toString()
                        break
                    }
                }
            }

        }
        return searchedName
    }

    private fun resultFromString(number: String): Int {
        var result = 0
        var first = ""
        var second = ""
        var operation = 'a'
        var i = 0
        for (char in number) {
            if (i == 0)
                first += char.toString()
            else if (operation == 'a') {
                if (char in '0'..'9')
                    first += char.toString()
                else
                    operation = char
            } else
                second += char.toString()
            i++
        }
        when (operation) {
            '+' -> result = first.toInt() + second.toInt()
            '-' -> result = first.toInt() - second.toInt()
            '*' -> result = first.toInt() * second.toInt()
            '/' -> result = first.toInt() / second.toInt()
        }

        return result
    }

    private fun getCompChoice(compChoice: List<String>) = (Math.random() * compChoice.size).toInt()
}