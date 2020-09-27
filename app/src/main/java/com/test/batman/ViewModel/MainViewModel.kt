package com.test.batman.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.batman.model.SearchItemModel
import com.test.batman.Repository.MainRepository

class MainViewModel(private val mainRepository : MainRepository) : ViewModel() {

//    private val mainLiveData = MutableLiveData<SearchResponseModel>()
//    val data: LiveData<SearchResponseModel>
//    get() = data

    fun getMainPage(context: Context): MutableLiveData<List<SearchItemModel>>{
        return mainRepository.getMainPage(context)
    }
}