package io.github.yusaka39.reversi.commandline.main.impl

import io.github.yusaka39.reversi.game.constants.Disks
import io.github.yusaka39.reversi.game.interfaces.Player


class CommandLineInputPlayer(override val name: String, override val side: Disks) : Player