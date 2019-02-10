package com.example.games

import android.content.res.ColorStateList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    var scoreP1win = 0
    var scoreP2win = 0
    var scoreTie = 0
    var player = 1
    var gameOver : Boolean = false
    var p1 = ArrayList<Int>()
    var p2 = ArrayList<Int>()
    var buttons = ArrayList<Int>()
    var cont = 0
    var autoOn = false
    var rand = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var resetButton : Button = findViewById<Button>(R.id.reset)
        resetButton.isEnabled = false
        resetButton.visibility = View.INVISIBLE

        var scoreP1 : TextView = findViewById(R.id.scoreP1)
        scoreP1.text = "Player 1: $scoreP1win"
        var scoreP2 : TextView = findViewById(R.id.scoreP2)
        scoreP2.text = "Player 2: $scoreP2win"
        var scoreTieText : TextView = findViewById(R.id.scoreTie)
        scoreTieText.text = "Ties: $scoreTie"

    }
    fun randRoutine(){
        if(cont <= 8) {
            val selectedButtonP2 = generateRanButton()
            selectedButtonP2.text = "O"
            selectedButtonP2.setBackgroundResource(R.color.green)
            selectedButtonP2.isEnabled = false
            p2.add(rand)
            player = 1
            cont++
        }

        andTheWinnerIs()
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    fun gameOn(buttonCode:Int, selectedButton:Button){
        var isAnyWinner : Boolean
        if(buttonCode == 10){
            resetBoard()
        }else {

            if (player == 1) {
                selectedButton.text = "X"
                selectedButton.setBackgroundResource(R.color.blue)
                p1.add(buttonCode)
                player = 2
                isAnyWinner = andTheWinnerIs()
                if(autoOn && !isAnyWinner){
                   randRoutine()
                }
            } else if (player == 2 && autoOn == false) {
                selectedButton.text = "O"
                selectedButton.setBackgroundResource(R.color.green)
                p2.add(buttonCode)
                player = 1
                andTheWinnerIs()
            }else if(player == 1 && autoOn){
                val selectedButtonP2 = generateRanButton()
                selectedButtonP2.text = "X"
                selectedButtonP2.setBackgroundResource(R.color.blue)
                selectedButtonP2.isEnabled = false
                p1.add(rand)
                player = 2
                andTheWinnerIs()
            }else{

            }

            selectedButton.isEnabled = false
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    fun andTheWinnerIs() : Boolean{
        var array = ArrayList<Button>()
        array.add(findViewById<Button>(R.id.button))
        array.add(findViewById<Button>(R.id.button2))
        array.add(findViewById<Button>(R.id.button3))
        array.add(findViewById<Button>(R.id.button4))
        array.add(findViewById<Button>(R.id.button5))
        array.add(findViewById<Button>(R.id.button6))
        array.add(findViewById<Button>(R.id.button7))
        array.add(findViewById<Button>(R.id.button8))
        array.add(findViewById<Button>(R.id.button9))

        var win = 0

        if(cont >= 9) win = 3

        if(p1.contains(1) && p1.contains(2) && p1.contains(3)) win = 1
        if(p2.contains(1) && p2.contains(2) && p2.contains(3)) win = 2

        if(p1.contains(1) && p1.contains(4) && p1.contains(7)) win = 1
        if(p2.contains(1) && p2.contains(4) && p2.contains(7)) win = 2

        if(p1.contains(1) && p1.contains(5) && p1.contains(9)) win = 1
        if(p2.contains(1) && p2.contains(5) && p2.contains(9)) win = 2

        if(p1.contains(3) && p1.contains(5) && p1.contains(7)) win = 1
        if(p2.contains(3) && p2.contains(5) && p2.contains(7)) win = 2

        if(p1.contains(4) && p1.contains(5) && p1.contains(6)) win = 1
        if(p2.contains(4) && p2.contains(5) && p2.contains(6)) win = 2

        if(p1.contains(7) && p1.contains(8) && p1.contains(9)) win = 1
        if(p2.contains(7) && p2.contains(8) && p2.contains(9)) win = 2

        if(p1.contains(2) && p1.contains(5) && p1.contains(8)) win = 1
        if(p2.contains(2) && p2.contains(5) && p2.contains(8)) win = 2

        if(p1.contains(3) && p1.contains(6) && p1.contains(9)) win = 1
        if(p2.contains(3) && p2.contains(6) && p2.contains(9)) win = 2

        if(win != 0){
            gameOver = true
            for( button in array){
                button.isEnabled = false
            }
            if(win == 1){
                player = 1
                scoreP1win++
            }
            if(win == 2){
                player = 2
                scoreP2win++
            }
            if(win ==1 || win ==2) {
                Toast.makeText(this, "And the winner is PLAYER $win", Toast.LENGTH_LONG).show()
            }else {
                scoreTie++
                Toast.makeText(this, "TIE", Toast.LENGTH_LONG).show()
            }
            cont = 0
            var resetButton : Button = findViewById<Button>(R.id.reset)
            resetButton.isEnabled = true
            resetButton.visibility = View.VISIBLE
            var scoreP1 : TextView = findViewById(R.id.scoreP1)
            scoreP1.text = "Player 1: $scoreP1win"
            var scoreP2 : TextView = findViewById(R.id.scoreP2)
            scoreP2.text = "Player 2: $scoreP2win"
            var scoreTieText : TextView = findViewById(R.id.scoreTie)
            scoreTieText.text = "Ties: $scoreTie"
            return true
        }else{
            return false
        }


    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    fun resetBoard() {
        cont = 0
        gameOver = false
        var array = ArrayList<Button>()
        array.add(findViewById<Button>(R.id.button))
        array.add(findViewById<Button>(R.id.button2))
        array.add(findViewById<Button>(R.id.button3))
        array.add(findViewById<Button>(R.id.button4))
        array.add(findViewById<Button>(R.id.button5))
        array.add(findViewById<Button>(R.id.button6))
        array.add(findViewById<Button>(R.id.button7))
        array.add(findViewById<Button>(R.id.button8))
        array.add(findViewById<Button>(R.id.button9))

        for(button in array){
            button.setBackgroundResource(R.color.grayButton)
            button.text = ""
            button.isEnabled = true
        }

        p1.clear()
        p2.clear()
        buttons.clear()

        var resetButton : Button = findViewById<Button>(R.id.reset)
        resetButton.isEnabled = false
        resetButton.visibility = View.INVISIBLE

        if(autoOn && player == 2){
            randRoutine()
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    fun select(view: View) {
        val selectedButton = view as Button
        var buttonCode = 0
        when(selectedButton.id){
            R.id.button -> buttonCode = 1
            R.id.button2 -> buttonCode = 2
            R.id.button3 -> buttonCode = 3
            R.id.button4 -> buttonCode = 4
            R.id.button5 -> buttonCode = 5
            R.id.button6 -> buttonCode = 6
            R.id.button7 -> buttonCode = 7
            R.id.button8 -> buttonCode = 8
            R.id.button9 -> buttonCode = 9
            R.id.reset -> buttonCode = 10
        }
        when(buttonCode){
            1,2,3,4,5,6,7,8,9 -> cont++
        }
        when(buttonCode){
            1,2,3,4,5,6,7,8,9 -> buttons.add(buttonCode)
        }
        gameOn(buttonCode, selectedButton)
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    fun autoPlayer(view: View){
        var autoOnButton :Button = findViewById(R.id.auto)
        autoOn = !autoOn
        if(autoOn && player == 2 && !gameOver){
            randRoutine()
        }
        if(autoOn){
            Toast.makeText(this, "ON", Toast.LENGTH_LONG).show()
            autoOnButton.setBackgroundResource(R.color.colorPrimaryDark)
            autoOnButton.setTextColor(resources.getColor(R.color.white))
        }else{
            Toast.makeText(this, "OFF", Toast.LENGTH_LONG).show()
            autoOnButton.setBackgroundResource(R.color.colorPrimary)
            autoOnButton.setTextColor(resources.getColor(R.color.black))

        }
    }
    ////////////////////////////////////////////////////////////////////////////////////
    fun generateRanButton() : Button{
        var flag : Boolean = false
        var selectedButton :Button = findViewById(R.id.button)
        rand = Random.nextInt(1,10) //Número random 1, 2,..., 9
        do {
            flag = false
            for(int in buttons){
                if(rand == int){
                    rand = Random.nextInt(1,10)
                    flag = true
                }
            }
        }while (flag)
        buttons.add(rand)
        when(rand){
            1 -> selectedButton = findViewById<Button>(R.id.button)
            2 -> selectedButton = findViewById<Button>(R.id.button2)
            3 -> selectedButton = findViewById<Button>(R.id.button3)
            4 -> selectedButton = findViewById<Button>(R.id.button4)
            5 -> selectedButton = findViewById<Button>(R.id.button5)
            6 -> selectedButton = findViewById<Button>(R.id.button6)
            7 -> selectedButton = findViewById<Button>(R.id.button7)
            8 -> selectedButton = findViewById<Button>(R.id.button8)
            9 -> selectedButton = findViewById<Button>(R.id.button9)
        }
        return selectedButton
    }

}
