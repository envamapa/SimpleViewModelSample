package com.envamapa.simpleviewmodelsample.domain.repository

import io.reactivex.rxjava3.core.Single

class RepositoryA {
    fun getDataA(): Single<String> {
        println("      RepositoryA: just returning some text")
        return Single.just("Seems you got data from repository A")
    }
}
