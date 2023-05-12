package ru.sb066coder.coroutineflow.lesson2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

suspend fun main() {
    getFlowByBuilderFlow().filter { it.isPrime() }
        .filter { it > 20 }
        .map {
            println("Map")
            "Number: $it"
        }
        .collect { println(it) }

}

fun getFlowByFlowOfBuilder(): Flow<Int> {
    return flowOf(3, 4, 8, 16, 5, 7, 11, 32, 41, 28, 43, 47, 84, 116, 53, 59, 61)
}

fun getFlowByBuilderFlow(): Flow<Int> {
    val numbers = listOf(3, 4, 8, 16, 5, 7, 11, 32, 41, 28, 43, 47, 84, 116, 53, 59, 61)
    return flow {
        val a = 103
        println("Before emit $a")
        emit(a)
        println("Emitted $a")
        delay(1000)
        val b = a * 2
        emit(b)
        println("Emitted $b")
        for (i in numbers) {
            emit(i)
        }
    }
}

suspend fun Int.isPrime(): Boolean {
    if (this <= 1) return false
    for (i in 2..this / 2) {
        delay(50)
        if (this % i == 0) return false
    }
    return true
}