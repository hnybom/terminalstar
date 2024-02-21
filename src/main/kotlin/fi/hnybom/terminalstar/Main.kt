package fi.hnybom.terminalstar

import fi.hnybom.terminalstar.stars.StarService
import java.nio.file.Paths

fun main(args: Array<String>) {
    when {
        args.size == 1 && args[0] == "ls" -> StarService.getStars().forEach { println(it) }
        args.size == 1 && args[0] == "rm" -> StarService.removePath(Paths.get("").toAbsolutePath().toString())
        args.size == 1 -> StarService.getStarByShorthand(args[0])?.let { println(it.path) } ?: println("No such shorthand found")
        args.size == 2 && args[0] == "rm" -> StarService.removePath(args[1])
        args.size > 2 && args[0] == "add" -> StarService.addStar(Paths.get("").toAbsolutePath().toString(),
            args.drop(2).joinToString(" "), args[1])
        else -> println("Usage: star [add <shorthand> <desc> | ls | rm | <shorthand>]")
    }
}