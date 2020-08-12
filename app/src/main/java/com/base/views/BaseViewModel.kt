package com.base.views

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.pempem.utils.navigation.NavigationLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()
    val navigation = NavigationLiveData()
    val isLoading = ObservableBoolean(false)

    val obserableError = ObservableField<String>()


    protected fun addDisposable(d: Disposable){
        resetErrors()
        disposables.add(d)
    }
    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    protected fun resetErrors() {
        obserableError.set(null)
    }

    protected fun showError(error: Throwable?) {
        error?.printStackTrace()
        obserableError.set(error?.message)
    }

}