package ru.sb066coder.coroutineflow.lesson9

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

val coroutineScope = CoroutineScope(Dispatchers.IO)

suspend fun main() {

    val flow = MutableSharedFlow<Int>()

    coroutineScope.launch {
        repeat(10) {
            println("Emitted $it")
            flow.emit(it)
            delay(1_000)
        }
    }

    val job1 = coroutineScope.launch {
        flow.first().let {
            println("From job1 $it")
        }
    }
    delay(5_000)
    val job2 = coroutineScope.launch {
        flow.collect {
            println("From job2 $it")
        }
    }
    job1.join()
    job2.join()
}

fun getFlow(): Flow<Int> = flow {
    repeat(10) {
        println("Emitted $it")
        emit(it)
        delay(1_000)
    }
}