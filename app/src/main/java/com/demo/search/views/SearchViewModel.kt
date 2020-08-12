package com.demo.search.views

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.base.views.BaseViewModel
import com.demo.search.data.model.SearchResultData
import com.demo.search.domain.usecase.SearchUserUseCase
import javax.inject.Inject

class SearchViewModel @Inject constructor(private var searchUserUseCase: SearchUserUseCase) : BaseViewModel() {


    private var lastSearchString: String? = ""
    val searchResultsLiveData = MutableLiveData<List<SearchResultData>>()

    val isSearchResultsLoaded = ObservableField<Boolean>(false)
    val isNoData = ObservableBoolean(false)

    fun searchUser(queryString: String?) {
        isLoading.set(!isSearchResultsLoaded.get()!!)
        isNoData.set(false)
        addDisposable(
            searchUserUseCase.execute(queryString)
                .subscribe(
                    { searchResponseEntity ->
                        isLoading.set(false)
                        updateUsers(searchResponseEntity?.items, queryString)
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

    private fun updateUsers(users: List<SearchResultData>?, requestString : String?) {
        if (users == null || users.isEmpty()) {
            isSearchResultsLoaded.set(false)
            isNoData.set(true)
        } else if(lastSearchString?.equals(requestString)?.not() == true){
            this.lastSearchString = requestString
            isSearchResultsLoaded.set(true)
            searchResultsLiveData.value = users
        }
    }

    fun clearSearch() {
        isSearchResultsLoaded.set(false)
        isNoData.set(true)
        lastSearchString = ""
    }

}