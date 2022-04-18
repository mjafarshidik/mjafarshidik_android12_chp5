package com.penatabahasa.suitgamev3.controller

import android.util.Log
import com.penatabahasa.suitgamev3.R

class Controller(private val callback: Callback) : InterfaceController {
    override fun cekSuit(
        player1: CharSequence,
        com: CharSequence,
        playerOne: String,
        playerTwo: String
    ) {
        if (player1 == com) {
            Log.d("result", "draw")
            callback.result(R.string.draw, R.color.colorBlue, R.color.white)
            callback.checkGame("DRAW")

        } else if (player1 == "rock" && com == "scissor" || player1 == "scissor" && com == "paper" || player1 == "paper" && com == "rock") {
            Log.d("result", "pemain 1 menang")
            callback.result(R.string.pemain1menang, R.color.colorGreen, R.color.white)
            callback.checkGame("$playerOne WIN")

        } else {
            Log.d("result", "pemain 2 menang")
            callback.result(R.string.pemain2menang, R.color.colorGreen, R.color.white)
            callback.checkGame("$playerTwo WIN")
        }
    }
}