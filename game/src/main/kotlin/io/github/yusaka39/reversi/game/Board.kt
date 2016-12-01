package io.github.yusaka39.reversi.game

import io.github.yusaka39.reversi.game.constants.Disks


class Board {
    companion object {
        private val INITIAL_BOARD = listOf(
                listOf(null, null, null, null, null, null, null, null),
                listOf(null, null, null, null, null, null, null, null),
                listOf(null, null, null, null, null, null, null, null),
                listOf(null, null, null, Disks.BLACK, Disks.WHITE, null, null, null),
                listOf(null, null, null, Disks.WHITE, Disks.BLACK, null, null, null),
                listOf(null, null, null, null, null, null, null, null),
                listOf(null, null, null, null, null, null, null, null),
                listOf(null, null, null, null, null, null, null, null)
        )

        private val SEARCH_STRATEGIES: List<(Grid) -> Grid> = listOf(
                { it -> Grid(it.x + 1, it.y + 1) },
                { it -> Grid(it.x + 1, it.y) },
                { it -> Grid(it.x + 1, it.y - 1) },
                { it -> Grid(it.x, it.y + 1) },
                { it -> Grid(it.x, it.y - 1) },
                { it -> Grid(it.x - 1, it.y - 1) },
                { it -> Grid(it.x - 1, it.y) },
                { it -> Grid(it.x - 1, it.y + 1) }
        )
    }

    var boardAsList: List<List<Disks?>> = INITIAL_BOARD
        private set

    fun put(disk: Disks, grid: Grid) {
        tailrec fun getRevesingGridForStrategy(getNext: (Grid) -> Grid, g: Grid, acc: List<Grid>)
                : List<Grid> {
            if (!g.isValid()) {
                return emptyList()
            }
            val d = this.boardAsList[g.y][g.x]
            return when(d) {
                disk -> acc
                null -> emptyList()
                else -> getRevesingGridForStrategy(getNext, getNext(g), acc + listOf(g))
            }
        }

        val reverseTargetGrids = SEARCH_STRATEGIES.map {
            getRevesingGridForStrategy(it, it(grid), emptyList())
        }.flatten() + listOf(grid)

        this.boardAsList = this.boardAsList.mapIndexed { y, list ->
            list.mapIndexed { x, d ->
                if (reverseTargetGrids.contains(Grid(x, y))) {
                    disk
                } else {
                    d
                }
            }
        }
    }
}

data class Grid(val x: Int, val y: Int) {
    fun isValid() = 0 <= this.x && this.x <= 7 && 0 <= this.y && this.y <= 7
}