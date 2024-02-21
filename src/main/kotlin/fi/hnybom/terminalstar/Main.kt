package fi.hnybom.terminalstar

import de.m3y.kformat.table
import fi.hnybom.terminalstar.stars.StarService
import java.nio.file.Paths

fun main(args: Array<String>) {
    when {
        args.size == 1 && args[0] == "ls" -> table {
                header("Shorthand", "Path", "Description")
                hints {
                    borderStyle = de.m3y.kformat.Table.BorderStyle.SINGLE_LINE
                }
                StarService.getStars().forEach { row(it.shorthand, it.path, it.desc) }
            }.render().let(::println)
        args.size == 1 && args[0] == "rm" -> StarService.removePath(Paths.get("").toAbsolutePath().toString())
        args.size == 1 -> StarService.getStarByShorthand(args[0])?.let { println(it.path) } ?: println("No such shorthand found")
        args.size == 2 && args[0] == "rm" -> StarService.removePath(args[1])
        args.size > 2 && args[0] == "add" -> {
            if (!StarService.addStar(
                    Paths.get("").toAbsolutePath().toString(),
                    args.drop(2).joinToString(" "),
                    args[1]
                )
            ) println("Path already added")

        }
        else -> println("Usage: star [add <shorthand> <desc> | ls | rm | <shorthand>]")
    }
}