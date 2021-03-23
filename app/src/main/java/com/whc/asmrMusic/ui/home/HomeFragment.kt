package com.whc.asmrMusic.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.whc.asmrMusic.R
import com.whc.asmrMusic.databinding.FragmentHomeBinding
import com.whc.asmrMusic.model.Diary
import com.whc.asmrMusic.ui.base.BaseFragment
import java.util.*


class HomeFragment : BaseFragment() {

    lateinit var diaryAdapter: DiaryAdapter

    lateinit var binding: FragmentHomeBinding


    override fun createContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        diaryAdapter = DiaryAdapter().apply {
            setOnItemChildClickListener { _, view, position ->
                when (view.id) {
                    R.id.itemLayout -> {
//                        Diaryed.confirmationAction(amount)
//                        Diaryed
                        val bundle = Bundle() //建立bundle用以将数据带过去，并在下面controller中带入bundle

                        bundle.putSerializable("diary", data[position])
                        findNavController().navigate(
                            R.id.action_navigation_home_to_diaryEditFragment,
                            bundle
                        )
                    }
                }
            }
        }
        diaryAdapter.addChildClickViewIds(R.id.itemLayout)
        binding.recyclerView.adapter = diaryAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        val viewModel = getViewModel(HomeViewModel::class.java)


        viewModel?.diaryLiveData?.observe(viewLifecycleOwner, Observer {
            diaryAdapter.setList(it)
        })


        binding.searchEditText.setOnEditorActionListener { _, _, _ ->
            hideKeyboard(binding.searchEditText)
            searchDiaryByText(binding.searchEditText.text.toString())
            return@setOnEditorActionListener false
        }
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty()) {
                    hideKeyboard(binding.searchEditText)
                    binding.searchEditTextClearButton.visibility = View.GONE
                } else {
                    binding.searchEditTextClearButton.visibility = View.VISIBLE
                }
                searchDiaryByText(s.toString())
            }

        }
        )
        binding.searchEditTextClearButton.setOnClickListener {
            binding.searchEditText.text = null
            it.visibility = View.GONE
        }


    }

    class DiaryAdapter : BaseQuickAdapter<Diary, BaseViewHolder>(R.layout.item_diary) {
        override fun convert(holder: BaseViewHolder, item: Diary) {
            holder.setText(R.id.textView, item.text)
        }
    }

    fun searchDiaryByText(text: String) {
        val viewModel = getViewModel(HomeViewModel::class.java)
        viewModel?.updateSearchText(text)
    }

}