package com.landev.fxgl.firstgame.components

import com.almasb.fxgl.entity.component.Component

class MovingComponent(private val step: Double) : Component() {

    fun moveRight() {
        entity.translateX(step)
    }

    fun moveLeft() {
        entity.translateX(-step)
    }

    fun moveUp() {
        entity.translateY(-step)
    }

    fun moveDown() {
        entity.translateY(step)
    }
}
