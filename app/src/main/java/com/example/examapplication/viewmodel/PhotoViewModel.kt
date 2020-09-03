package com.example.examapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.examapplication.Outcome
import com.example.examapplication.model.Photo
import com.example.examapplication.repo.PhotoRepository
import com.example.examapplication.rx.SchedulerProvider
import com.example.examapplication.rx.with
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable

class PhotoViewModel(private var repo: PhotoRepository, private var schedulerProvider: SchedulerProvider): ViewModel(){

    private val _photoListLiveData = MutableLiveData<Outcome<List<Photo>>>()
    val photoListLiveData: MutableLiveData<Outcome<List<Photo>>>
        get() = _photoListLiveData

    fun getListPhoto(){
        if (_photoListLiveData.value != Outcome.Progress<List<Photo>>(
                true
            )
        ){
            repo.getListPhoto().with(schedulerProvider).subscribe(object : MaybeObserver<List<Photo>> {

                override fun onSuccess(t: List<Photo>) {
                    this@PhotoViewModel._photoListLiveData.value =
                        Outcome.Progress(false)
                    this@PhotoViewModel._photoListLiveData.value =
                        Outcome.Success(t)
                }

                override fun onComplete() {
                    this@PhotoViewModel._photoListLiveData.value =
                        Outcome.Progress(false)
                }

                override fun onSubscribe(d: Disposable) {
                    this@PhotoViewModel._photoListLiveData.value =
                        Outcome.Progress(true)
                }

                override fun onError(e: Throwable) {
                    this@PhotoViewModel._photoListLiveData.value =
                        Outcome.Progress(false)
                    this@PhotoViewModel._photoListLiveData.value =
                        Outcome.failure(e)
                }
            })
        }
    }
}