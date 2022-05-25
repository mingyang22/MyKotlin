package com.example.mykotlin.learnkotlin

import java.util.*

/**
 * @author ym on 2020/10/15
 * 列表函数操作符
 */
fun main() {

    val fruitList = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val numList = listOf(1, 3, 5, -1, 4, 7, 9, 2)

    // 总数操作符
    println("max length fruit is ${fruitList.maxByOrNull { it.length }}")
    println("anyResult is ${fruitList.any { it.length >= 5 }}")
    println("allResult is ${fruitList.all { it.length >= 5 }}")
    println("totalCount is ${fruitList.count { it.length >= 6 }}")
    fruitList.forEach { print("$it ") }
    println()

    // 在一个初始值的基础上从第一项到最后一项通过一个函数累计所有的元素
    println(numList.fold(2) { total, next -> total + next })
    // 通过一个函数从第一项到最后一项进行累计
    println(numList.reduce { total, next -> total + next })
    println("numList.min = ${numList.minOrNull()}")
    println("numList.max = ${numList.maxOrNull()}")
    // 如果没有任何元素与给定的函数匹配，则返回true
    println("numList.none = ${numList.none { it > 10 }}")
    // 返回所有每一项通过函数转换之后的数据的总和
    println("numList.sumBy = ${numList.sumBy { it + 1 }}")

    // // // 过滤操作符
    // 返回包含去掉前n个元素的所有元素的列表
    println("numList.drop(2) = ${numList.drop(2)}")
    // 返回从第一个开始不符合给定函数的元素起之后的列表
    println("numList.dropWhile { it < 4 } = ${numList.dropWhile { it < 4 }}")
    // 从最后一项开始，返回从开始不符合给定函数的元素起之后的列表
    println("numList.dropLastWhile { it > 4 } = ${numList.dropLastWhile { it > 4 }}")

    println("numList.filter = ${numList.filter { it <= 5 }}")
    println("numList.filterNot = ${numList.filterNot { it <= 5 }}")
    // 过滤一个list中指定index的元素
    println("numList.slice = ${numList.slice(listOf(0, 3))}")
    // 返回从第一个开始的n个元素
    println("numList.take(2) = ${numList.take(2)}")
    println("numList.takeLast(2 = ${numList.takeLast(2)}")
    // 返回从第一个开始符合给定函数条件的元素
    println("numList.takeWhile = ${numList.takeWhile { it > 0 }}")
    println(numList.takeWhile { it > 2 })

    // // // 映射操作符
    // 遍历所有的元素，为每一个创建一个集合，最后把所有的集合放在一个集合中
    println("numList.flatMap = ${numList.flatMap { listOf(it, it + 1) }}")
    // 返回一个每一个元素根据给定的函数转换所组成的List
    println("fruitList.map = ${fruitList.map { it.toUpperCase(Locale.ROOT) }}")
    // 返回一个每一个元素根据给定的包含元素index的函数转换所组成的List
    println("numList.mapIndexed = ${numList.mapIndexed { index, value -> index * value }}")

    // // // 元素操作符
    println("numList.contains(2) is ${numList.contains(2)}")
    println("numList.elementAt(2) is ${numList.elementAt(2)}")
    // 返回给定index对应的元素，如果index数组越界则会根据给定函数返回默认值
    println("numList.elementAtOrElse(2) is ${numList.elementAtOrElse(2) { it * 2 }}")
    println("numList.elementAtOrElse(12) is ${numList.elementAtOrElse(12) { it * 2 }}")
    // 返回给定index对应的元素，如果index数组越界则会返回null
    println("numList.elementAtOrNull(2) is ${numList.elementAtOrNull(2)}")
    println("numList.elementAtOrNull(12) is ${numList.elementAtOrNull(12)}")
    // 返回符合给定函数条件的第一个元素
    println("numList.first { it % 3 == 0 } is ${numList.first { it % 3 == 0 }}")
    println("numList.firstOrNull { it % 6 == 0 } is ${numList.firstOrNull { it % 6 == 0 }}")

    println("numList.indexOf(5) is ${numList.indexOf(5)}")
    // 返回第一个符合给定函数条件的元素的index，如果没有符合则返回 -1
    println("numList.indexOfFirst { it % 8 == 0 } is ${numList.indexOfFirst { it % 8 == 0 }}")
    // 返回符合给定函数的单个元素，如果没有符合或者超过一个，则抛出异常
    println("numList.single { it % 7 == 0 } is ${numList.single { it % 7 == 0 }}")
    // 返回符合给定函数的单个元素，如果没有符合或者超过一个，则返回null
    println("numList.singleOrNull { it % 8 == 0 } is ${numList.singleOrNull { it % 8 == 0 }}")

    // // // 生产操作符
    val (list1, list2) = numList.partition { it % 2 == 0 }
    println("$list1 $list2")
    val list3 = listOf(1, 2, 4, 6, 8, 10)
    println(numList.plus(list3))

    // // // 顺序操作符
    println("numList.reversed() = ${numList.reversed()}")
    // 返回一个自然排序后的list
    println("numList.sorted() = ${numList.sorted()}")
    println("numList.sortedDescending() = ${numList.sortedDescending()}")
    // 返回一个根据指定函数排序后的list
    println("fruitList.sortedBy { it.length } = ${fruitList.sortedBy { it.length }}")
    println("fruitList.sortedByDescending { it.length } = ${fruitList.sortedByDescending { it.length }}")


}