package com.example.mykotlin.learnkotlin

/**
 * @author ym on 2020/11/9
 *
 */
fun main() {
    var vipPayProductList = ArrayList<String>()
    for (index in vipPayProductList.withIndex()) {
        if (index.index > 2) break
        if ("1 month" == index.value) {
            println("true")
            break
        }
    }


}