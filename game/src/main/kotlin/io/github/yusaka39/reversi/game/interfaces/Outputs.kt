package io.github.yusaka39.reversi.game.interfaces

import io.github.yusaka39.reversi.game.Board


interface Outputs {
    fun announceWinner(winner: Player)
    fun outputBoard(board: Board)
}