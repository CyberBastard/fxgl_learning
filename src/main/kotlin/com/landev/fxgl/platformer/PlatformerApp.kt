package com.landev.fxgl.platformer

import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.settings.GameSettings
import javafx.application.Application

class PlatformerApp : GameApplication() {

    override fun initSettings(settings: GameSettings?) {
        with(settings!!) {
            width = 14 * 128
            height = 7 * 128
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(PlatformerApp::class.java)
}