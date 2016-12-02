package io.github.yusaka39.reversi.game

import io.github.yusaka39.reversi.game.constants.Sides


class Board : Cloneable {
    companion object {
        private val INITIAL_BOARD = listOf(
                listOf(null, null, null, null, null, null, null, null),
                listOf(null, null, null, null, null, null, null, null),
                listOf(null, null, null, null, null, null, null, null),
                listOf(null, null, null, Sides.BLACK, Sides.WHITE, null, null, null),
                listOf(null, null, null, Sides.WHITE, Sides.BLACK, null, null, null),
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

    var boardAsList: List<List<Sides?>> = INITIAL_BOARD
        private set

    fun getSideOfGrid(grid: Grid) = if(grid.isValid()) this.boardAsList[grid.y][grid.x] else null

    private fun hasReverseTargetForStrategy(f: (Grid) -> Grid, grid: Grid, side: Sides): Boolean {
        val opposite = side.reverse()
        var pointed = f(grid)

        if (this.getSideOfGrid(pointed) != opposite) {
            return false
        }

        while (this.getSideOfGrid(pointed) == opposite) {
            pointed = f(pointed)
        }
        return this.getSideOfGrid(pointed) == side
    }

    fun canPut(side: Sides): Boolean {
        for (y in 0 until 8) {
            for (x in 0 until 8) {
                val g = Grid(x, y)
                val sideOfGrid = this.getSideOfGrid(g)
                if (sideOfGrid != null) {
                    continue
                }
                SEARCH_STRATEGIES.forEach {
                    if (hasReverseTargetForStrategy(it, g, side)) {
                        return true
                    }
                }
            }
        }
        return false
    }

    fun put(side: Sides, grid: Grid) {
        tailrec fun getReversingGridForStrategy(getNext: (Grid) -> Grid, g: Grid, acc: List<Grid>)
                : List<Grid> {
            if (!g.isValid()) {
                return emptyList()
            }
            val sideOfGrid = this.boardAsList[g.y][g.x]
            return when(sideOfGrid) {
                side -> acc
                null -> emptyList()
                else -> getReversingGridForStrategy(getNext, getNext(g), acc + listOf(g))
            }
        }

        val reverseTargetGrids = SEARCH_STRATEGIES.map {
            getReversingGridForStrategy(it, it(grid), emptyList())
        }.flatten() + listOf(grid)

        this.boardAsList = this.boardAsList.mapIndexed { y, list ->
            list.mapIndexed { x, sideOfGrid ->
                if (reverseTargetGrids.contains(Grid(x, y))) {
                    side
                } else {
                    sideOfGrid
                }
            }
        }
    }

    fun getCountForSide(side: Sides) =
            this.boardAsList.map { it.count { it == side } }.sum()

    fun getValidMoves(side: Sides): List<Grid> {
        val grids = (0 until 8).map { y -> (0 until 8).map { x -> Grid(x, y) } }.flatten()
        return grids.filter { g ->
            this.getSideOfGrid(g) == null &&
                    SEARCH_STRATEGIES.any { this.hasReverseTargetForStrategy(it, g, side) }
        }
    }

    override public fun clone(): Board {
        return Board().apply { this.boardAsList = this@Board.boardAsList }
    }
}

data class Grid(val x: Int, val y: Int) {
    fun isValid() = 0 <= this.x && this.x <= 7 && 0 <= this.y && this.y <= 7
}