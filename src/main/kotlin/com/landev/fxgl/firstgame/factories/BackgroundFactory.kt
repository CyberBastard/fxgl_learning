@file:JvmName("BackgroundFactory")

package com.landev.fxgl.firstgame.factories

import com.almasb.fxgl.entity.Entities
import com.almasb.fxgl.entity.EntityFactory
import com.almasb.fxgl.entity.SpawnData
import com.almasb.fxgl.entity.Spawns

class BackgroundFactory : EntityFactory {

    @Spawns("block")
    fun newBlock(data: SpawnData) = Entities.builder()
        .from(data)
}