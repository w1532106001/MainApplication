package com.whc.asmrMusic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.whc.asmrMusic.databinding.FragmentPagingBinding
import com.whc.asmrMusic.databinding.ItemCommentBinding
import com.whc.asmrMusic.ui.base.BaseFragment
import java.lang.Exception
import java.util.concurrent.TimeUnit

class PagingFragment : BaseFragment() {

    companion object {
        fun newInstance() = PagingFragment()
    }


    private lateinit var viewModel: PagingViewModel
    lateinit var titleAdapter: TitleAdapter
    lateinit var binding: FragmentPagingBinding

    val userComments:MutableList<Triple<Int,String,String>> = mutableListOf()



    override fun createContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentPagingBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PagingViewModel::class.java)
        viewModel.fragment = this
        baseBinding.appbarBase.visibility = View.GONE


        titleAdapter = TitleAdapter(this)
        binding.recyclerView.adapter = titleAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.livePagedList.observe(viewLifecycleOwner, Observer {
            titleAdapter.submitList(it)
        })

        userComments.add(Triple(1,"百度网友123","超喜欢王毅外长，能力出众的同时，在我们农村人看来他的面相气势都很出众，相当符合大国外长这个职位跟形象\n"))
        userComments.add(Triple(2,"长寿菩提兔","开诚布公，不藏着掖着 "))
        userComments.add(Triple(3,"上官","双方都需要认真看待自己的问题\n"))
        userComments.add(Triple(4,"清风","美国很强，事实如此，谁也没有否认，一直有不少国人在提醒，无需一直提醒。不要盲目自信是必须的，但是不要盲目自信不等于丢掉自信，也不代表不可以展示实力。适度的展示实力更不代表盲目自信。\n"))
        userComments.add(Triple(5,"清风来徐","这次谈话，是有划时代意义的\n"))

        binding.refreshLayout.setOnRefreshListener {
            viewModel.livePagedList.value?.dataSource?.invalidate()
            return@setOnRefreshListener
        }

    }


    class TitleAdapter(val fragment: PagingFragment) :PagedListAdapter<Comment, TitleAdapter.RecycleViewHolder>(comparator){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecycleViewHolder {
            val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return RecycleViewHolder(binding)
        }



        override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
            val item = getItem(position)
            item?.let {
                holder.binding.apply {
                    id.text = it.id.toString()
                    userName.text = it.userName
                    likeNum.text = it.likeNum.toString()
                    commentTextView.text = it.commentText
                    commentTextView.setOnClickListener {
                        item.likeNum++
                        fragment.viewModel.comments[position] = item
                        fragment.viewModel.livePagedList.value?.dataSource?.invalidate()
                    }
                    userName.setOnClickListener {
                        fragment.viewModel.comments.removeAt(position)
                        fragment.viewModel.livePagedList.value?.dataSource?.invalidate()

                    }
                }
            }
        }

        companion object {

            private val comparator = object : DiffUtil.ItemCallback<Comment>() {

                override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                    //比较唯一id
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                    //比较包含对象
                    return oldItem == newItem
                }

            }
        }

        class RecycleViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {

        }

    }



    class DataSourceByPage(
        val fragment: PagingFragment,
        var isLocalData: Boolean
    ):PageKeyedDataSource<Int,Comment>(){
        var index = 0
        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Comment>
        ) {
//            fragment.activity?.runOnUiThread {
//                fragment.binding.refreshLayout.isRefreshing = true
//            }

            if(isLocalData){
                callback.onResult(fragment.viewModel.comments,/*上一页的页码*/null,2)//回调结果
                isLocalData = false
                return
            }

            val data = getCommentData()
            fragment.viewModel.comments.addAll(data)
            callback.onResult(data,/*上一页的页码*/null,/*下一页的页面*/if(data.size<10){null}else{2})//回调结果

            fragment.activity?.runOnUiThread {
                fragment.binding.refreshLayout.isRefreshing = false
                fragment.binding.recyclerView.scrollToPosition(0)
            }

        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Comment>) {
            //获取下一页的数据
            val data = getCommentData()
            fragment.viewModel.comments.addAll(data)
            callback.onResult(data,/*下一页的页面*/if(data.size<10){null}else{params.key + 1})//回调结果
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Comment>) {
        }

        private fun addDelay(){
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1))
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

        private fun getCommentData(): ArrayList<Comment> {
            val list = arrayListOf<Comment>()

            for (i in 0 until if(index>=30){5}else{10}){
                index++
                val comment = Comment()
                comment.id = index
                val userComment = fragment.userComments.random()
                comment.userId =userComment.first
                comment.userName = userComment.second
                comment.commentText = userComment.third
                comment.likeNum = (0..1000).random()
                list.add(comment)
            }
            addDelay()
            return list
        }
    }
}