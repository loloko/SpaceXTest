package com.fernando.spacex.ui


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.fernando.spacex.databinding.FragmentRocketDetailBinding
import com.fernando.spacex.extension.createLoadingPopup
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RocketDetailFragment @Inject constructor() : DaggerFragment() {

    private val args: RocketDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentRocketDetailBinding

    private val loadingPopup by lazy {
        requireContext().createLoadingPopup()
    }

    // View initialization logic
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Binding
        binding = FragmentRocketDetailBinding.inflate(layoutInflater)

        return binding.root
    }

    // Post view initialization logic
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.rocket = args.rocket
        binding.executePendingBindings()

        // Send user to wikipedia
        binding.btOpenWikipedia.setOnClickListener {
            loadingPopup.show()
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(args.rocket.wikipedia)))
        }

    }

    override fun onStop() {
        super.onStop()

        if (loadingPopup.isShowing)
            loadingPopup.dismiss()
    }

}