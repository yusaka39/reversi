package io.github.yusaka39.reversi.game

import io.github.yusaka39.reversi.game.constants.Disks
import io.github.yusaka39.reversi.game.factory.AbstractPlayerFactory
import io.github.yusaka39.reversi.game.interfaces.Outputs
import io.github.yusaka39.reversi.game.interfaces.Player


class Game(val board: Board, val outputs: Outputs, playerFactory: AbstractPlayerFactory) {
    val players: List<Player> =
            listOf(playerFactory.create(Disks.BLACK), playerFactory.create(Disks.WHITE))

    fun start(){
        this.players.forEach { println(it.name) }
    }
}