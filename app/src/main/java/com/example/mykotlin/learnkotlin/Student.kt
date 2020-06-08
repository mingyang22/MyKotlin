package com.example.mykotlin.learnkotlin

/**
 * @author yang on 2020/5/4
 */
class Student(var sno: String = "", var grade: Int = 0, name: String = "", age: Int = 0) :
    Person(name, age), Study {
//class Student : Person {
//    constructor(name: String, age: Int) : super(name, age)

//    constructor() : this("", 0)
//    constructor(name: String, age: Int) : this("", 0, name, age)

    init {
        if (name.isNotEmpty() && age > 0 && sno.isNotEmpty() && grade > 0)
            println("$name is $age years old, sno is $sno, grade is $grade")
    }

    override fun readBooks() {
        println("$name is reading")
    }

    override fun doHomework() {
        println("$name id doing homework")
    }


}