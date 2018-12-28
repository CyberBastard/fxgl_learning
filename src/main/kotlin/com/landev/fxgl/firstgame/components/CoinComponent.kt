@file:JvmName("CoinControl")

package com.landev.fxgl.firstgame.components

import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import com.landev.fxgl.firstgame.Constants.COIN_TEXTURE
import javafx.util.Duration

class CoinComponent : Component() {

    private val defaultChannel = AnimationChannel(COIN_TEXTURE, 10, 100, 100, Duration.seconds(1.0), 0, 9)
    private val texture = AnimatedTexture(defaultChannel)

    override fun onAdded() {
        entity.setView(texture)
        texture.loopAnimationChannel(defaultChannel)
    }
}