@file:JvmName("Config")

package com.landev.fxgl

data class Config(
    var windowHeight: Int = 0,
    var windowWidth: Int = 0,
    var playerStep: Double = 0.0,
    var coinRadius: Double = 0.0,
    var playerTexture: String,
    var coinTexture: String,
    var dropSound: String
)