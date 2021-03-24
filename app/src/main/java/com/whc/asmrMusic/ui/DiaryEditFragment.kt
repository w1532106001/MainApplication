package com.whc.asmrMusic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.whc.asmrMusic.R
import com.whc.asmrMusic.common.AppDatabase
import com.whc.asmrMusic.databinding.DiaryEditFragmentBinding
import com.whc.asmrMusic.model.Diary
import com.whc.asmrMusic.ui.base.BaseFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class DiaryEditFragment : BaseFragment() {
    lateinit var binding: DiaryEditFragmentBinding
    var text = ""
    var type = DiaryEditType.EDIT
    private var diary:Diary? = null

    @Inject
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = requireArguments().getSerializable("type") as DiaryEditType
        diary = requireArguments().getSerializable("diary") as? Diary?
    }

    override fun createContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DiaryEditFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseBinding.toolbarBase.apply {
            visibility = View.VISIBLE
            title = "备忘录"
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        binding.editTextView.setText(diary?.text)
    }


    override fun onDestroyView() {
        GlobalScope.launch {
            database.getDiaryDao().insert(when(type){
                DiaryEditType.ADD->{
                    Diary().apply {
                        text = binding.editTextView.text.toString()
                        updateCount = 0
                        createTime = Date()
                        updateTime = Date()
                    }
                }
                DiaryEditType.EDIT->{
                    diary!!.apply {
                        text = binding.editTextView.text.toString()
                        updateCount++
                        updateTime = Date()
                    }
                }
            })
        }


        super.onDestroyView()
    }




    enum class DiaryEditType{
        ADD,
        EDIT
    }


}