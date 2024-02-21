package fi.hnybom.terminalstar.stars

import fi.hnybom.terminalstar.config.DbConnection

object StarService {

    data class Star(val id: Int?, val path: String, val desc: String, val shorthand: String = "") {
        override fun toString(): String {
            return if(shorthand.isEmpty() && desc.isEmpty()) {
                path
            } else if(shorthand.isEmpty()) {
                "$path - $desc"
            } else {
                "$path - $shorthand - $desc"
            }
        }
    }

    init {
        DbConnection.withDatabaseConnection { conn ->
            val stmt = conn.createStatement()
            stmt.execute("CREATE TABLE IF NOT EXISTS stars (id INTEGER PRIMARY KEY, path TEXT, desc TEXT, shorthand TEXT)")
        }
    }

    fun addStar(path: String, desc: String, shorthand: String = "") {
        if(isPathAlreadyAdded(path)) return

        DbConnection.withDatabaseConnection { conn ->
            val stmt = conn.prepareStatement("INSERT INTO stars (path, desc, shorthand) VALUES (?, ?, ?)")
            stmt.setString(1, path)
            stmt.setString(2, desc)
            stmt.setString(3, shorthand)
            stmt.execute()
        }
    }

    fun getStars(): List<Star> {

        return DbConnection.withDatabaseConnection { conn ->
            val stmt = conn.createStatement()
            val rs = stmt.executeQuery("SELECT * FROM stars")
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

    fun removePath(path: String) {
        DbConnection.withDatabaseConnection { conn ->
            val stmt = conn.prepareStatement("DELETE FROM stars WHERE path = ?")
            stmt.setString(1, path)
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