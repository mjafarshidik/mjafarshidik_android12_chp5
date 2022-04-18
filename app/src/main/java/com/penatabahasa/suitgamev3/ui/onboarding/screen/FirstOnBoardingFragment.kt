package com.penatabahasa.suitgamev3.ui.onboarding.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.penatabahasa.suitgamev3.databinding.FragmentFirstOnBoardingBinding

class FirstOnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentFirstOnBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load("https://raw.githubusercontent.com/mjafarshidik/assets-chapter5/master/PNG/landing-page1.png")
            .override(500, 500)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.ivOnBoard)
    }
}