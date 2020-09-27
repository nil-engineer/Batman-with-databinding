package com.test.batman.Repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.test.batman.BatmanAPI
import com.test.batman.model.SearchItemModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class MainRepository(private val api: BatmanAPI) {
    var compositeDisposable = CompositeDisposable()
    var data: MutableLiveData<List<SearchItemModel>> = MutableLiveData()

    fun getMainPage(context: Context): MutableLiveData<List<SearchItemModel>> {
        api.getBatmanVideos("3e974fca", "batman")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                data.postValue(it.searchItemList)
            }).let { compositeDisposable.add(it) }
        return data
    }

}