package io.github.yusaka39.reversi.commandline.main.impl.factory

import io.github.yusaka39.reversi.commandline.main.impl.CommandLineInputPlayer
import io.github.yusaka39.reversi.game.constants.Disks
import io.github.yusaka39.reversi.game.factory.AbstractPlayerFactory
import io.github.yusaka39.reversi.game.interfaces.Player


class CommandLineInputPlayerFactory : AbstractPlayerFactory() {
    override fun create(side: Disks): Player {
        println("Input player name for $side [$side]")
        val playerName = readLine()?.trim().let {
            if (it.isNullOrEmpty()) null else it
        } ?: side.toString()
        return CommandLineInputPlayer(playerName, side)
    }
}