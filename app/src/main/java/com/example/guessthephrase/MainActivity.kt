package com.example.guessthephrase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var myButton: Button
    lateinit var textInput: TextView
    lateinit var blackText: TextView
    lateinit var outText: TextView

    var phrase = "Hello World"
    var stars = ""
    var chars = ArrayList<Char>()
    var guess = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myButton = findViewById(R.id.submitButton)
        myButton.setOnClickListener{ guessText() }

        textInput = findViewById(R.id.textInput)
        blackText = findViewById(R.id.blackText)
        outText = findViewById(R.id.outText)

        outText.visibility = View.INVISIBLE

        createStars()
    }

    private fun guessText() {
        if(textInput.text.toString() == "") return
        if(textInput.text.toString().length == 1){
            showLetter(textInput.text.toString().toCharArray()[0])
        }
        else {
            if(textInput.text.toString().trim().equals(phrase, true)){
                restart("You won!\ntry again?")
            }
            else {
                guess--
                if(guess == 0) {
                    restart("You lose!\ntry again")
                }
                outText.text = "$guess guesses remaining"
            }
        }
        outText.visibility = View.VISIBLE
    }

    private fun restart(outputText: String){
        createStars()
        chars.clear()
        guess == 10
        outText.text = outputText
        updateBlackText()
        textInput.text = ""
    }

    private fun showLetter(letter: Char){
        val realChars = phrase.toCharArray()

        var i = 0
        for (char in realChars) {
            if(char.equals(letter, true)) {
                val starChars = stars.toCharArray()
                starChars[i] = char
                stars = String(starChars)

                val fixedChar = char.toUpperCase()
                if(!chars.contains(fixedChar)) chars.add(fixedChar)
            }
            i++
        }

        updateBlackText()

        guess--
        if(guess == 0) {
            restart("You lose!\ntry again")
        }
        else outText.text = "$guess guesses remaining"
    }

    private fun updateBlackText(){
        blackText.text = "$stars\n Guessed Letters:"
        for (char in chars) {
            blackText.text = "${blackText.text} $char"
        }
    }

    private fun createStars(){
        val chars = phrase.toCharArray()
        var i = 0
        for (index in phrase.indices) {
            if(chars[index] != ' ') chars[index] = '*'
        }
        stars = String(chars)
        updateBlackText()
    }
}