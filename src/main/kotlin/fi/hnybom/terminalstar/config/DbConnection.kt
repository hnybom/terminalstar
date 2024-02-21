package fi.hnybom.terminalstar.config

import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


object DbConnection {

    val homedir = "${System.getProperty("user.home")}/.termniastar"
    val connectionUrl = "jdbc:sqlite:$homedir/terminalstar.db"

    init {
        File(homedir).mkdirs()
    }

    fun <R> withDatabaseConnection(block : (Connection) -> R): R {
        DriverManager.getConnection(connectionUrl).use { conn ->
            if (conn != null) {
                return block(conn)
            }
        }

        throw SQLException("Failed to connect to database")
    }

}