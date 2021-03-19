package com.whc.asmrMusic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.transition.ChangeBounds
import com.whc.asmrMusic.R
import com.whc.asmrMusic.databinding.FragmentDetailBinding
import com.whc.asmrMusic.databinding.FragmentPagingBinding
import com.whc.asmrMusic.ui.base.BaseFragment


class DetailFragment : BaseFragment() {

    lateinit var binding: FragmentDetailBinding

    override fun createContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val position= arguments?.getString("position","")
//        ViewCompat.setTransitionName(imageView, "imageView$position")
    }


}