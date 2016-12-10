package io.github.yusaka39.reversi.commandline.main.impl.factory

import io.github.yusaka39.reversi.commandline.main.impl.CommandLineOutputs
import io.github.yusaka39.reversi.game.Board
import io.github.yusaka39.reversi.game.Game
import io.github.yusaka39.reversi.game.factory.AbstractGameFactory
import io.github.yusaka39.reversi.game.factory.AbstractPlayerFactory
import io.github.yusaka39.reversi.game.interfaces.Player


class CommandLineGameFactory(val bPlayer: Player, val wPlayer: Player)
    : AbstractGameFactory() {
    override fun create(): Game = Game(Board(), CommandLineOutputs(),
                                       this.bPlayer, this.wPlayer)
}