package com.example.mykotlin.learnkotlin

/**
 * @author yang on 2020/5/4
 */
open class Person(var name: String, var age: Int) {
    constructor() : this("", 0)

    companion object {
        const val index = 0
    }

    protected  var page = index

    fun eat() {
        println("\n$name is eating. He is $age years old.")
    }

}