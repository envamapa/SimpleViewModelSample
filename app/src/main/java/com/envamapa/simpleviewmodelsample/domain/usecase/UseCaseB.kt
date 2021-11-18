package com.envamapa.simpleviewmodelsample.domain.usecase

import com.envamapa.simpleviewmodelsample.domain.repository.RepositoryB
import io.reactivex.rxjava3.core.Single

class UseCaseB(
    private val repositoryB: RepositoryB
) {
    fun invoke(): Single<String> {
        println("    UseCaseB: Just getting data from repository B (no logic here)")
        return repositoryB.getDataB()
    }
}
