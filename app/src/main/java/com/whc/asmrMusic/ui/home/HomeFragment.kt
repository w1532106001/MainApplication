package com.whc.asmrMusic.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.whc.asmrMusic.R
import com.whc.asmrMusic.common.AppDatabase
import com.whc.asmrMusic.databinding.FragmentHomeBinding
import com.whc.asmrMusic.model.Diary
import com.whc.asmrMusic.ui.base.BaseFragment
import java.util.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    lateinit var itemAdapter: ItemAdapter

    lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var database: AppDatabase

    override fun createContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        itemAdapter = ItemAdapter().apply {
            setOnItemChildClickListener { _, view, _ ->
                when (view.id) {
                    R.id.itemLayout -> {
                        view as ConstraintLayout
                        val imageView = view.getViewById(R.id.imageView) as ImageView
                        val extras = FragmentNavigatorExtras(imageView to "secondTransitionName")
                        findNavController().navigate(
                            R.id.action_navigation_home_to_detailFragment,
                            null,
                            null,
                            extras
                        )
                    }
                }
            }
        }
        val list = arrayListOf<String>()
        for (i in 0..10) {
            list.add(i.toString())
        }

        itemAdapter.addChildClickViewIds(R.id.itemLayout)
        binding.recyclerView.adapter = itemAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.apply {
//            postponeEnterTransition()
//            viewTreeObserver
//                .addOnPreDrawListener {
//                    startPostponedEnterTransition()
//                    true
//                }
        }
        itemAdapter.setNewInstance(list)

        database.getDiaryDao().insert(Diary().apply {
            text = "123"
            updateCount = 0
            createTime = Date()
            updateTime = Date()
        })
    }

    class ItemAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_view) {
        override fun convert(holder: BaseViewHolder, item: String) {
            val imageView = holder.getView<ImageView>(R.id.imageView)
            ViewCompat.setTransitionName(imageView, "imageView${holder.adapterPosition}")

        }

    }


}