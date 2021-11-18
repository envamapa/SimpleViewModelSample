package com.envamapa.simpleviewmodelsample.domain.repository

import kotlin.random.Random

class ABCTestRepository {
    fun isMyTestEnable(test: String): Boolean {
        val isMyTestEnable = Random.nextBoolean()
        println("        ABCTestRepository: isMyTestEnable(test = $test) -> $isMyTestEnable")
        return isMyTestEnable
    }
}
