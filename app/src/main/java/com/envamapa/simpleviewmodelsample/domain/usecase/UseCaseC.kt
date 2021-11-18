package com.envamapa.simpleviewmodelsample.domain.usecase

import com.envamapa.simpleviewmodelsample.domain.repository.ABCTestRepository
import com.envamapa.simpleviewmodelsample.domain.repository.RepositoryB
import com.envamapa.simpleviewmodelsample.domain.repository.RepositoryC
import com.envamapa.simpleviewmodelsample.domain.repository.RepositoryE
import io.reactivex.rxjava3.core.Single
import kotlin.random.Random

class UseCaseC(
    private val repositoryB: RepositoryB,
    private val repositoryC: RepositoryC,
    private val repositoryE: RepositoryE,
    private val abcTestRepository: ABCTestRepository
) {
    fun invoke(
        something: String
    ): Single<String> {
        println("    UseCaseC: invoke()")
        return if (abcTestRepository.isMyTestEnable("TEST B") && something.isNotEmpty()) {
            println("    UseCaseC: Test B is enable and $something is not Empty")
            if (abcTestRepository.isMyTestEnable("TEST C")) {
                println("    UseCaseC: Test C is enable -> getting data from repositoryC.getDataC()")
                repositoryC.getDataC()
            } else {
                println("    UseCaseC: Test C is disable -> getting data from repositoryB.getDataB()")
                repositoryB.getDataB()
            }
        } else {
            println("    UseCaseC: Test B is disable or $something is Empty -> getting data from repositoryE.getDataE()")
            repositoryE.getDataE()
                .map {
                    it[Random.nextInt(0, it.size-1)]
                }
        }
    }
}
