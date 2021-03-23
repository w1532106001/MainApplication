package com.whc.asmrMusic.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.whc.asmrMusic.common.AppDatabase
import com.whc.asmrMusic.model.Diary
import javax.inject.Inject


class HomeViewModel @Inject constructor(val database: AppDatabase) : ViewModel() {
    private val searchText = MutableLiveData<String>("")
    var diaryLiveData: LiveData<MutableList<Diary>> = Transformations.switchMap(searchText) {
        database.getDiaryDao().getAllDiary("%${it}%")
    }

    fun updateSearchText(text: String) {
        searchText.value = text
    }

}