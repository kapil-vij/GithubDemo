package com.demo.search.domain.usecase

import com.base.domain.UseCase
import com.base.domain.UseCaseComposer
import com.demo.search.data.model.ReposResponseEntity
import com.demo.search.data.repository.SearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class SearchRepositoriesUseCase @Inject constructor(private val searchRepository: SearchRepository, composer: UseCaseComposer) : UseCase<String?, List<ReposResponseEntity>>(composer) {

    override fun createUseCaseObservable(username : String?): Observable<List<ReposResponseEntity>> {
        return searchRepository.searchUserRepos(username)
    }

}