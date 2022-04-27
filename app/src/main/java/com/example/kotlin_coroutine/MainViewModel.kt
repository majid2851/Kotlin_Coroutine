package com.example.kotlinmvvm2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvm2.network.RetroInstance
import com.example.kotlinmvvm2.network.RetroService
import com.example.kotlinmvvm2.network.model.BooksListModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Call

class MainViewModel : ViewModel()
{
     var disposable=CompositeDisposable()

    fun getBooksObserver(title: String):MutableLiveData<BooksListModel>
    {
        val booksList: MutableLiveData<BooksListModel> = MutableLiveData()
         apiProvider().getBooks(title).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :
                io.reactivex.Observer<BooksListModel> {
                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onNext(t: BooksListModel) {
                    booksList.postValue(t)
                    Log.i("mag2851",t.toString())
                }

                override fun onError(e: Throwable) {
                    Log.i("mag2851-error",e.toString())
                }

                override fun onComplete() {

                }

            })
        return booksList
    }
    suspend fun getBooksWithCoroutine(title: String): Call<BooksListModel> {
        return apiProvider().getBooksRetrofit(title)
    }

    fun apiProvider(): RetroService {
        return RetroInstance.getRetrofit().create(RetroService::class.java)
    }



}