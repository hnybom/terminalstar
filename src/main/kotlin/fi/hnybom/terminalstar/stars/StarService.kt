package fi.hnybom.terminalstar.stars

import de.m3y.kformat.Table
import de.m3y.kformat.table
import fi.hnybom.terminalstar.config.DbConnection
import java.lang.StringBuilder

object StarService {

    data class Star(val id: Int?, val path: String, val desc: String, val shorthand: String = "")

    init {
        DbConnection.withDatabaseConnection { conn ->
            val stmt = conn.createStatement()
            stmt.execute("CREATE TABLE IF NOT EXISTS stars (id INTEGER PRIMARY KEY, path TEXT, desc TEXT, shorthand TEXT)")
        }
    }

    fun addStar(path: String, desc: String, shorthand: String = "") : Boolean {
        if(isPathAlreadyAdded(path)) return false

        return DbConnection.withDatabaseConnection { conn ->
            val stmt = conn.prepareStatement("INSERT INTO stars (path, desc, shorthand) VALUES (?, ?, ?)")
            stmt.setString(1, path)
            stmt.setString(2, desc)
            stmt.setString(3, shorthand)
            stmt.execute()
            true
        }
    }

    fun getStars(): List<Star> {

        return DbConnection.withDatabaseConnection { conn ->
            val stmt = conn.createStatement()
            val rs = stmt.executeQuery("SELECT * FROM stars order by shorthand")
            generateSequence {
                if (rs.next()) {
                    Star(
                        rs.getInt("id"),
                        rs.getString("path"),
                        rs.getString("desc"),
                        rs.getString("shorthand"))
                } else {
                    null
                }
            }.toList()

        }

    }

    fun getStarByShorthand(shorthand: String): Star? {
        return DbConnection.withDatabaseConnection { conn ->
            val stmt = conn.prepareStatement("SELECT * FROM stars WHERE shorthand = ?")
            stmt.setString(1, shorthand)
            val rs = stmt.executeQuery()
            if (rs.next()) {
                Star(
                    rs.getInt("id"),
                    rs.getString("path"),
                    rs.getString("desc"),
                    rs.getString("shorthand"))
            } else {
                null
            }
        }
    }

    fun removePath(shorthand: String) {
        DbConnection.withDatabaseConnection { conn ->
            val stmt = conn.prepareStatement("DELETE FROM stars WHERE shorthand = ?")
            stmt.setString(1, shorthand)
            stmt.execute()
        }
    }

    private fun isPathAlreadyAdded(path: String): Boolean {
        return DbConnection.withDatabaseConnection { conn ->
            val stmt = conn.prepareStatement("SELECT * FROM stars WHERE path = ?")
            stmt.setString(1, path)
            val rs = stmt.executeQuery()
            rs.next()
        }
    }

}