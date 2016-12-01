package io.github.yusaka39.reversi.game.constants


enum class Disks {
    BLACK, WHITE;
    fun reverse() = when (this) {
        BLACK -> WHITE
        WHITE -> BLACK
    }
}