package io.github.yusaka39.reversi.commandline.main.impl.factory

import io.github.yusaka39.reversi.commandline.main.impl.RandomPlayer
import io.github.yusaka39.reversi.game.constants.Sides
import io.github.yusaka39.reversi.game.factory.AbstractPlayerFactory
import io.github.yusaka39.reversi.game.interfaces.Player

/**
 * Created by yusaka on 12/10/16.
 */

class RandomPlayerFactory : AbstractPlayerFactory() {
    override fun create(side: Sides): Player = RandomPlayer(side, "CPU[$side]")
}