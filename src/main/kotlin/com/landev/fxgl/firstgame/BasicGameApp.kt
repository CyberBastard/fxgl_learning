package com.landev.fxgl.firstgame

import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.entity.Entities
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.components.CollidableComponent
import com.almasb.fxgl.input.UserAction
import com.almasb.fxgl.physics.CollisionHandler
import com.almasb.fxgl.physics.PhysicsComponent
import com.almasb.fxgl.physics.box2d.dynamics.BodyType
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef
import com.almasb.fxgl.settings.GameSettings
import com.landev.fxgl.firstgame.Constants.COIN_RADIUS
import com.landev.fxgl.firstgame.Constants.DROP_SOUND
import com.landev.fxgl.firstgame.Constants.HEIGHT
import com.landev.fxgl.firstgame.Constants.PIXELS_MOVED_CONSTANT
import com.landev.fxgl.firstgame.Constants.PLAYER_TEXTURE
import com.landev.fxgl.firstgame.Constants.STEP
import com.landev.fxgl.firstgame.Constants.WIDTH
import com.landev.fxgl.firstgame.components.CoinComponent
import com.landev.fxgl.firstgame.components.DudeComponent
import com.landev.fxgl.firstgame.components.MovingPhysicalComponent
import javafx.application.Application
import javafx.scene.input.KeyCode
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.scene.text.Text

class BasicGameApp : GameApplication() {

    private lateinit var player: Entity

    override fun initSettings(settings: GameSettings) {
        with(settings) {
            width = WIDTH
            height = HEIGHT
        }
    }

    override fun initGame() {
        val playersPhysics = getPhysicsComponent()
        player = Entities.builder()
            .type(EntityType.PLAYER)
            .at(HEIGHT / 2.0, WIDTH / 2.0)
            .viewFromNodeWithBBox(Rectangle(32.0, 42.0))
            .with(
                CollidableComponent(true),
                DudeComponent(),
                MovingPhysicalComponent(2.0, playersPhysics),
                playersPhysics
            )
            .buildAndAttach(gameWorld)
        Entities.builder()
            .type(EntityType.COIN)
            .at(500.0, 200.0)
            .viewFromNodeWithBBox(Circle(COIN_RADIUS))
            .with(CollidableComponent(true))
            .with(CoinComponent())
            .buildAndAttach(gameWorld)

    }

    fun getPhysicsComponent(): PhysicsComponent {
        val physicsComponent = PhysicsComponent()
        with(physicsComponent) {
            setBodyType(BodyType.DYNAMIC)
            setFixtureDef(FixtureDef().also {
                it.density = 1.0f
                it.restitution = 0.3f
            })
        }
        return physicsComponent
    }

    override fun initInput() {
        with(input) {
            addAction(getUserAction("Move Left") {
                player.getComponent(MovingPhysicalComponent::class.java).moveLeft()
                player.getComponent(DudeComponent::class.java).moveLeft()
                gameState.increment(PIXELS_MOVED_CONSTANT, STEP)
            }, KeyCode.A)
            addAction(getUserAction("Move Right") {
                player.getComponent(MovingPhysicalComponent::class.java).moveRight()
                player.getComponent(DudeComponent::class.java).moveRight()
                gameState.increment(PIXELS_MOVED_CONSTANT, STEP)
            }, KeyCode.D)
            addAction(getUserAction("Move Up") {
                player.getComponent(MovingPhysicalComponent::class.java).moveUp()
                gameState.increment(PIXELS_MOVED_CONSTANT, STEP)
            }, KeyCode.W)
            addAction(getUserAction("Move Down") {
                player.getComponent(MovingPhysicalComponent::class.java).moveDown()
                gameState.increment(PIXELS_MOVED_CONSTANT, STEP)
            }, KeyCode.S)
            addAction(getUserAction("Play Sound") {
                audioPlayer.playSound(DROP_SOUND)
            }, KeyCode.F)
            addAction(getUserOnEndAction("Jump") {
                player.getComponent(PhysicsComponent::class.java).setLinearVelocity(0.0, -100.0)
            }, KeyCode.SPACE)
        }
    }

    private fun getUserAction(name: String, action: () -> Unit): UserAction {
        return object : UserAction(name) {
            override fun onAction() {
                action()
            }
        }
    }

    private fun getUserOnEndAction(name: String, action: () -> Unit): UserAction {
        return object : UserAction(name) {
            override fun onActionEnd() {
                action()
            }
        }
    }

    override fun initUI() {
        val text = Text()
        with(text) {
            translateX = 50.0
            translateY = 100.0
            textProperty().bind(gameState.doubleProperty(PIXELS_MOVED_CONSTANT).asString())
        }
        val texture = assetLoader.loadTexture(PLAYER_TEXTURE)
        with(texture) {
            translateX = 50.0
            translateY = 450.0
        }
        gameScene.addUINodes(text, texture)
    }

    override fun initGameVars(vars: MutableMap<String, Any>) {
        vars[PIXELS_MOVED_CONSTANT] = 0.0
    }

    override fun initPhysics() {
        physicsWorld.addCollisionHandler(object : CollisionHandler(
            EntityType.PLAYER,
            EntityType.COIN
        ) {
            override fun onCollisionBegin(player: Entity, coin: Entity) {
                coin.removeFromWorld()
            }
        })
    }
}

fun main(args: Array<String>) {
    Application.launch(BasicGameApp::class.java)
}