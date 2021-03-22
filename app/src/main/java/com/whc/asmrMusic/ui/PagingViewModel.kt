//package com.whc.asmrMusic.ui
//
//import androidx.lifecycle.ViewModel
//import androidx.paging.DataSource
//import androidx.paging.LivePagedListBuilder
//import androidx.paging.PagedList
//
//class PagingViewModel() : ViewModel() {
//    lateinit var fragment: PagingFragment
//    var isLocalData = false
//    val dataSourceFactory = object : DataSource.Factory<Int, Comment>() {
//        override fun create(): DataSource<Int, Comment> {
//            return PagingFragment.DataSourceByPage(fragment,isLocalData)
//        }
//
//    }
//
//    private val config = PagedList.Config.Builder()
//        .setInitialLoadSizeHint(10)//初始化请求数据的长度（就是DataSource.loadInitial方法中的params.requestedLoadSize）
//        .setPageSize(10)//请求数据的长度（loadAfter和loadBefore方法）
//        .build()
//
//    val livePagedList = LivePagedListBuilder(dataSourceFactory, config).build()
//    val comments = arrayListOf<Comment>()
//
//
//}