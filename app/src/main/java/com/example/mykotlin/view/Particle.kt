package com.example.mykotlin.view

/**
 * @author ym on 3/1/21
 * 粒子
 */
class Particle(
    var x: Float, // X坐标
    var y: Float, // Y坐标
    var radius: Float, // 半径
    var speed: Float, // 速度
    var alpha: Int, // 透明度
    var maxOffset: Float = 300f, // 最大移动距离
    var offset: Int, // 当前移动距离
    var angle: Double // 粒子角度
)