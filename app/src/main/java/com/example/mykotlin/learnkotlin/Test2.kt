package com.example.mykotlin.learnkotlin

/**
 * @author ym on 2023/2/22
 *
 */
// 给定一个整数数组nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//样例：
//输入：[-2, 1, -3, 4,-1, 2, 1, -5, 4输出：6
//说明：连续子数组 [4,-1,2,1] 的和最大，为 6。


fun main() {
    val nums = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
    val max = max(nums)
    println(max)
}

fun max(nums: IntArray): Int {
    var max = nums[0]
    var cur = nums[0]
    for (i in 1 until nums.size) {
        if (cur < 0) {
            cur = nums[i]
        } else {
            cur += nums[i]
        }
        max = Math.max(max, cur)
    }
    return max
}