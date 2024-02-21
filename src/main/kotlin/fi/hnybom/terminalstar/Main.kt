package fi.hnybom.terminalstar

import fi.hnybom.terminalstar.stars.StarService
import java.nio.file.Paths

fun main(args: Array<String>) {
    when {
        args.isEmpty() -> StarService.addStar(Paths.get("").toAbsolutePath().toString(), "")
        args.size == 1 && args[0] == "ls" -> StarService.getStars().forEach { println(it) }
        args.size == 1 && args[0] == "rm" -> StarService.removePath(Paths.get("").toAbsolutePath().toString())
        args.size == 1 -> StarService.getStarByShorthand(args[0])?.let { println(it.path) }
        args.size == 2 && args[0] == "rm" -> StarService.removePath(args[1])
        else -> StarService.addStar(Paths.get("").toAbsolutePath().toString(), args.joinToString(" "))
    }
}