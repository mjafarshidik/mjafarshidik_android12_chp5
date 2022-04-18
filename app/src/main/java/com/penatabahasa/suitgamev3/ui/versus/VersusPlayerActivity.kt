package com.penatabahasa.suitgamev3.ui.versus

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.penatabahasa.suitgamev3.R
import com.penatabahasa.suitgamev3.controller.Callback
import com.penatabahasa.suitgamev3.controller.CallbackDialog
import com.penatabahasa.suitgamev3.controller.Controller
import com.penatabahasa.suitgamev3.databinding.ActivityVersusPlayerBinding
import com.penatabahasa.suitgamev3.ui.dialog.ResultDialogFragment

open class VersusPlayerActivity : AppCompatActivity(), Callback, CallbackDialog {
    private lateinit var binding: ActivityVersusPlayerBinding
    private var playerOneResult = ""
    private var playerTwoResult = ""
    val name by lazy { intent.getStringExtra("name") }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVersusPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playGame()
    }

    private fun playGame() {
        binding.apply {
            tvUserName.text = name
            val playerOneName = name.toString()
            val playerTwoName = "Pemain 2"

            val btnPlayerOne = arrayOf(
                ivOptionPaperP1,
                ivOptionRockP1,
                ivOptionScissorP1
            )

            val btnPlayerTwo = arrayOf(
                ivOptionPaperP2,
                ivOptionRockP2,
                ivOptionScissorP2
            )

            val controllerVersusPlayer = Controller(this@VersusPlayerActivity)
            btnPlayerOne.forEachIndexed { index, imageView ->
                imageView.setOnClickListener {
                    Log.d(
                        "${btnPlayerOne[index].contentDescription}",
                        "Clicked"
                    )
                    playerOneResult = btnPlayerOne[index].contentDescription.toString()
                    disableClickPlayer1(false)
                    Toast.makeText(
                        this@VersusPlayerActivity,
                        btnPlayerOne[index].contentDescription,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    btnPlayerOne.forEach {
                        it.setBackgroundResource(android.R.color.transparent)
                    }
                    btnPlayerOne[index].setBackgroundResource(R.drawable.bg_image)
                }
            }
            btnPlayerTwo.forEachIndexed { index, imageView ->
                imageView.setOnClickListener {
                    Log.d(
                        "${btnPlayerTwo[index].contentDescription}",
                        "Clicked"
                    )
                    playerTwoResult = btnPlayerTwo[index].contentDescription.toString()
                    disableClickPlayer2(false)
                    if (playerOneResult != "") {
                        controllerVersusPlayer.cekSuit(
                            playerOneResult,
                            playerTwoResult,
                            playerOneName,
                            playerTwoName
                        )
                        Toast.makeText(
                            this@VersusPlayerActivity,
                            btnPlayerTwo[index].contentDescription,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        btnPlayerTwo.forEach {
                            it.setBackgroundResource(android.R.color.transparent)
                        }
                        btnPlayerTwo[index].setBackgroundResource(R.drawable.bg_image)
                    } else {
                        btnPlayerTwo[index].setBackgroundResource(android.R.color.transparent)
                        Toast.makeText(
                            this@VersusPlayerActivity,
                            "Silahkan restart dan pilih pilihan pemain 1",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            ivRefresh.setOnClickListener {
                Toast.makeText(
                    this@VersusPlayerActivity,
                    "Mulai ulang permainan",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("reset", "Clicked")
                result(R.string.vs, android.R.color.transparent, R.color.colorRed)
                playerOneResult = ""
                playerTwoResult = ""
                restartGame(android.R.color.transparent, "", "")
            }

            ivClose.setOnClickListener {
                finish()
            }
        }
    }

    private fun disableClickPlayer2(isEnable: Boolean) {
        binding.apply {
            ivOptionPaperP2.isEnabled = isEnable
            ivOptionRockP2.isEnabled = isEnable
            ivOptionScissorP2.isEnabled = isEnable
        }
    }

    private fun disableClickPlayer1(isEnable: Boolean) {
        binding.apply {
            ivOptionPaperP1.isEnabled = isEnable
            ivOptionRockP1.isEnabled = isEnable
            ivOptionScissorP1.isEnabled = isEnable
        }
    }

    override fun result(text: Int, background: Int, textColor: Int) {
        binding.apply {
            tvResult.text = getString(text).replace("Pemain 1", name.toString())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tvResult.setBackgroundColor(getColor(background))
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tvResult.setTextColor(getColor(textColor))
            }
            tvResult.textSize = 24f
        }
    }

    override fun checkGame(resultGame: String) {
        val resultDialog = ResultDialogFragment()
        val bundle = Bundle()
        bundle.putString("result", resultGame)
        resultDialog.arguments = bundle
        resultDialog.show(supportFragmentManager, "DialogResult")
    }

    override fun restartGame(
        background: Int,
        resultPlayerOne: String,
        resultPlayerTwo: String
    ) {
        binding.apply {
            ivOptionRockP1.setBackgroundResource(background)
            ivOptionPaperP1.setBackgroundResource(background)
            ivOptionScissorP1.setBackgroundResource(background)
            ivOptionRockP2.setBackgroundResource(background)
            ivOptionPaperP2.setBackgroundResource(background)
            ivOptionScissorP2.setBackgroundResource(background)
            tvResult.text = getString(R.string.vs)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tvResult.setBackgroundColor(getColor(android.R.color.transparent))
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tvResult.setTextColor(getColor(R.color.colorRed))
            }
            tvResult.textSize = 50f
            disableClickPlayer1(true)
            disableClickPlayer2(true)
            playerOneResult = ""
            playerTwoResult = ""
        }
    }
}