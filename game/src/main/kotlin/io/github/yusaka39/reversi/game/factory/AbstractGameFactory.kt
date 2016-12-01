package io.github.yusaka39.reversi.game.factory

import io.github.yusaka39.reversi.game.Game


abstract class AbstractGameFactory {
    abstract fun create(): Game
}