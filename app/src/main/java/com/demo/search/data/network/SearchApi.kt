package com.demo.search.data.network

import com.demo.search.data.model.ReposResponseEntity
import com.demo.search.data.model.SearchResponseEntity
import io.reactivex.Observable
import retrofit2.http.*

interface SearchApi {

    @GET("search/users")
    fun searchUsername(@Query("q") username : String?): Observable<SearchResponseEntity>

    @GET("/users/{username}/repos")
    fun searchUserRepos(@Path("username") username : String?, @Query("type") type : String = "all"): Observable<List<ReposResponseEntity>>

}