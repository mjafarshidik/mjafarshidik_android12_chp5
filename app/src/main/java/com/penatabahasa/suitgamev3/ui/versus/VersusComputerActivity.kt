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
import com.penatabahasa.suitgamev3.databinding.ActivityVersusComputerBinding
import com.penatabahasa.suitgamev3.ui.dialog.ResultDialogFragment

open class VersusComputerActivity : AppCompatActivity(), Callback, CallbackDialog {
    private lateinit var binding: ActivityVersusComputerBinding
    private var playerOneResult = ""
    private var playerTwoResult = ""
    val name by lazy { intent.getStringExtra("name") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVersusComputerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playGame()
    }

    private fun playGame() {
        binding.apply {
            tvUserName.text = name
            val playerOneName = name.toString()
            val playerTwoName = "CPU"

            val btnPlayerOne = arrayOf(
                ivOptionPaperP1,
                ivOptionRockP1,
                ivOptionScissorP1
            )

            val btnCpu = arrayOf(
                ivOptionPaperCom,
                ivOptionRockCom,
                ivOptionScissorCom
            )

            val controllerVsCpu = Controller(this@VersusComputerActivity)
            btnPlayerOne.forEachIndexed { index, imageView ->
                imageView.setOnClickListener {
                    val dataCpu = btnCpu.random()
                    Log.d(
                        "${btnPlayerOne[index].contentDescription} ${btnCpu[index].contentDescription}",
                        "Clicked"
                    )
                    dataCpu.setBackgroundResource(R.drawable.bg_image)
                    disableClick(false)
                    playerOneResult = btnPlayerOne[index].contentDescription.toString()
                    playerTwoResult = dataCpu.contentDescription.toString()
                    controllerVsCpu.cekSuit(
                        playerOneResult,
                        playerTwoResult,
                        playerOneName,
                        playerTwoName
                    )
                    Toast.makeText(
                        this@VersusComputerActivity,
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

            ivRefresh.setOnClickListener {
                Toast.makeText(this@VersusComputerActivity, "Mulai ulang permainan", Toast.LENGTH_SHORT).show()
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

    private fun disableClick(isEnable: Boolean) {
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

    override fun restartGame(background: Int, resultPlayerOne: String, resultPlayerTwo: String) {
        binding.apply {
            ivOptionRockP1.setBackgroundResource(background)
            ivOptionPaperP1.setBackgroundResource(background)
            ivOptionScissorP1.setBackgroundResource(background)
            ivOptionRockCom.setBackgroundResource(background)
            ivOptionPaperCom.setBackgroundResource(background)
            ivOptionScissorCom.setBackgroundResource(background)
            tvResult.text = getString(R.string.vs)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tvResult.setBackgroundColor(getColor(android.R.color.transparent))
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tvResult.setTextColor(getColor(R.color.colorRed))
            }
            tvResult.textSize = 50f
            disableClick(true)
            playerOneResult = ""
            playerTwoResult = ""
        }
    }
}