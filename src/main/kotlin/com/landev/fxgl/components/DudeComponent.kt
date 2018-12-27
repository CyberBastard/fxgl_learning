@file:JvmName("DudeControl")

package com.landev.fxgl.components

import com.almasb.fxgl.core.math.FXGLMath
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import com.landev.fxgl.Constants.PLAYER_TEXTURE
import javafx.util.Duration

class DudeComponent : Component() {

    private var speed = 0
    private val animIdle: AnimationChannel = AnimationChannel(PLAYER_TEXTURE, 4, 32, 42, Duration.seconds(1.0), 1, 1)
    private val animWalk: AnimationChannel = AnimationChannel(PLAYER_TEXTURE, 4, 32, 42, Duration.seconds(1.0), 0, 3)
    private val texture: AnimatedTexture = AnimatedTexture(animIdle)

    override fun onAdded() {
        entity.setView(texture)
    }

    override fun onUpdate(tpf: Double) {
        entity.translateX(speed * tpf)

        if (speed != 0) {
            if (texture.animationChannel == animIdle) {
                texture.loopAnimationChannel(animWalk)
            }
            speed = (speed * .9).toInt()
            if (FXGLMath.abs(speed.toDouble()) < 1) {
                speed = 0
                texture.loopAnimationChannel(animIdle)
            }
        }
    }

    fun moveRight() {
        speed = 5
        entity.setScaleX(1.0)
    }

    fun moveLeft() {
        speed = -5
        entity.setScaleX(-1.0)
    }
}
