package com.example.tictac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
    var Player = true
    var TurnCount =0
    var BoardStatus =Array(3){IntArray(3)}
    lateinit var board: Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board = arrayOf(arrayOf(button1,button2,button3), arrayOf(button4,button5,button6), arrayOf(button7,button8,button9))
        for(i :Array<Button> in board)
        {
            for(button:Button in i)
            {
                button.setOnClickListener(this)
            }

        }
        initializeBoardStatus()
        resetbtn.setOnClickListener {
            Player = true
            TurnCount = 0
            initializeBoardStatus()
        }
    }

    private fun initializeBoardStatus() {
        for (i in 0..2){
            for(j in 0..2){
                BoardStatus[i][j] = -1

            }
        }
        for(i :Array<Button> in board)
        {
            for(button:Button in i)
            {
                button.isEnabled = true
                button.text = ""
            }

        }
    }

    override fun onClick(view: View) {
        when(view.id)
        {
            R.id.button1 ->{
                updateValue(row =0 , col =0,player=Player)
            }
            R.id.button2 ->{
                updateValue(row =0 , col =1,player=Player)
            }
            R.id.button3 ->{
                updateValue(row =0 , col =2,player=Player)
            }
            R.id.button4 ->{
                updateValue(row =1 , col =0,player=Player)
            }
            R.id.button5 ->{
                updateValue(row =1 , col =1,player=Player)
            }
            R.id.button6 ->{
                updateValue(row =1 , col =2,player=Player)
            }
            R.id.button7 ->{
                updateValue(row =2 , col =0,player=Player)
            }
            R.id.button8 ->{
                updateValue(row =2 , col =1,player=Player)
            }
            R.id.button9 ->{
                updateValue(row =2 , col =2,player=Player)
            }
        }
        Player = !Player
        TurnCount ++
        if(Player){
            updateDisplay("Player X Turns")
        }else{
            updateDisplay("Player O Turns")
        }
        if(TurnCount == 9){
            updateDisplay("Game Draw")
        }
        checkWinner()
    }

    private fun checkWinner() {
        // for horizontal rows
        for(i in 0..2)
        {
            if(BoardStatus[i][0] == BoardStatus[i][1] && BoardStatus[i][0] == BoardStatus[i][2]){

                if(BoardStatus[i][0] ==1)
                {
                    updateDisplay("Player X is winner")
                    break;
                }else if(BoardStatus[i][0] ==0){
                    updateDisplay("player O is winner")
                    break;
                }
            }

        }
        // vertical colums
        for(i in 0..2)
        {
            if(BoardStatus[0][i] == BoardStatus[1][i] && BoardStatus[0][i] == BoardStatus[2][i]){

                if(BoardStatus[0][i] ==1)
                {
                    updateDisplay("Player X is winner")
                    break;
                }else if(BoardStatus[0][i] ==0){
                    updateDisplay("player O is winner")
                    break;
                }
            }

        }
        // left diagonal
        if(BoardStatus[0][0] == BoardStatus[1][1] && BoardStatus[0][0] == BoardStatus[2][2]){

            if(BoardStatus[0][0] ==1)
            {
                updateDisplay("Player X is winner")

            }else  if(BoardStatus[0][0] ==0){
                updateDisplay("player O is winner")

            }
        }
        // right diagonal
        if(BoardStatus[0][2] == BoardStatus[1][1] && BoardStatus[0][2] == BoardStatus[2][0]){

            if(BoardStatus[0][2] ==1)
            {
                updateDisplay("Player X is winner")

            }else if (BoardStatus[0][2] ==0){
                updateDisplay("player O is winner")

            }
        }
    }

    private fun updateDisplay(text: String) {
        displayTV.text = text
        if(text.contains("winner"))
        {
            disable()
        }

    }
    private fun disable(){
        for(i :Array<Button> in board)
        {
            for(button:Button in i)
            {
                button.isEnabled = false;
            }

        }
    }
    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if(player) "X" else "O"
        val value =if(player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        BoardStatus[row][col] = value

    }
}
