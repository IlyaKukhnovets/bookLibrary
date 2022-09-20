package com.example.bookapp.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookapp.R
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.presentation.base.BaseFragment

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : BaseFragment(R.layout.fragment_splash_screen) {

    private val viewModel by viewModels<SplashScreenViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.delayLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingResult.Success -> {
                    findNavController().navigate(R.id.action_splashScreenFragment_to_booksFragmentContainer)
                }
                else -> {}
            }
        }
    }
}