package com.test.batman.Repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.test.batman.BatmanAPI
import com.test.batman.model.VideoDetailModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class DetailRepository(private val api: BatmanAPI) {
    var compositeDisposable = CompositeDisposable()
    var data: MutableLiveData<VideoDetailModel> = MutableLiveData()

    fun getDetailPage(context: Context, id: String): MutableLiveData<VideoDetailModel>{
        api.getVideoDetail("3e974fca", id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                data.postValue(it)
            }).let { compositeDisposable.add(it) }
        return data
    }
}