package com.penatabahasa.suitgamev3.controller

interface Callback {
    fun result(text: Int, background: Int, textColor: Int)
    fun checkGame(resultGame: String)
}