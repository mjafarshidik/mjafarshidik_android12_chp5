package com.penatabahasa.suitgamev3.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.penatabahasa.suitgamev3.databinding.ActivityOnBoardingBinding
import com.penatabahasa.suitgamev3.ui.onboarding.adapter.ViewPagerAdapter
import com.penatabahasa.suitgamev3.ui.onboarding.screen.FirstOnBoardingFragment
import com.penatabahasa.suitgamev3.ui.onboarding.screen.SecondOnBoardingFragment
import com.penatabahasa.suitgamev3.ui.onboarding.screen.ThirdOnBoardingFragment

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showOnBoarding()
    }

    private fun showOnBoarding() {
        binding.apply {
            val fragment: ArrayList<Fragment> = arrayListOf(
                FirstOnBoardingFragment(),
                SecondOnBoardingFragment(),
                ThirdOnBoardingFragment()
            )
            val adapter = ViewPagerAdapter(
                fragment, this@OnBoardingActivity
            )
            vpOnBoarding.adapter = adapter
            dotsIndicator.setViewPager2(vpOnBoarding)
        }
    }
}