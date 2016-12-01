package io.github.yusaka39.reversi.game

import io.github.yusaka39.reversi.game.constants.Disks
import io.github.yusaka39.reversi.game.factory.AbstractPlayerFactory
import io.github.yusaka39.reversi.game.interfaces.Outputs
import io.github.yusaka39.reversi.game.interfaces.Player


class Game(val board: Board, val outputs: Outputs, playerFactory: AbstractPlayerFactory) {
    val players: Map<Disks, Player> = mapOf(
            Disks.BLACK to playerFactory.create(Disks.BLACK),
            Disks.WHITE to playerFactory.create(Disks.WHITE)
    )

    var turnHolder = this.players[Disks.BLACK]!!

    fun start(){
        while (true) {
            val next = this.getNextTurnHolder()
            if (next == null) {
                break
            }
            this.turnHolder = next
        }
        this.outputs.announceWinner(players.values.maxBy { this.board.getCountForSide(it.side) }!!)
    }

    private fun getNextTurnHolder(): Player? {
        var next = this.turnHolder.side
        return this.players[next]
    }
}