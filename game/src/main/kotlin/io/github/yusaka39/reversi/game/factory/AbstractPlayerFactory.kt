package io.github.yusaka39.reversi.game.factory

import io.github.yusaka39.reversi.game.constants.Disks
import io.github.yusaka39.reversi.game.interfaces.Player


abstract class AbstractPlayerFactory {
    abstract fun create(side: Disks): Player
}