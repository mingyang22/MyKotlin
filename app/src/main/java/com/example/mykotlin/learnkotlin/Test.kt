package com.example.mykotlin.learnkotlin

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mykotlin.utils.DateUtils
import java.lang.StringBuilder

/**
 * @author ym on 2020/11/9
 *
 */
@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    var vipPayProductList = ArrayList<String>()
    for (index in vipPayProductList.withIndex()) {
        if (index.index > 2) break
        if ("1 month" == index.value) {
            println("true")
            break
        }
    }

    // 1860.52
    var test: String = "null"
    var str = with(test) {
        println(length)
        return@with 100
    }
    println(str)

    var text1 = "2"
    text1 = text1.split(".")[0]
    val price = StringBuilder()
    text1.forEachIndexed { index, c ->
        if (index == 0) {
            price.append(c)
        } else {
            price.append("?")
        }
    }
    text1 = "券后价¥${price}"
    println(text1)

    val list = listOf("不可变", "涨", "死哦更黑")
    println(list.toString())
    val list2 = mutableListOf<String>()
    list2.add("1")
    list2.add("2")
    list2.add("3")
    list2.add("4")
    testForEach()
    println(list2.take(2))
    val list3 = list2.reversed()
    println(list3)
    println(list2)
    println(testConversion())
    testIndexOf()
    testSubList()
    println(DateUtils.timeToString(DateUtils.getCurrentMillisecond()))
}

fun testForEach() {
    val list = listOf(1, 3, 4, 5, 6, 8)
    run forEach@{
        list.forEachIndexed { index, i ->
            if (i == 5) return@forEach
            println(i)
        }
    }
    println("forEachIndexed end")

    for (count in list) {
        // continue 会跳过5   return和break会跳出循环
        if (count == 5) return
        println(count)
    }
}

fun testConversion(): Long {
    val str = "01:21:00"
    val list = str.split(":")
    val hour = list.getOrElse(0) { "0" }.toInt()
    val minute = list.getOrElse(1) { "0" }.toInt()
    val second = list.getOrElse(2) { "0" }.toInt()
    return ((hour * 3600 + minute * 60 + second) * 1000).toLong()

}

fun testIndexOf() {
    val map = mutableMapOf<String, String>()
    map["101"] = "main"
    map["103"] = "main3"
    map["102"] = "main2"
    val position = map.values.indexOf("main2")
    println(position)
}

fun testSubList() {
    val list2: MutableList<String>? = null
//    list2.add("1")
//    list2.add("2")
//    list2.add("3")
//    list2.add("4")
    println(list2?.take(6))
}