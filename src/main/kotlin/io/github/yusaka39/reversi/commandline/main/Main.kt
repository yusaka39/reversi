package io.github.yusaka39.reversi.commandline.main

import io.github.yusaka39.reversi.game.interfaces.Io
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
    IoImpl().announceWinner(PlayerImpl())
}

class IoImpl : Io {
    override fun announceWinner(winner: Player) {
        println("${winner.getName()} win")
    }
}

class PlayerImpl : Player {
    override fun getName(): String = "noripi"
}