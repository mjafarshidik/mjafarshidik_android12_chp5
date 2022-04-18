package com.penatabahasa.suitgamev3.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.penatabahasa.suitgamev3.databinding.ActivityMainBinding
import com.penatabahasa.suitgamev3.ui.versus.VersusComputerActivity
import com.penatabahasa.suitgamev3.ui.versus.VersusPlayerActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        getImagesFromInternet()
        setUserName()
        moveToVersusPlayer()
        moveToVersusCom()
        showSnackBar()
    }

    private fun showSnackBar() {
        val name = intent.getStringExtra("name")
        Snackbar.make(
            mainBinding.mainActivity,
            "Selamat Datang $name",
            Snackbar.LENGTH_LONG
        ).setAction("Tutup") {
            Toast.makeText(this, "Silahkan pilih menu permainan", Toast.LENGTH_SHORT).show()
        }.show()
    }

    private fun moveToVersusCom() {
        mainBinding.apply {
            val name = intent.getStringExtra("name")
            ivVersusCom.setOnClickListener {
                val intent = Intent(this@MainActivity, VersusComputerActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
            }
        }
    }

    private fun moveToVersusPlayer() {
        mainBinding.apply {
            val name = intent.getStringExtra("name")
            ivVersusPlayer.setOnClickListener {
                val intent = Intent(this@MainActivity, VersusPlayerActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
            }
        }
    }

    private fun setUserName() {
        mainBinding.apply {
            val name = intent.getStringExtra("name")
            tvVersusPlayer.text = name.plus(" vs Pemain")
            tvVersusCom.text = name.plus(" vs CPU")
        }
    }

    private fun getImagesFromInternet() {
        mainBinding.apply {
            Glide.with(this@MainActivity)
                .load("https://raw.githubusercontent.com/mjafarshidik/assets-chapter5/master/PNG/landing-page1.png")
                .override(500, 500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivVersusPlayer)

            Glide.with(this@MainActivity)
                .load("https://raw.githubusercontent.com/mjafarshidik/assets-chapter5/master/PNG/landing-page2.png")
                .override(500, 500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivVersusCom)
        }
    }

    override fun onBackPressed() {
        super.finish()
    }
}