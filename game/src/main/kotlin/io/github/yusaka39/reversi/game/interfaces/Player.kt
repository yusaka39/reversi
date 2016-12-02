package io.github.yusaka39.reversi.game.interfaces

import io.github.yusaka39.reversi.game.Board
import io.github.yusaka39.reversi.game.Grid
import io.github.yusaka39.reversi.game.constants.Sides


interface Player {
    val name: String
    val side: Sides
    fun handleTurn(board: Board, validMoves: List<Grid>): Grid
}