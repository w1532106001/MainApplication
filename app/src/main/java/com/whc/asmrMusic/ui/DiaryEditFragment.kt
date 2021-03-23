package com.whc.asmrMusic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.whc.asmrMusic.databinding.DiaryEditFragmentBinding
import com.whc.asmrMusic.model.Diary
import com.whc.asmrMusic.ui.base.BaseFragment

class DiaryEditFragment : BaseFragment() {
    lateinit var binding: DiaryEditFragmentBinding
    var text = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        text = requireArguments().getString("text", "")
    }

    override fun createContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DiaryEditFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val diary = requireArguments().getSerializable("diary") as Diary
        binding.textView.text = diary.text
    }

}