package com.envamapa.simpleviewmodelsample.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.envamapa.simpleviewmodelsample.domain.Tracking
import com.envamapa.simpleviewmodelsample.domain.usecase.UseCaseA
import com.envamapa.simpleviewmodelsample.domain.usecase.UseCaseB
import com.envamapa.simpleviewmodelsample.domain.usecase.UseCaseC
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ViewModelA(
    private val useCaseA: UseCaseA,
    private val useCaseB: UseCaseB,
    private val useCaseC: UseCaseC,
    private val tracking: Tracking
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _state = MutableLiveData<String>()
    val state: LiveData<String> = _state

    init {
        println("  ViewModelA: init")
        compositeDisposable.add(
            useCaseA.invoke()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe { result, _ ->
                    println("  ViewModelA: Result for use case A -> $result")
                    _state.postValue(result)
                }
        )
    }

    fun getInformation() {
        println("  ViewModelA: Executing getInformation")
        compositeDisposable.add(
            useCaseB.invoke()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe { result, _ ->
                    println("  ViewModelA: Result for use case B -> $result")
                    _state.postValue(result)
                }
        )
    }

    fun submitSomething(
        something: String
    ) {
        println("  ViewModelA: Executing submitSomething")
        compositeDisposable.add(
            useCaseC.invoke(
                something = something
            )
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe { result, _ ->
                    println("  ViewModelA: Result for use case C -> $result")
                    _state.postValue(result)
                }
        )
    }

    fun track() {
        println("  ViewModelA: Executing track")
        tracking.trackAMovement()
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
