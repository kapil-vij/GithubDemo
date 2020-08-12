package com.demo.search.views

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.base.views.BaseViewModel
import com.demo.search.data.model.ReposResponseEntity
import com.demo.search.data.model.SearchResultData
import com.demo.search.domain.usecase.SearchRepositoriesUseCase
import javax.inject.Inject

class RepoListViewModel @Inject constructor(private var searchRepositoriesUseCase: SearchRepositoriesUseCase) : BaseViewModel() {


    private var lastSearchString: String? = ""
    val searchResultsLiveData = MutableLiveData<List<ReposResponseEntity>>()

    val isSearchResultsLoaded = ObservableField<Boolean>(false)
    val isNoData = ObservableBoolean(false)

    fun searchRepos(userName: String?) {
        isLoading.set(!isSearchResultsLoaded.get()!!)
        isNoData.set(false)
        addDisposable(
            searchRepositoriesUseCase.execute(userName)
                .subscribe(
                    { respos ->
                        isLoading.set(false)
                        updateRepos(respos, userName)
                    },
                    { error ->
                        isSearchResultsLoaded.set(false)
                        isNoData.set(true)
                        isLoading.set(false)
                        showError(error)
                    }
                )
        )
    }

    private fun updateRepos(respos: List<ReposResponseEntity>?, requestString : String?) {
        if (respos == null || respos.isEmpty()) {
            isSearchResultsLoaded.set(false)
            isNoData.set(true)
        } else if(lastSearchString?.equals(requestString)?.not() == true){
            this.lastSearchString = requestString
            isSearchResultsLoaded.set(true)
            searchResultsLiveData.value = respos
        }
    }

    fun clearSearch() {
        isSearchResultsLoaded.set(false)
        isNoData.set(true)
        lastSearchString = ""
    }

}