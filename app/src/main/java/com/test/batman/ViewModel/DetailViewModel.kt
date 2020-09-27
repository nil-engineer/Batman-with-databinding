package com.test.batman.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.batman.Repository.DetailRepository
import com.test.batman.Repository.MainRepository
import com.test.batman.model.VideoDetailModel

class DetailViewModel (private val detailRepository: DetailRepository) : ViewModel(){

    fun getDetailPage(context: Context, id: String): MutableLiveData<VideoDetailModel>{
        return detailRepository.getDetailPage(context, id)
    }
}