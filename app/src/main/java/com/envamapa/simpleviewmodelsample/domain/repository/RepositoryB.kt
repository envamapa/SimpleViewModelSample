package com.envamapa.simpleviewmodelsample.domain.repository

import io.reactivex.rxjava3.core.Single

class RepositoryB {
    fun getDataB(): Single<String> {
        println("      RepositoryB: just returning some text")
        return Single.just("We're getting data from repository B")
    }
}
