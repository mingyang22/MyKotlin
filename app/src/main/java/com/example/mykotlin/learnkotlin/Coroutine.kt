package com.example.mykotlin.learnkotlin

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * @author ym on 2020/11/4
 * 协程
 */

//fun main() = runBlocking {
//    GlobalScope.launch {
//        delay(1000)
//        println("World!")
//    }
//    println("Hello")
//    delay(2000)
//}

suspend fun main() {

    val job = GlobalScope.launch {
        delay(500) // 非阻塞
        println("World!")
    }
    println("Hello")
//  1.  Thread.sleep(2000) // 阻塞主线程
//  2.  runBlocking {  // 阻塞主线程
//        delay(2000)
//    }
    job.join()

    main2()
    main3()
    println()

    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")
    }

    println("Completed in $time ms")

    main4()
}

fun main4() = runBlocking {
    launch {
        println("main runBlocking    : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) {
        println("Unconfined          : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) {
        println("Default             : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.IO) {
        println("IO                  : I'm working in thread ${Thread.currentThread().name}")
    }


}

fun main2() = runBlocking {
    launch { // 在 runBlocking 作用域中启动一个新协程
        delay(200)
        println("Task from runBlocking")
    }

    coroutineScope {
        launch {
            delay(500)
            println("Task from nested launch")
        }
        delay(200)
        println("Task from coroutine scope")
    }
    println("Coroutine scope is over")
}

fun main3() = runBlocking {
    repeat(100) {
        launch {
            delay(1000L)
            print(".")
        }
    }
}

suspend fun doSomethingUsefulOne(): Int {
    delay(500)
    return 12
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(500)
    return 22
}