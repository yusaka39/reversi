package io.github.yusaka39.reversi.commandline.main.impl

import io.github.yusaka39.reversi.game.Board
import io.github.yusaka39.reversi.game.Grid
import io.github.yusaka39.reversi.game.constants.Sides
import io.github.yusaka39.reversi.game.interfaces.Player
import java.util.*

/**
 * Created by yusaka on 12/10/16.
 */
class RandomPlayer(override val side: Sides, override val name: String) : Player {
    override fun handleTurn(board: Board, validMoves: List<Grid>): Grid =
            validMoves[Random().nextInt(validMoves.size)]
}