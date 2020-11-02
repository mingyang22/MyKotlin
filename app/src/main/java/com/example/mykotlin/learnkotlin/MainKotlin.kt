package com.example.mykotlin.learnkotlin

import kotlin.math.max

/**
 * @author yang on 2020/5/2
 */
fun main() {
    val a = 22
    val b = 30
    val value = largeNumber2(a, b)
    println("largeNumber is  $value")
    println(getScore("Tom"))
    checkNumber(10L)

    val range = 0 until 10
    val range2 = 10 downTo 0
    for (i in range) {
        print("$i ")
    }
    for (i in range2 step 2) {
        print("$i ")
    }

    val person = Person("Jack", 20)
    person.eat()

    val student = Student()
    val student1 = Student(name = "Tom", age = 22)
    val student2 = Student("a123", 5, "Tony", 18)
    student1.readBooks()
    student1.doHomework()

    val cellphone = Cellphone("HuaWei", 1299.99)
    println(cellphone)

    Singleton.singletonTest()

    val list = mutableListOf("a", "b", "c", "d")
    list.add("e")
    val i = list[1]
    println("i = $i")
    println(list)

    val map = mapOf("a" to 1, "b" to 2, "c" to 3, "d" to 4)
    println(map)

    test(null)

    var wordIds = ""
    if (wordIds.isNotEmpty()) {
        wordIds = wordIds.take(wordIds.length - 1)
    }

}

fun methodName(): Int = 0

fun largeNumber(num1: Int, num2: Int): Int = max(num1, num2)

fun largeNumber2(num1: Int, num2: Int): Int = if (num1 > num2) num1 else num2

fun getScore(name: String) = when (name) {
    "Tom" -> 86
    "Jim" -> 77
    else -> 0
}

fun checkNumber(num: Number) {
    when (num) {
        is Int -> println("number is Int")
        is Long -> println("number is Long")
        is Double -> println("number is Double")
        else -> println("number not support")
    }
}

fun test(str: String?) {
    if (str?.isNotEmpty() == true) {
        println("test")
    }
    if (str.isNullOrEmpty()) {
        println("test isNullOrEmpty")
    }

}