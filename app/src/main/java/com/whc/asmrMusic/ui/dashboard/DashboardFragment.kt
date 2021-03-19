package com.whc.asmrMusic.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.whc.asmrMusic.R
import com.whc.asmrMusic.databinding.FragmentDashboardBinding
import com.whc.asmrMusic.databinding.FragmentPagingBinding
import com.whc.asmrMusic.ui.base.BaseFragment
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timerTask

class DashboardFragment : BaseFragment() {

    private lateinit var binding:FragmentDashboardBinding

    override fun createContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentDashboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showError("32423")
        val timer = Timer()
        timer.schedule(timerTask {

            showContent()
        }, TimeUnit.SECONDS.toMillis(3))

    }

}