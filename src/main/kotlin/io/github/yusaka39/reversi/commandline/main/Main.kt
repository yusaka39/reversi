package io.github.yusaka39.reversi.commandline.main

import com.google.common.reflect.ClassPath
import io.github.yusaka39.reversi.commandline.main.impl.factory.CommandLineGameFactory
import io.github.yusaka39.reversi.commandline.main.impl.factory.CommandLineInputPlayerFactory
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
    val game = CommandLineGameFactory(getPlayerFactoryClass(args)).create()
    game.start()
}

private fun getPlayerFactoryClass(args: Array<out String>): Class<out AbstractPlayerFactory> {
    //val files = File(args[])
    //val classLoaders = listOf(ClassLoader.getSystemClassLoader()) +

    ClassPath.from(ClassLoader.getSystemClassLoader())
            .topLevelClasses
            .map { try { it.load() } catch (e: NoClassDefFoundError) { null } }
            .filterNotNull()
            .filter { it.isImplements(AbstractPlayerFactory::class.java) }



    return CommandLineInputPlayerFactory::class.java
}

private fun <T> Class<T>.isImplements(clazz: Class<*>): Boolean {
    tailrec fun iter(c: Class<*>?): Boolean = when (c) {
        null -> false
        clazz -> true
        else -> iter(c.superclass)
    }
    return iter(this)
}
