package io.github.yusaka39.reversi.commandline.main.impl

import io.github.yusaka39.reversi.game.Board
import io.github.yusaka39.reversi.game.constants.Sides
import io.github.yusaka39.reversi.game.interfaces.Outputs
import io.github.yusaka39.reversi.game.interfaces.Player


class CommandLineOutputs : Outputs {

    companion object {
        const val X_INDEX = " a   b   c   d   e   f   g   h"
        const val BOARD_FORMAT = " %s | %s | %s | %s | %s | %s | %s | %s"
        const val DIVIDER = "---+---+---+---+---+---+---+---"
    }

    override fun announceWinner(winner: Player) {
        println("${winner.name} WIN")
    }

    override fun announceDraw() {
        println("DRAW")
    }

    override fun announcePassing(passingPlayer: Player) {
        println("${passingPlayer.name} passed")
    }

    override fun outputBoard(board: Board) {
        println()
        println("  $X_INDEX")
        var i = 1
        this.getBoardAsString(board).split("\n").forEachIndexed { idx, s ->
            val yIndex = if (idx % 2 == 0) "${i++} " else "  "
            println("$yIndex$s")
        }
        println()
    }

    override fun outputScore(scores: List<Pair<Player, Int>>) {
        scores.forEach {
            val (player, score) = it
            println("%${scores.map { it.first.name.length }.max()}s: %2d"
                            .format(player.name, score))
        }
        println()
    }

    private fun getBoardAsString(board: Board) = board.boardAsList.map {
        BOARD_FORMAT.format(
                *(it.map {
                    when (it) {
                        Sides.BLACK -> "@"
                        Sides.WHITE -> "O"
                        else -> " "
                    }
                }).toTypedArray()
        )
    }.joinToString("\n$DIVIDER\n")
}

