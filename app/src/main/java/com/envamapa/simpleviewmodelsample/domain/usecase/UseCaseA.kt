package com.envamapa.simpleviewmodelsample.domain.usecase

import com.envamapa.simpleviewmodelsample.domain.repository.ABCTestRepository
import com.envamapa.simpleviewmodelsample.domain.repository.RepositoryA
import com.envamapa.simpleviewmodelsample.domain.repository.RepositoryD
import io.reactivex.rxjava3.core.Single

class UseCaseA(
    private val repositoryA: RepositoryA,
    private val repositoryD: RepositoryD,
    private val abcTestRepository: ABCTestRepository
) {
    fun invoke(): Single<String> {
        println("    UseCaseA: invoke()")
        return if (abcTestRepository.isMyTestEnable(test = "TEST A")) {
            println("    UseCaseA: Test A is enable -> getting data from repositoryA.getDataA()")
            repositoryA.getDataA()
        } else {
            println("    UseCaseA: Test A is disable -> getting data from repositoryD.getDataD()")
            repositoryD.getDataD()
        }
    }
}
