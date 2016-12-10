package io.github.yusaka39.reversi.commandline.main

import com.google.common.reflect.ClassPath
import io.github.yusaka39.reversi.commandline.main.impl.factory.CommandLineGameFactory
import io.github.yusaka39.reversi.commandline.main.impl.factory.CommandLineInputPlayerFactory
import io.github.yusaka39.reversi.game.constants.Sides
import io.github.yusaka39.reversi.game.factory.AbstractPlayerFactory
import java.io.File


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
    val playerFactoryClasses = getPlayerFactoryClasses(args)
    println("Choose BLACK player factory")
    val blackFactory = createPlayerFactoryFromInput(playerFactoryClasses)
    println("Choose WHITE player factory")
    val whiteFactory = createPlayerFactoryFromInput(playerFactoryClasses)
    val game = CommandLineGameFactory(blackFactory.create(Sides.BLACK),
                                      whiteFactory.create(Sides.WHITE))
            .create()
    game.start()
}

private fun getPlayerFactoryClasses(args: Array<out String>):
        List<Class<out AbstractPlayerFactory>> {
    return ClassPath.from(ClassLoader.getSystemClassLoader())
            .topLevelClasses
            .map { try { it.load() } catch (e: NoClassDefFoundError) { null } }
            .filter {
                it?.let {
                    it != AbstractPlayerFactory::class.java &&
                            AbstractPlayerFactory::class.java.isAssignableFrom(it)
                } ?: false
            }
            .map { it as Class<out AbstractPlayerFactory> }
}

private fun createPlayerFactoryFromInput(
        availableFactoryClasses: List<Class<out AbstractPlayerFactory>>): AbstractPlayerFactory {
    availableFactoryClasses.forEachIndexed { i, clazz ->
        println("    ${"%2d".format(i)}) ${clazz.name}")
    }
    val idx: Int? = try { readLine()?.trim()?.toInt() } catch (e: NumberFormatException) { null }

    return if (idx != null && 0 <= idx && idx < availableFactoryClasses.size) {
        availableFactoryClasses[idx].newInstance()
    } else {
        createPlayerFactoryFromInput(availableFactoryClasses)
    }
}
