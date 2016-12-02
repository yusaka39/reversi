package io.github.yusaka39.reversi.commandline.main.impl

import io.github.yusaka39.reversi.game.Board
import io.github.yusaka39.reversi.game.Grid
import io.github.yusaka39.reversi.game.constants.Sides
import io.github.yusaka39.reversi.game.interfaces.Player


class CommandLineInputPlayer(override val name: String, override val side: Sides) : Player {
    override tailrec fun handleTurn(board: Board, validMoves: List<Grid>): Grid {
        println("${this.name}'s turn")
        println("Valid moves")
        validMoves.forEachIndexed { i, grid ->
            println("    ${"%2d".format(i)}) ${this.convertGridToString(grid)}")
        }
        val input = readLine()?.let {
            ".*?([0-9]*).*?".toRegex().matchEntire(it)?.groupValues?.get(1)?.toInt()
        } ?: return this.handleTurn(board, validMoves)
        return validMoves.getOrNull(input) ?: this.handleTurn(board, validMoves)
    }

    fun convertGridToString(grid: Grid) = "${('a'.toInt() + grid.x).toChar()}${grid.y + 1}"
}