package com.demo.search.data.repository

import com.base.networking.NetworkClient
import com.demo.search.data.model.ReposResponseEntity
import com.demo.search.data.model.SearchResponseEntity
import com.demo.search.data.network.SearchApi
import io.reactivex.Observable
import javax.inject.Inject

class SearchRepository @Inject constructor(private val networkClient: NetworkClient) {

    fun searchUsername(searchString : String?): Observable<SearchResponseEntity> {
           return networkClient.create(SearchApi::class.java).searchUsername(searchString)
    }

    fun searchUserRepos(username : String?): Observable<List<ReposResponseEntity>> {
        return networkClient.create(SearchApi::class.java).searchUserRepos(username)
    }
}