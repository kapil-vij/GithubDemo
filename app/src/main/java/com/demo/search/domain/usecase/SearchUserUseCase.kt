package com.demo.search.domain.usecase

import com.base.domain.UseCase
import com.base.domain.UseCaseComposer
import com.demo.search.data.model.SearchResponseEntity
import com.demo.search.data.repository.SearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class SearchUserUseCase @Inject constructor(private val searchRepository: SearchRepository, composer: UseCaseComposer) : UseCase<String?, SearchResponseEntity>(composer) {

    override fun createUseCaseObservable(searchString : String?): Observable<SearchResponseEntity> {
        return searchRepository.searchUsername(searchString)
    }

}