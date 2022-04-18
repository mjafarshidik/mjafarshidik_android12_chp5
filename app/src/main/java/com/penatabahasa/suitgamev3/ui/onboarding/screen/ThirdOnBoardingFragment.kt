package com.penatabahasa.suitgamev3.ui.onboarding.screen

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.penatabahasa.suitgamev3.ui.main.MainActivity
import com.penatabahasa.suitgamev3.databinding.FragmentThirdOnBoardingBinding

class ThirdOnBoardingFragment : Fragment() {
    private var _binding: FragmentThirdOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load("https://raw.githubusercontent.com/mjafarshidik/assets-chapter5/master/PNG/landing-page3.png")
            .override(500, 500)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.ivOnBoard)

        getUserName()
    }

    private fun getUserName() {
        binding.apply {
            edtName.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    ivNext.isVisible = s.toString().trim().isNotEmpty()
                }
            })

            ivNext.setOnClickListener {
                if (edtName.text.isNotEmpty()) {
                    val name = edtName.text.toString()
                    val intent = Intent(activity, MainActivity::class.java)
                    intent.putExtra("name", name)
                    startActivity(intent)
                } else {
                   Toast.makeText(activity, "Masukkan nama anda terlebih dahulu", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}