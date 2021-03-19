package com.whc.asmrMusic.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.add
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.whc.asmrMusic.R
import com.whc.asmrMusic.databinding.FragmentDashboardBinding
import com.whc.asmrMusic.databinding.FragmentHomeBinding
import com.whc.asmrMusic.ui.DetailFragment
import com.whc.asmrMusic.ui.base.BaseFragment
import org.jetbrains.anko.view

class HomeFragment : BaseFragment() {

    lateinit var itemAdapter: ItemAdapter

    lateinit var binding:FragmentHomeBinding

    override fun createContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        itemAdapter = ItemAdapter().apply {
            setOnItemChildClickListener { adapter, view, position ->
                when (view.id) {
                    R.id.itemLayout -> {
//                        view as ConstraintLayout
//                        val imageView = view.getViewById(R.id.imageView) as ImageView
//
//
//                        val extras = FragmentNavigatorExtras( imageView to "secondTransitionName")
//                        findNavController().navigate(R.id.action_navigation_home_to_detailFragment,null,null,extras)
                        findNavController().navigate(R.id.action_navigation_home_to_play1Fragment,null)
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

    }

    class ItemAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_view) {
        override fun convert(holder: BaseViewHolder, item: String) {
            val imageView = holder.getView<ImageView>(R.id.imageView)
            ViewCompat.setTransitionName(imageView, "imageView${holder.adapterPosition}")

        }

    }
}