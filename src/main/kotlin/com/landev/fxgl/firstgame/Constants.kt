package com.landev.fxgl.firstgame

import com.sun.javafx.binding.StringFormatter
import javafx.scene.paint.Color

object Constants {
    const val HEIGHT = 600
    const val WIDTH = 800
    const val STEP = 5.0
    const val PLAYER_SIZE = 25.0
    const val COIN_RADIUS = 50.0
    @JvmField val COINT_COLOR = Color.YELLOW
    @JvmField val PLAYER_COLOR = Color.BLUE!!
    const val PIXELS_MOVED_CONSTANT = "pixelsMoved"
//    const val PLAYER_TEXTURE = "brick.png"
    const val PLAYER_TEXTURE = "newdude.png"
    const val DROP_SOUND = "drop.wav"
    const val COIN_TEXTURE = "coin.png"
}

fun divide(ifZero: () -> Unit, ifSuccess: (Int) -> Unit, top: Int, bottom: Int) {
    if (bottom == 0) {
        ifZero()
    } else {
        ifSuccess(top/bottom)
    }
}

val ifZero = { println("bad") }
val ifSuccess = { x: Int -> println(StringFormatter.format("good %d", x)) }

fun divide1(top: Int, bottom: Int) =
    divide(
        ifZero,
        ifSuccess,
        top,
        bottom
    )

val good1 = divide1(6, 3)
val bad1 = divide1(6, 0)