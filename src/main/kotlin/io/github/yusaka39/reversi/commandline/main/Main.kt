package io.github.yusaka39.reversi.commandline.main

import io.github.yusaka39.reversi.commandline.main.impl.CommandLineOutputs
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
    board.put(Disks.BLACK, Grid(3, 5))
    CommandLineOutputs().outputBoard(board)
}