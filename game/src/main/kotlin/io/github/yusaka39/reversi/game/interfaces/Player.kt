package io.github.yusaka39.reversi.game.interfaces

import io.github.yusaka39.reversi.game.constants.Disks


interface Player {
    val name: String
    val side: Disks
}