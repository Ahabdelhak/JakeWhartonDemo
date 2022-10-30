package com.demo.reposData.domain.usecase.repos

import com.demo.reposData.domain.repository.ReposRepository
import javax.inject.Inject

class GetReposDataUseCase @Inject constructor(
    private val ReposRepository: ReposRepository
) {
    operator fun invoke(page : Int) = ReposRepository.remoteRepossData(page)
}