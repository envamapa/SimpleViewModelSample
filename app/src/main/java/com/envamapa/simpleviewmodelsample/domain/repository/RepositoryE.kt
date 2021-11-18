package com.envamapa.simpleviewmodelsample.domain.repository

import io.reactivex.rxjava3.core.Single
import kotlin.random.Random

class RepositoryE {
    fun getDataE(): Single<List<String>> {
        println("      RepositoryE: just returning a random list")
        return Single.just(
            getRandomList()
        )
    }

    private fun getRandomList(): List<String> {
        val list = mutableListOf<String>()
        for (i in 0..Random.nextInt(10)) {
            list.add("Element number $i from repository E")
        }
        return list
    }
}
