package io.github.yusaka39.reversi.game

import io.github.yusaka39.reversi.game.constants.Sides
import io.github.yusaka39.reversi.game.factory.AbstractPlayerFactory
import io.github.yusaka39.reversi.game.interfaces.Outputs
import io.github.yusaka39.reversi.game.interfaces.Player


class Game(val board: Board, val outputs: Outputs, playerFactory: AbstractPlayerFactory) {
    val players: Map<Sides, Player> = mapOf(
            Sides.BLACK to playerFactory.create(Sides.BLACK),
            Sides.WHITE to playerFactory.create(Sides.WHITE)
    )

    var turnHolder = this.players[Sides.BLACK]!!

    fun start(){
        while (true) {
            this.outputs.outputBoard(this.board)
            this.outputs.outputScore(
                    this.players.map { it.value to this.board.getCountForSide(it.key) })

            val validMove = this.board.getValidMoves(this.turnHolder.side)
            var move: Grid
            do {
                move = this.turnHolder.handleTurn(this.board, validMove)
            } while (!move.isValid() || !validMove.contains(move))
            this.board.put(this.turnHolder.side, move)

            val next = this.getNextTurnHolder() ?: break
            if (next == this.turnHolder) {
                this.outputs.announcePassing(this.players.get(this.turnHolder.side.reverse())!!)
            }
            this.turnHolder = next
        }

        this.outputs.outputBoard(this.board)
        val scores = this.players.map { it.value to this.board.getCountForSide(it.key) }
        val isDraw = scores.map { it.second }.distinct().size == 1

        if (isDraw) {
            this.outputs.announceDraw()
        } else {
            this.outputs.announceWinner(scores.maxBy { it.second }?.first!!)
        }
    }

    private fun getNextTurnHolder(): Player? {
        val opposite = this.turnHolder.side.reverse()
        return when {
            this.board.canPut(opposite) -> this.players[opposite]
            this.board.canPut(this.turnHolder.side) -> this.turnHolder
            else -> null
        }
    }
}