package io.github.yusaka39.reversi.commandline.main.impl.factory

import io.github.yusaka39.reversi.commandline.main.impl.CommandLineOutputs
import io.github.yusaka39.reversi.game.Board
import io.github.yusaka39.reversi.game.Game
import io.github.yusaka39.reversi.game.factory.AbstractGameFactory
import io.github.yusaka39.reversi.game.factory.AbstractPlayerFactory


class CommandLineGameFactory(val playerFactoryClass: Class<out AbstractPlayerFactory>)
    : AbstractGameFactory() {
    override fun create(): Game = Game(Board(), CommandLineOutputs(),
                                       this.playerFactoryClass.newInstance())
}