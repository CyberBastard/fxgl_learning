package com.landev.fxgl.firstgame.components

import com.almasb.fxgl.core.math.Vec2
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.physics.PhysicsComponent

class MovingPhysicalComponent(private val step: Double, private val physicalComponent: PhysicsComponent) : Component() {

    fun moveRight() {
        physicalComponent.setBodyLinearVelocity(Vec2(step, 0.0))
    }

    fun moveLeft() {
        physicalComponent.setBodyLinearVelocity(Vec2(-step, 0.0))
    }

    fun moveUp() {
        physicalComponent.setBodyLinearVelocity(Vec2(0.0, step))
    }

    fun moveDown() {
        physicalComponent.setBodyLinearVelocity(Vec2(0.0, -step))
    }
}