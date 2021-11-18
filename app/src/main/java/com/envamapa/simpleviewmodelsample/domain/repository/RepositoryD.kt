package com.envamapa.simpleviewmodelsample.domain.repository

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import kotlin.random.Random

class RepositoryD(
    private val repositoryC: RepositoryC,
    private val repositoryE: RepositoryE
) {
    fun getDataD(): Single<String> {
        println("      RepositoryD: doing some magic with repositoryA and repositoryE")
        return repositoryC.getDataC()
            .zipWith(
                repositoryE.getDataE(),
                BiFunction<String, List<String>, String> { dataC, dataE ->
                    if (dataE.size < Random.nextInt(5)) {
                        println("      RepositoryD: will return data from Repository E")
                        dataE[Random.nextInt(0, 4)]
                    } else {
                        println("      RepositoryD: will return data from Repository C")
                        dataC
                    }
                }
            )
    }
}
