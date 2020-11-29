package com.fernando.spacex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.fragment.findNavController
import com.fernando.spacex.databinding.FragmentIntroBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class IntroFragment @Inject constructor() : DaggerFragment() {

    private lateinit var binding: FragmentIntroBinding

    // View initialization logic
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // View binding
        binding = FragmentIntroBinding.inflate(layoutInflater)

        // End of animation, send user to MainActivity
        binding.motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                navToRocketList()
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {}

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}
        })


        return binding.root
    }

    private fun navToRocketList() {
        val directions = IntroFragmentDirections.navigateToRocketList()
        findNavController().navigate(directions)
    }

}