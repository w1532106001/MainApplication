package com.whc.asmrMusic.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.whc.asmrMusic.R
import com.whc.asmrMusic.databinding.FragmentNotificationsBinding
import com.whc.asmrMusic.ui.base.BaseFragment

class NotificationsFragment : BaseFragment() {

    lateinit var binding: FragmentNotificationsBinding

    override fun createContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentNotificationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showMessage("25324234")
    }
}