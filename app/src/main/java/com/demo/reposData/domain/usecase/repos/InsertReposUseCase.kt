package com.demo.reposData.domain.usecase.repos

import com.demo.reposData.domain.repository.ReposRepository
import javax.inject.Inject

class InsertReposUseCase @Inject constructor(
    private val ReposRepository: ReposRepository
) {
    operator fun invoke() = ReposRepository.offlineRepossData()
}