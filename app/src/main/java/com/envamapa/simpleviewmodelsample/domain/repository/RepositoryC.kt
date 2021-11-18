package com.envamapa.simpleviewmodelsample.domain.repository

import io.reactivex.rxjava3.core.Single

class RepositoryC {
    fun getDataC(): Single<String> {
        println("      RepositoryC: just returning some text")
        return Single.just("Data from repo C")
    }
}
