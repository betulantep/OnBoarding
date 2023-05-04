package com.betulantep.onboarding.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.betulantep.onboarding.ViewPagerAdapter
import com.betulantep.onboarding.databinding.FragmentOnBoardingBinding
import com.betulantep.onboarding.screens.FirstScreen
import com.betulantep.onboarding.screens.SecondScreen
import com.betulantep.onboarding.screens.ThirdScreen

class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        binding.fragment = this
        val fragmentList = arrayListOf(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        binding.viewPager.adapter = ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)
        binding.wormDotsIndicator.setViewPager2(viewPager2 = binding.viewPager)

        viewPager2Listener()

        return binding.root
    }

    private fun viewPager2Listener() {
        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2){
                    binding.btnNext.visibility = View.INVISIBLE
                    binding.btnFinish.visibility = View.VISIBLE
                }else{
                    binding.btnNext.visibility = View.VISIBLE
                    binding.btnFinish.visibility = View.GONE
                }
            }
        })
    }

    fun nextButtonClicked(){
        binding.viewPager.currentItem++
    }

    fun goToHomePage(){
        findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToHomeFragment())
    }
}