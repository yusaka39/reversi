package io.github.yusaka39.reversi.commandline.main

import io.github.yusaka39.reversi.game.Board
import io.github.yusaka39.reversi.game.Grid
import io.github.yusaka39.reversi.game.constants.Disks
import io.github.yusaka39.reversi.game.interfaces.Outputs
import io.github.yusaka39.reversi.game.interfaces.Player


const val BANNER = """
      ___           ___           ___           ___           ___           ___
     /\  \         /\  \         /\__\         /\  \         /\  \         /\  \          ___
    /::\  \       /::\  \       /:/  /        /::\  \       /::\  \       /::\  \        /\  \
   /:/\:\  \     /:/\:\  \     /:/  /        /:/\:\  \     /:/\:\  \     /:/\ \  \       \:\  \
  /::\~\:\  \   /::\~\:\  \   /:/__/  ___   /::\~\:\  \   /::\~\:\  \   _\:\~\ \  \      /::\__\
 /:/\:\ \:\__\ /:/\:\ \:\__\  |:|  | /\__\ /:/\:\ \:\__\ /:/\:\ \:\__\ /\ \:\ \ \__\  __/:/\/__/
 \/_|::\/:/  / \:\~\:\ \/__/  |:|  |/:/  / \:\~\:\ \/__/ \/_|::\/:/  / \:\ \:\ \/__/ /\/:/  /
    |:|::/  /   \:\ \:\__\    |:|__/:/  /   \:\ \:\__\      |:|::/  /   \:\ \:\__\   \::/__/
    |:|\/__/     \:\ \/__/     \::::/__/     \:\ \/__/      |:|\/__/     \:\/:/  /    \:\__\
    |:|  |        \:\__\        ~~~~          \:\__\        |:|  |        \::/  /      \/__/
     \|__|         \/__/                       \/__/         \|__|         \/__/
"""

fun main(vararg args: String) {
    println(BANNER)
    val board = Board()
    board.put(Disks.BLACK, Grid(2, 4))
    board.put(Disks.WHITE, Grid(2, 5))
    CommandLineOutputs().outputBoard(board)
}

class CommandLineOutputs : Outputs {

    companion object {
        const val X_INDEX = " a   b   c   d   e   f   g   h"
        const val BOARD_FORMAT = " %s | %s | %s | %s | %s | %s | %s | %s"
        const val DIVIDER = "---+---+---+---+---+---+---+---"
    }

    override fun announceWinner(winner: Player) {
        println("${winner.getName()} win")
    }

    override fun outputBoard(board: Board) {
        println("  $X_INDEX")
        var i = 1
        this.getBoardAsString(board).split("\n").forEachIndexed { idx, s ->
            val yIndex = if (idx % 2 == 0) "${i++} " else "  "
            println("$yIndex$s")
        }
        println()
    }

    private fun getBoardAsString(board: Board) = board.boardAsList.map {
        BOARD_FORMAT.format(
                *(it.map {
                    when (it) {
                        Disks.BLACK -> "@"
                        Disks.WHITE -> "O"
                        else -> " "
                        }
                }).toTypedArray()
        )
    }.joinToString("\n$DIVIDER\n")
}

class PlayerImpl : Player {
    override fun getName(): String = "noripi"
}